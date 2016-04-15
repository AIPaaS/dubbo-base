package com.ai.paas.ipaas;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class PaaSBeanFactory {
	static ClassPathXmlApplicationContext context;

	private PaaSBeanFactory() {
	}

	public static <T> T getBean(String name, Class<T> cls) {
		if (context != null) {
			return context.getBean(name, cls);
		} else
			return null;
	}

	public static Object getBean(String name) {
		if (context != null) {
			return context.getBean(name);
		} else
			return null;
	}
}
