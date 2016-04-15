package com.ai.paas.ipaas.rest.vo;

import java.io.Serializable;

public class BaseInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1470031147620501451L;
	private String userId;
	private String applyType;
	private String serviceId;

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
