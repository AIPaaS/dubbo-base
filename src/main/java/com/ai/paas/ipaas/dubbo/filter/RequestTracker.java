package com.ai.paas.ipaas.dubbo.filter;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.paas.ipaas.PaaSConstant;
import com.ai.paas.ipaas.dubbo.vo.RestResponse;
import com.ai.paas.ipaas.util.DateTimeUtil;
import com.ai.paas.ipaas.util.UUIDTool;
import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcResult;
import com.google.gson.Gson;

@Activate(group = { Constants.PROVIDER })
public class RequestTracker implements Filter {

	private static final Logger LOG = LoggerFactory
			.getLogger(RequestTracker.class);

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) {
		// 获取访问协议
		String protocol = invoker.getUrl().getProtocol();
		String reqSV = invoker.getInterface().getName();
		String reqMethod = invocation.getMethodName();
		Object[] reqParams = invocation.getArguments();
		// 交易序列号+时间
		String reqSeq = DateTimeUtil.dateToString(new java.util.Date(),
				"yyyyMMddHHmmss") + "_" + UUIDTool.genId32();
		Gson gson = new Gson();
		// 打印请求参数明细
		if (null != reqParams && reqParams.length >= 0) {
			if (LOG.isInfoEnabled()) {
				LOG.info(
						"req_seq:{},req_protocol:{},srv_name:{},srv_method:{},srv_params:{}",
						reqSeq, protocol, reqSV, reqMethod,
						gson.toJson(reqParams));
			}
		} else {
			if (LOG.isInfoEnabled()) {
				LOG.info(
						"req_seq:{},req_protocol:{},srv_name:{},srv_method:{},srv_params:{}",
						reqSeq, protocol, reqSV, reqMethod, "blank");
			}
		}
		// 执行结果
		Result result = null;
		try {
			result = invoker.invoke(invocation);
			if (LOG.isInfoEnabled()) {
				LOG.info(
						"req_seq:{} call over....!,req_protocol:{},srv_name:{},srv_method:{},result{}",
						reqSeq, protocol, reqSV, reqMethod, result);
			}
			// 这里需要处理在rest协议下如果方法本身成功，又没有返回的情况
			if (null != protocol && protocol.equalsIgnoreCase("rest")
					&& null == result.getValue()
					&& null == result.getException()) {
				RestResponse response = new RestResponse(
						PaaSConstant.RPC_CALL_OK,
						"Rest service call successfully!");
				// 像客户端返回调用结果
				writeCallResult(response);
			}
			return result;
		} catch (Exception ex) {
			// 此处保留不动
			if (LOG.isErrorEnabled()) {
				LOG.error("req_seq:{},执行{}类中的{}方法发生异常:{}", reqSeq, reqSV,
						reqMethod, ex);
			}
			RpcResult r = new RpcResult();
			r.setException(ex);
			return r;
		}

	}

	private void writeCallResult(RestResponse response) throws Exception {
		if (RpcContext.getContext().getResponse() != null
				&& RpcContext.getContext().getResponse() instanceof HttpServletResponse) {
			HttpServletResponse res = (HttpServletResponse) RpcContext
					.getContext().getResponse();
			res.setContentType(MediaType.APPLICATION_JSON);
			res.setCharacterEncoding(PaaSConstant.CHARSET_UTF8);
			// 设置header
			Gson gson = new Gson();
			res.getWriter().println(gson.toJson(response));
			res.flushBuffer();
		}
	}
}