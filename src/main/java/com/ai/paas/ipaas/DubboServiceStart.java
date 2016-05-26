package com.ai.paas.ipaas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * dubbo启动入口，支持自动扫描配置文件
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
		log.info("Got jdbc connection pool type:"+context.getBeanFactory().resolveEmbeddedValue("${jdbc.cnnPoolType}"));
		log.info("Using HikariCP connection pool:"
				+ context.getBean("useHikariCP"));
		log.info("PaaS Dubbo 服务启动完毕---------------------------");
		while (true) {
			try {
				Thread.currentThread();
				Thread.sleep(3000L);
			} catch (Exception e) {
				log.error("Dubbo error", e);
			}
		}
	}

	public static void main(String[] args) {
		startDubboService();
		
	}
}
