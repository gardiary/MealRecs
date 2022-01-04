package com.meal.recs.utility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/**
 * User: gardiary
 * Date: 03/01/2022, 21.12
 */
public class Utilities {
	private static final Logger LOGGER = LoggerFactory.getLogger(Utilities.class);
	public static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
	public static final DecimalFormat DECIMAL_FORMAT;
	public static final String DATE_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_STR);

	static {
		DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.getDefault());
		dfs.setDecimalSeparator(',');
		dfs.setGroupingSeparator('.');
		DECIMAL_FORMAT = new DecimalFormat("", dfs);
	}

	/**
	 * Simple Random code.
	 *
	 * @param length Length of code
	 * @return String
	 */
	public static String generateRandomCode(final int length) {
		String retvalue = "";
		for (int cnt = 0; length > cnt; cnt++) {
			retvalue += new Random().nextInt(10);
		}
		return retvalue;
	}

	public static String generateRandomString(int length) {
		StringBuilder builder = new StringBuilder();
		while (length-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	public static boolean isBlank(Object value) {
		if (value == null) {
			return true;
		} else if (value instanceof String) {
			return (((String) value).trim().length() == 0);
		} else if (value instanceof Map) {
			return ((Map) value).isEmpty();
		} else if (value instanceof Collection) {
			return ((Collection) value).isEmpty();
		} else {
			return value.getClass().isArray() && (Array.getLength(value) == 0);
		}
	}

	public static boolean isNotBlank(Object value) {
		return !isBlank(value);
	}

	public static String formalDecimal(double number) {
		return DECIMAL_FORMAT.format(number);
	}

	public static String objToJsonString(Object obj, boolean indentation, boolean includeNulls, DateFormat dateFormat) {
		try {
			if (obj != null) {
				ObjectMapper om = new ObjectMapper();
				if (!includeNulls) {
					om.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				}
				if (indentation) {
					om.configure(SerializationFeature.INDENT_OUTPUT, true);
				}
				om.setDateFormat(dateFormat);
				return om.writeValueAsString(obj);
			} else {
				return null;
			}
		} catch (Exception e) {
			LOGGER.error("Error in objToJsonString", e);
			return null;
		}
	}

	public static String objToJsonString(Object obj) {
		return objToJsonString(obj, false, false, new SimpleDateFormat(DATE_FORMAT_STR));
	}

	public static <T extends Object> T jsonStringToObj(String json, Class<T> clazz, boolean failOnUnknownProperties) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, failOnUnknownProperties);
		objectMapper.setDateFormat(DATE_FORMAT);
		try {
			return objectMapper.readValue(json, clazz);
		} catch (JsonProcessingException e) {
			return null;
		}
	}

	public static <T extends Object> T jsonStringToObj(String json, Class<T> clazz) {
		return jsonStringToObj(json, clazz, true);
	}

	/*public static <T extends Object> T jsonStringToObj(String json, TypeReference<T> typeReference) throws Exception {
		return jsonStringToObj(json, typeReference, true);
	}

	public static <T extends Object> T jsonStringToObj(String json, TypeReference<T> typeReference,
													   boolean failOnUnknownProperties) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, failOnUnknownProperties);
		objectMapper.setDateFormat(WebUtils.getIsoDateFormat());
		return objectMapper.readValue(json, typeReference);
	}*/



}
