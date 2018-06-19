package com.turismo.queue.consumer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {
private static final ObjectMapper mapperJson = new ObjectMapper();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object convertToObject(String jsonInString,  Class clazz) throws Exception {
		return JsonConverter.mapperJson.readValue(jsonInString, clazz);
	}
	public static String convertToJson(Object object)throws Exception {
		return JsonConverter.mapperJson.writeValueAsString(object);
	}
}
