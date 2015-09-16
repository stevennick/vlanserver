/*******************************************************************************
 * Copyright (c) 2010, 2014 Information and Communications Research Laboratories
 * Industrial Technology Research Institute (ITRI) and others.
 * All rights reserved. 
 *
 * This program and the accompanying materials are made available under the terms of the
 * ITRI Copyright License v1 which accompanies this distribution, and is available at
 * http://www.itri.org.tw/eng/econtent/copyright/copyright01.aspx
 *
 * Authors:
 *     (c) 2010, 2014 Yi-Fu Ciou <stevennick@gmail.com>
 *     And all corresponding contributors.
 *******************************************************************************/
package org.ccma.itri.vlanserver.servlet.provider;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.ccma.itri.vlanserver.model.ApplicationException;

import com.sun.jersey.core.spi.factory.ResponseImpl;

@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

	@Override
	public Response toResponse(WebApplicationException exception) {
		ApplicationException appException;
		Response sourceResponse = exception.getResponse();
		if (sourceResponse instanceof ResponseImpl) {
			ResponseImpl jerseyResponse = (ResponseImpl) sourceResponse;
			appException = new ApplicationException(sourceResponse.getStatus(),
					jerseyResponse.getStatusType().toString(), exception.getMessage());
		} else {
			appException = new ApplicationException(sourceResponse.getStatus(), null, exception.getMessage());
		}

		return Response.status(sourceResponse.getStatus()).entity(appException).type(MediaType.APPLICATION_JSON)
				.build();
	}
}
