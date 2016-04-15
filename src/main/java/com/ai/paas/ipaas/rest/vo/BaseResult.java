package com.ai.paas.ipaas.rest.vo;

public class BaseResult extends BaseInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8231574461428985418L;

	private String resultCode;
	private String resultMsg;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

}
