package com.rumahbangsa.draftbox.handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ApplicationExceptionHandler implements ExceptionMapper<Exception> {

	Logger log = LoggerFactory.getLogger(ApplicationExceptionHandler.class);
	
	@Override
	public Response toResponse(Exception exception) {
		log.error("Error", exception);
		return Response.status(Status.BAD_REQUEST).entity(exception.getMessage()).build();
	}

}
