package com.enike.admin.config;

import common.component.HttpResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(basePackages = "com.enike")
public class ResponseBodyHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (null == body && !selectedConverterType.equals(StringHttpMessageConverter.class)) {
            return HttpResult.ok();
        }
        if (selectedConverterType.equals(StringHttpMessageConverter.class)) {
            return HttpResult.buildSuccessResultStr(body);
        }
        if(body.getClass().equals(HttpResult.class)||body.getClass().equals(byte[].class)) {
            return body;
        }
        return HttpResult.ok(body);
    }

}
