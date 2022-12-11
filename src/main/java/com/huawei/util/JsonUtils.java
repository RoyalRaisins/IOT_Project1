package com.huawei.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class JsonUtils {

    private static ObjectMapper objectMapper = null;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String Obj2String(Object object) {
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            System.out.println("parse object error.");
        }
        return jsonString;
    }

    public static <T> T jsonStr2Object(String content, Class<T> tClass) {
        T obj = null;
        try {
            obj = objectMapper.readValue(content, tClass);
        } catch (JsonProcessingException e) {
            System.out.println("parse object error.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
