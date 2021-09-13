package com.ruby.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JSONUtil {

    public static <T> T toObject(Object json, Class<T> clazz) {
        if (json == null) {
            throw new IllegalArgumentException("Json can not be null");
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            if (json instanceof String) {
                return mapper.readValue(json.toString(), clazz);
            } else {
                return mapper.readValue(mapper.writeValueAsString(json), clazz);
            }
        } catch (JsonProcessingException e) {
            log.error("failed obj to json");
        }
        return null;
    }

    public static String toJsonStr(Object json) {
        if (json == null) {
            throw new IllegalArgumentException("Json can not be null");
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (json instanceof String) {
                return json.toString();
            } else {
                return mapper.writeValueAsString(json);
            }
        } catch (JsonProcessingException e) {
            log.error("failed to parse json str");
        }
        return "";
    }
}
