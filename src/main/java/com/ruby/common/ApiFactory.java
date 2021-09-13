package com.ruby.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiFactory {

    public static Object callApi(String apiName) {
        log.info("start calling api: {}", apiName);
        return null;
    }
}
