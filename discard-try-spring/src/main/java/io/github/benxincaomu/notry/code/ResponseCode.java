package io.github.benxincaomu.notry.code;

/**
 * response code and message
 * 
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
