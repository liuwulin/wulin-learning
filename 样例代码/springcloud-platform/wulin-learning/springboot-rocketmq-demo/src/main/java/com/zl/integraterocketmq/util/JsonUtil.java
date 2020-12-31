package com.zl.integraterocketmq.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: zhouliang
 * @Date: 2019/7/12 10:04
 */
@Slf4j
public class JsonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    }

    /**
     * 对象转json字符串
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String objectToJson(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : mapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse Object to Json error", e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 对象转格式化的json字符串
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String objectToJsonPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse Object to Json error", e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * json转java对象
     *
     * @param src
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String src, Class<T> clazz) {
        if (StringUtils.isEmpty(src) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) src : mapper.readValue(src, clazz);
        } catch (Exception e) {
            log.warn("Parse Json to Object error", e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * json转java对象或list、map
     *
     * @param src
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String src, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(src) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? src : mapper.readValue(src, typeReference));
        } catch (Exception e) {
            log.warn("Parse Json to Object error", e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * json转java对象或list、map
     *
     * @param <T>
     * @param src
     * @return
     */
    public static <T> T jsonToObject(String src, Class<?> collectionClass, Class<?>... elementClasses) {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            return mapper.readValue(src, javaType);
        } catch (Exception e) {
            log.warn("Parse Json to Object error", e);
            e.printStackTrace();
            return null;
        }
    }
}
