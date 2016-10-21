package com.ai.paas.ipaas.rpc.api.seq;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.GZIP;

import com.ai.paas.ipaas.rpc.api.vo.ApplyInfo;
import com.ai.paas.ipaas.rpc.api.vo.ApplyResult;
import com.ai.paas.ipaas.rpc.api.vo.SequenceInfo;

@Path("seq")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface ISequenceRPC {

	/**
	 * 获取下一个序列值
	 * 
	 * @param sequenceName
	 * @return
	 */
	@Path("get/{seqName}")
	@GET
	@GZIP
	public Long nextVal(@PathParam("seqName") @NotNull String sequenceName);

	/**
	 * 创建一个序列
	 * 
	 * @param seq
	 */

	@interface CreateSequence {
	}

	@Path("add")
	@POST
	public void createSequence(SequenceInfo seq);

	/**
	 * @param info
	 * @return
	 */
	@Path("test")
	@POST
	public ApplyResult getSeq(ApplyInfo info);
}
