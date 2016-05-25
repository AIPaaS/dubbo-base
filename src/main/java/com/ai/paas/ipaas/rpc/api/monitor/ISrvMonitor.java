package com.ai.paas.ipaas.rpc.api.monitor;


/**
 * 面向租户的监控服务提供
 * 
 * @author DOUXF
 *
 */
public interface ISrvMonitor {
	public String getUsage(String usageReq);
}
