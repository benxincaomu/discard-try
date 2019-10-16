package io.github.benxincaomu.notry.exception.handler;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import io.github.benxincaomu.notry.code.CommonResponseCode;

/**
 * 请求后置处理，异常处理和响应结构处理
 */
@RestControllerAdvice
public class CommonHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {
        if (body instanceof ResponseMessage) {
            return body;
        }
        if (selectedContentType != MediaType.APPLICATION_JSON
                && selectedContentType != MediaType.APPLICATION_JSON_UTF8) {
            return body;
        }
        return new ResponseMessage(CommonResponseCode.SUCCESS, null);
    }

}
