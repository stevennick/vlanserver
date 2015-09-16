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
package org.ccma.itri.vlanserver.servlet.reader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;

import org.codehaus.jackson.map.ObjectMapper;

//@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class ObjectReader<T> implements MessageBodyReader<T> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public T readFrom(Class<T> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
					throws IOException, WebApplicationException {

		InputStreamReader reader = new InputStreamReader(entityStream);
		ObjectMapper mapper = new ObjectMapper();
		T object = mapper.readValue(reader, type);

		return object;
	}

}
