package com.ai.paas.ipaas.base.demo.rest.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ai.paas.ipaas.base.demo.dao.mapper.bo.User;
import com.ai.paas.ipaas.base.demo.service.IUserManager;
import com.alibaba.dubbo.config.annotation.Service;

@Service(validation="true")
public class UserManager implements IUserManager {
	private static final Logger log = LogManager.getLogger(UserManager.class
			.getName());

	@Override
	public void addUser(User user) {
		log.info(user);
	}

	@Override
	public User getUser(long id) {
		log.info("---------" + id);
		User user = new User();
		user.setAge(25);
		user.setName("小明");
		return user;
	}

}
