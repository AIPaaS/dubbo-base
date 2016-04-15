package com.ai.paas.ipaas.rest.manage;

public interface ISrvManager {
	/**
	 * 获取某种类型的服务所支持的租户管理功能，如申请、注销、启动、停止
	 * 
	 * @return
	 */
	public String getFuncList();

	/**
	 * 服务申请
	 * 
	 * @param srvApply
	 *            :申请内容为json格式
	 * @return
	 */
	public String create(String createApply);

	/**
	 * 注销服务，资源回收
	 * 
	 * @param srvCancelApply
	 * @return
	 */
	public String cancel(String cancelApply);

	/**
	 * 调整申请的服务，参数调整
	 * 
	 * @param srvApply
	 * @return
	 */
	public String modify(String modifyApply);

	/**
	 * 启动服务
	 * 
	 * @param startApply
	 * @return
	 */
	public String start(String startApply);

	/**
	 * 停止服务
	 * 
	 * @param stopApply
	 * @return
	 */
	public String stop(String stopApply);

	/**
	 * 重启服务
	 * 
	 * @param restartApply
	 * @return
	 */
	public String restart(String restartApply);
}
