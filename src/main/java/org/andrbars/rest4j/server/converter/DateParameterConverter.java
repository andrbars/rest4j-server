package org.andrbars.rest4j.server.converter;

import static com.fasterxml.jackson.databind.util.StdDateFormat.DATE_FORMAT_STR_ISO8601;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.ParamConverter;

public class DateParameterConverter implements ParamConverter<Date>
{

	private final String format;

	public DateParameterConverter()
	{
		this.format = DATE_FORMAT_STR_ISO8601;
	}

	public DateParameterConverter(String format)
	{
		this.format = format;
	}

	@Override
	public Date fromString(String string)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		try
		{
			return simpleDateFormat.parse(string);
		}
		catch (ParseException ex)
		{
			throw new WebApplicationException(ex);
		}
	}

	@Override
	public String toString(Date t)
	{
		return new SimpleDateFormat(format).format(t);
	}

}
