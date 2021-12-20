package com.github.benxincaomu.notry.exception.handler;



import com.github.benxincaomu.notry.code.CommonResponseCode;
import com.github.benxincaomu.notry.exception.CommonException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.HandlerResult;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * 请求后置处理，异常处理和响应结构处理,支持webflux
 * @author sunfutao
 */
@RestControllerAdvice
public class CommonHandler extends ResponseBodyResultHandler  {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    MethodParameter param = null ; 
    
    public CommonHandler(ServerCodecConfigurer serverCodecConfigurer,RequestedContentTypeResolver requestedContentTypeResolver){
        super(serverCodecConfigurer.getWriters(), requestedContentTypeResolver);
    }

    @Override
    public boolean supports(HandlerResult result) {
        return super.supports(result);
    }
    @SuppressWarnings({"unused"})
    private static Mono<?> methodForParams() {
        return null;
    }

    @Override
    public Mono<Void> handleResult(ServerWebExchange exchange, HandlerResult result) {
        
        if(param == null){
            synchronized(CommonHandler.class){
                if(param == null){
                    try {
                        param = new MethodParameter(getClass().getDeclaredMethod("methodForParams"), -1);
                    } catch (NoSuchMethodException | SecurityException e) {
                        logger.error("create MethodParameter failed.....", e);;
                        throw new RuntimeException("create MethodParameter failed.....");
                    }
                }
            }
        }
        
        MediaType contentType = exchange.getResponse().getHeaders().getContentType();

        Object body = result.getReturnValue();
        
        if(body == null){
            body = new ResponseMessage<String>();
        }else if(body instanceof ResponseMessage){
            // 已处理格式,无需处理

        }else if(contentType!=null 
        && !MediaType.APPLICATION_JSON.equalsTypeAndSubtype(contentType)  
        && !MediaType.APPLICATION_JSON.equalsTypeAndSubtype(contentType)
        && !MediaType.TEXT_XML.equalsTypeAndSubtype(contentType)){
            // jackson处理的格式之外，无需处理

        }else{
            body = new ResponseMessage<>(CommonResponseCode.SUCCESS,body);
        }
        body = Mono.just(body);
        return writeBody(body, param, exchange);
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
