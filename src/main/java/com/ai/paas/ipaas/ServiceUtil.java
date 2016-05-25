package com.ai.paas.ipaas;

import javax.annotation.PostConstruct;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.paas.ipaas.seq.service.ISequenceSv;

@Component
public class ServiceUtil {
	private static SqlSessionTemplate template;

	private static ISequenceSv seqSv;
	@Autowired
	private SqlSessionTemplate annoTemplate;

	@Autowired
	private ISequenceSv annoSeqSv;

	@PostConstruct
	public void init() {
		ServiceUtil.template = annoTemplate;
		ServiceUtil.seqSv = annoSeqSv;
	}

	public static <T> T getMapper(Class<T> clazz) {
		return template.getMapper(clazz);
	}

	public static long nextVal(String seqName) {
		return seqSv.nextVal(seqName);
	}

	public static void main(String[] args) {
		System.out.println(ServiceUtil.nextVal("cust_id"));
	}
}
