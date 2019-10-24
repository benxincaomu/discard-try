package io.github.benxincaomu.notry.utils;

import io.github.benxincaomu.notry.code.CommonResponseCode;
import io.github.benxincaomu.notry.exception.CommonException;

public class Asserts {
    private Asserts() {

    }

    /**
     * 抛出异常，响应代码参考
     * 
     * @see CommonResponseCode#ERROR
     */
    public static void error() {
        throw new CommonException(CommonResponseCode.ERROR);
    }

    /**
     * 抛出异常，响应代码参考
     * 
     * @see CommonResponseCode
     */
    public static void error(CommonResponseCode code, Object... params) {
        throw new CommonException(code, params);
    }

    /**
     * 条件不成立则抛出异常
     * 
     * @param bool
     */
    public static void isTrue(Boolean bool, CommonResponseCode code, Object... params) {
        if (bool == null || !bool) {
            error(code, params);
        }
    }

    /**
     * 条件成立则抛出异常
     * 
     * @param bool
     */
    public static void isFalse(Boolean bool, CommonResponseCode code, Object... params) {
        if (bool != null && bool) {
            error(code, params);
        }
    }

}
