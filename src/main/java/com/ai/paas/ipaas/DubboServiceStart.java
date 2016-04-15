package com.ai.paas.ipaas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 缓存服务 2015-03-12
 */
public class DubboServiceStart {
	private static final Logger log = LogManager
			.getLogger(DubboServiceStart.class.getName());

	private static void startDubboService() {
		log.info("开始启动 PaaS Dubbo 服务---------------------------");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:context/applicationContext-*.xml" });
		PaaSBeanFactory.context = context;
		context.registerShutdownHook();
		context.start();
		log.info("PaaS Dubbo 服务启动完毕---------------------------");
		while (true) {
			try {
				Thread.currentThread();
				Thread.sleep(3000L);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		startDubboService();
	}
}
