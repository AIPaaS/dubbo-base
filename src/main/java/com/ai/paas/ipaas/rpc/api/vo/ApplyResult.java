package com.ai.paas.ipaas.rpc.api.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ai.dubbo.ext.vo.BaseResponse;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ApplyResult extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8231574461428985418L;

	private String userId;
	private String applyType;
	private String serviceId;

	public ApplyResult() {

	}

	public ApplyResult(String resultCode, String resultMesg) {
		super(resultCode, resultMesg);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

}
