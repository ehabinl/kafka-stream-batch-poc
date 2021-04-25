/************************************************************
 * Copyright ©2015-2020 Boutiqaat. All rights reserved
 * —————————————————————————————————
 * NOTICE: All information contained herein is a property of Boutiqaat.
 *************************************************************/
package com.example.demo;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author ehakawati
 *
 */
@Slf4j
public class JsonUtils {

	private static JsonUtils INSTANCE = new JsonUtils();
	private ObjectMapper mapper;

	/**
	 *
	 */
	private JsonUtils() {
		mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, true);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * @return the mapper
	 */
	public ObjectMapper getMapper() {
		return mapper;
	}

	/**
	 * @param object
	 * @return
	 */
	public static String encode(final Object object) {
		try {
			return INSTANCE.mapper.writeValueAsString(object);
		} catch (final IOException | IllegalArgumentException e) {
			log.error("Unable to encode object", e);
		}
		return "";
	}

	/**
	 * @param object
	 * @return
	 */
	public static String encodePretty(final Object object) {
		try {
			INSTANCE.mapper.enable(SerializationFeature.INDENT_OUTPUT);
			return INSTANCE.mapper.writeValueAsString(object);
		} catch (final IOException | IllegalArgumentException e) {
			log.error("Unable to encode object", e);
		} finally {
			INSTANCE.mapper.disable(SerializationFeature.INDENT_OUTPUT);
		}
		return "";
	}

	/**
	 *
	 * @param jsonString
	 * @return
	 */
	public static Map<String, Object> decode(final String jsonString) {
		try {
			final TypeReference<HashMap<String, Object>> typeReference = new TypeReference<HashMap<String, Object>>() {
			};
			return INSTANCE.mapper.readValue(jsonString, typeReference);
		} catch (final IOException | IllegalArgumentException e) {
			log.error("Unable to decode object", e);
		}
		return Collections.emptyMap();
	}

	/**
	 *
	 * @param jsonString
	 * @param castAs
	 * @return
	 */
	public static <T> T decode(final String jsonString, final Class<T> castAs) {
		try {
			return INSTANCE.mapper.readValue(jsonString, castAs);
		} catch (final IOException | IllegalArgumentException e) {
			log.error("Unable to decode object", e);
		}
		return null;
	}

}
