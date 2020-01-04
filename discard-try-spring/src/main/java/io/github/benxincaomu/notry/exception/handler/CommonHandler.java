package io.github.benxincaomu.notry.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import io.github.benxincaomu.notry.code.CommonResponseCode;
import io.github.benxincaomu.notry.exception.CommonException;

/**
 * 请求后置处理，异常处理和响应结构处理
 */
@RestControllerAdvice
public class CommonHandler implements ResponseBodyAdvice<Object> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		if (body instanceof ResponseMessage) {
			return body;
		}
		if (selectedContentType != MediaType.APPLICATION_JSON && selectedContentType != MediaType.APPLICATION_JSON_UTF8
				&& selectedContentType != MediaType.TEXT_XML) {
			return body;
		}
		return new ResponseMessage<>(CommonResponseCode.SUCCESS, null);
	}

	@ExceptionHandler({ CommonException.class })
	public ResponseMessage<?> resolveCommonException(CommonException e) {
		if (logger.isDebugEnabled()) {
			logger.debug("bussiness code:" + e.getCode());
		}
		ResponseMessage<?> message = new ResponseMessage<>(e.getCode(), e.getMessage(), null);
		return message;
	}

}
