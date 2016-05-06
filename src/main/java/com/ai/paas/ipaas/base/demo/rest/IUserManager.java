package com.ai.paas.ipaas.base.demo.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.paas.ipaas.base.demo.dao.mapper.bo.User;


@Path("user")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
@Produces({ MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public interface IUserManager {
	@Path("create")
	@POST
	public void addUser(User user);
	
	@Path("get")
	@POST
	public User getUser(long id);
}
