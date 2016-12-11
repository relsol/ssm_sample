package com.ssm.core.frame.utils;

import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtils {
	
	private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	
	private static ObjectMapper mapper;
	private static JsonFactory factory;
	/**
	 * 将bean、List、Map、Array转成Json字符串
	 * @param obj bean、List、Map、Array
	 * @return json 字符串
	 */
	public static String objToString(Object obj){
		String json = "";
		if(null == mapper) mapper = new ObjectMapper();
		try {
			// 格式化
//			mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE); 
			json = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			logger.error("生成JSON字符串出错"+obj.getClass().getName(),e);
			json = "";
		}
		return json;
	}
	
	public static <T> T stringToObj(String json, Class<T> clazz){
		if(null == mapper) mapper = new ObjectMapper();
		if(null == factory) {
			factory = new JsonFactory(mapper);
			mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
			mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		}
		try {
			JsonParser jsonParser = factory.createJsonParser(json);
			return jsonParser.readValueAs(clazz);
		} catch (Exception e) {
			logger.error(""+e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 获取泛型的Collection Type
	 * @param jsonStr json字符串
	 * @param collectionClass 泛型的Collection
	 * @param elementClasses 元素类型
	 */
	public static <T> T readJson(String jsonStr, Class<?> collectionClass, Class<?>... elementClasses) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(
				collectionClass, elementClasses);
		return mapper.readValue(jsonStr, javaType);
	}
}
