package org.andrbars.rest4j.server.converter;

import static com.fasterxml.jackson.databind.util.StdDateFormat.DATE_FORMAT_STR_ISO8601;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Date;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;

public class DateParameterConverterProvider implements ParamConverterProvider
{

	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations)
	{
		if (Date.class.equals(rawType))
		{
			@SuppressWarnings("unchecked")
			ParamConverter<T> paramConverter = (ParamConverter<T>)new DateParameterConverter(DATE_FORMAT_STR_ISO8601);//baa !!! взять формат строки из анотации
			return paramConverter;
		}
		return null;
	}

}
