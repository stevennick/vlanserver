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
			appException = new ApplicationException(sourceResponse.getStatus(), jerseyResponse.getStatusType().toString(), exception.getMessage());
		} else {
			appException = new ApplicationException(sourceResponse.getStatus(), null, exception.getMessage());
		}

		return Response.status(sourceResponse.getStatus()).entity(appException).type(MediaType.APPLICATION_JSON).build();
	}
}
