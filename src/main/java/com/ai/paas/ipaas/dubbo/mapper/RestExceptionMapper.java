package com.ai.paas.ipaas.dubbo.mapper;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.ai.paas.ipaas.ExceptionSerializer;
import com.ai.paas.ipaas.PaaSConstant;
import com.ai.paas.ipaas.dubbo.vo.RestResponse;
import com.alibaba.dubbo.rpc.protocol.rest.RestConstraintViolation;
import com.alibaba.dubbo.rpc.protocol.rest.ViolationReport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RestExceptionMapper implements ExceptionMapper<Exception> {

	private static final String HTTP = "HTTP";

	@Override
	public Response toResponse(Exception ex) {
		RestResponse error = null;
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Throwable.class,
				new ExceptionSerializer());
		Gson gson = gsonBuilder.create();
		if (ex instanceof ForbiddenException) {
			error = new RestResponse(HTTP + "."
					+ Response.Status.FORBIDDEN.getStatusCode(),
					ex.getMessage());
			return Response.status(Response.Status.FORBIDDEN)
					.entity(gson.toJson(error)).type("text/plain").build();
		} else if (ex instanceof BadRequestException) {
			error = new RestResponse(HTTP + "."
					+ Response.Status.BAD_REQUEST.getStatusCode(),
					ex.getMessage());
			return Response.status(Response.Status.BAD_REQUEST)
					.entity(gson.toJson(error)).type("text/plain").build();
		} else if (ex instanceof InternalServerErrorException) {
			error = new RestResponse(HTTP + "."
					+ Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
					ex.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(gson.toJson(error)).type("text/plain").build();
		} else if (ex instanceof NotAuthorizedException) {
			error = new RestResponse(HTTP + "."
					+ Response.Status.UNAUTHORIZED.getStatusCode(),
					ex.getMessage());
			return Response.status(Response.Status.UNAUTHORIZED).entity(error)
					.type("text/plain").build();
		} else if (ex instanceof NotAcceptableException) {
			error = new RestResponse(HTTP + "."
					+ Response.Status.NOT_ACCEPTABLE.getStatusCode(),
					ex.getMessage());
			return Response.status(Response.Status.NOT_ACCEPTABLE)
					.entity(gson.toJson(error)).type("text/plain").build();
		} else if (ex instanceof NotFoundException) {
			// 返回实体为JSON文本，考虑DUBBOX框架，如果是对象，则客户端获取不到
			error = new RestResponse(HTTP + "."
					+ Response.Status.NOT_FOUND.getStatusCode(),
					ex.getMessage());
			return Response.status(Response.Status.NOT_FOUND)
					.entity(gson.toJson(error)).type("text/plain").build();
		}
		if (ex.getCause() instanceof ConstraintViolationException) {
			return handleConstraintViolationException((ConstraintViolationException) ex
					.getCause());
		} else {
			// 此处，如果是jsonparseException，本身是个无限循环的异常嵌套，因此系列化时会堆栈溢出
			// 因此需要进行转换，即使自定义异常序列化也溢出
			error = new RestResponse(PaaSConstant.ExceptionCode.SYSTEM_ERROR,
					ex.getMessage(), ex.getStackTrace());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(gson.toJson(error)).type("text/plain").build();
		}

	}

	@SuppressWarnings("rawtypes")
	protected Response handleConstraintViolationException(
			ConstraintViolationException cve) {
		Gson gson = new Gson();
		ViolationReport report = new ViolationReport();
		for (ConstraintViolation cv : cve.getConstraintViolations()) {
			report.addConstraintViolation(new RestConstraintViolation(cv
					.getPropertyPath().toString(), cv.getMessage(), cv
					.getInvalidValue() == null ? "null" : cv.getInvalidValue()
					.toString()));
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(gson.toJson(report)).type("text/plain").build();
	}
}