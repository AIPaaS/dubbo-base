package com.ai.paas.ipaas.dubbo.vo;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

import com.ai.paas.ipaas.rpc.api.vo.BaseResult;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RestResponse extends BaseResult {

	private static final long serialVersionUID = -2685764073290390958L;

	private Object info;

	public RestResponse(String resultCode, String resultMsg, Object info) {
		super.setResultCode(resultCode);
		super.setResultMsg(resultMsg);
		this.info = info;
	}

	public RestResponse(String resultCode, String resultMsg) {
		super.setResultCode(resultCode);
		super.setResultMsg(resultMsg);
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

}
