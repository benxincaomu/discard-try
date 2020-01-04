package io.github.benxincaomu.notry.code;

/**
 * 响应码定义，自定义请实现此接口
 * 
 * @see CommonResponseCode
 * @author benxincaomu
 *
 */
public interface ResponseCode {
    /**
     * get responseCode
     * 
     * @return code
     */
    int getCode();

    /**
     * get responseMessage
     * 
     * @return message
     */
    String getMessage();

}
