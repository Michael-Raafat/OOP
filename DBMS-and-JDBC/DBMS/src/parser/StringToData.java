package parser;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import models.Datatype;


public class StringToData {
	public static final Integer stringToInteger(String value) {
		Integer integer;
		if (value == null
			|| value.equals(Datatype.INTEGER.getDefaultNullValue())) {
			integer = null;
		} else {
			integer = Integer.parseInt(value);
		}
		return integer;
	}
	public static final float stringToFloat(String value) {
		Float floatNumber;
		if (value == null
			|| value.equals(Datatype.FLOAT.getDefaultNullValue())) {
			floatNumber = null;
		} else {
			floatNumber = Float.parseFloat(value);
		}
		return floatNumber;
	}

	public static final Date stringToDate(String value) {
		if (value == null
			|| value.equals(Datatype.DATE.getDefaultNullValue())) {
			return null;
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		try {
			startDate = new Date(df.parse(value).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return startDate;
	}
}
