package com.ruby.common;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestParser {

    public static Map<String, Object> requestParamParser(HttpRequest req) throws IOException {
        Map<String, Object> result = new HashMap<>();
        HttpMethod method = req.method();
        if (HttpMethod.GET == method) {
            QueryStringDecoder queryStr = new QueryStringDecoder(req.uri());
            queryStr.parameters().entrySet().forEach(param -> {
                result.put(param.getKey(), param.getValue().get(0));
            });
        } else if (HttpMethod.POST == method) {
            HttpPostRequestDecoder post = new HttpPostRequestDecoder(req);
            List<InterfaceHttpData> params = post.getBodyHttpDatas();
            for (InterfaceHttpData param : params) {
                Attribute attribute = (Attribute) param;
                result.put(attribute.getName(), attribute.getValue());
            }
        } else {
            throw new UnsupportedOperationException("Not support this method type");
        }
        return result;
    }
}
