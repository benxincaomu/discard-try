package io.github.benxincaomu.notry.exception.handler;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.benxincaomu.notry.code.CommonResponseCode;
import io.github.benxincaomu.notry.exception.CommonException;
import io.github.benxincaomu.notry.exception.handler.ResponseMessage;

/**
 * 请求后置处理，异常处理和响应结构处理
 * @author sunfutao
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
		
		if (!selectedContentType.equalsTypeAndSubtype(MediaType.APPLICATION_JSON)  && !selectedContentType.equalsTypeAndSubtype(MediaType.TEXT_PLAIN)
				&& !selectedContentType.equalsTypeAndSubtype(MediaType.TEXT_XML)) {
			return body;
		}
		if(body instanceof String){
			body = new ResponseMessage<>(CommonResponseCode.SUCCESS, body);
			response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
			ObjectMapper mapper = new ObjectMapper();
			try {
				return mapper.writeValueAsString(body);
			} catch (JsonProcessingException e) {
				logger.trace("writeValueAsString failed.....", e);
				return "inner error";
			}
		}
		
		return new ResponseMessage<>(CommonResponseCode.SUCCESS, body);
	}

	@ExceptionHandler({ CommonException.class })
	public ResponseMessage<?> resolveCommonException(CommonException e) {
		if (logger.isDebugEnabled()) {
			logger.debug("bussiness code:" + e.getCode());
		}
		ResponseMessage<?> message = new ResponseMessage<>(e.getCode(), e.getMessage(), null);
		return message;
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseMessage<?> resolveMethodArgumentNotValidException(MethodArgumentNotValidException e) { 
		logger.error("method argument not valid", e);

		return new ResponseMessage<>(CommonResponseCode.PARAM_VALIDATE_FAILED, e.getBindingResult().getFieldError().getDefaultMessage());

	}

}
