package com.ai.paas.ipaas.rpc.api.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ai.dubbo.ext.vo.BaseInfo;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ApplyInfo extends BaseInfo implements Serializable {
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
