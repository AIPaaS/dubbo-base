package com.ai.paas.ipaas.dubbo.mapper;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.ai.paas.ipaas.ExceptionSerializer;
import com.ai.paas.ipaas.PaaSConstant;
import com.ai.paas.ipaas.dubbo.vo.RestResponse;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.protocol.rest.RestConstraintViolation;
import com.alibaba.dubbo.rpc.protocol.rest.ViolationReport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RpcExceptionMapper implements ExceptionMapper<RpcException> {
	public Response toResponse(RpcException e) {
		RestResponse error = null;
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Throwable.class,
				new ExceptionSerializer());
		Gson gson = gsonBuilder.create();
		if (e.getCause() instanceof ConstraintViolationException) {
			return handleConstraintViolationException((ConstraintViolationException) e
					.getCause());
		}
		error = new RestResponse(PaaSConstant.ExceptionCode.SYSTEM_ERROR,
				e.getMessage(), e.getStackTrace());
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(gson.toJson(error)).type("text/plain").build();
	}

	@SuppressWarnings("rawtypes")
	protected Response handleConstraintViolationException(
			ConstraintViolationException cve) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Throwable.class,
				new ExceptionSerializer());
		Gson gson = gsonBuilder.create();
		ViolationReport report = new ViolationReport();
		for (ConstraintViolation cv : cve.getConstraintViolations()) {
			report.addConstraintViolation(new RestConstraintViolation(cv
					.getPropertyPath().toString(), cv.getMessage(), cv
					.getInvalidValue() == null ? "null" : cv.getInvalidValue()
					.toString()));
		}
		RestResponse error = new RestResponse(
				PaaSConstant.ExceptionCode.SYSTEM_ERROR, "validation error",
				report);
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(gson.toJson(error)).type("text/plain").build();
	}
}
