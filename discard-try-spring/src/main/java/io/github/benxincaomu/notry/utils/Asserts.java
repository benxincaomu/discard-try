package io.github.benxincaomu.notry.utils;

import io.github.benxincaomu.notry.code.CommonResponseCode;
import io.github.benxincaomu.notry.exception.CommonException;

public class Asserts {
    private Asserts() {

    }

    public static void error() {
        throw new CommonException(CommonResponseCode.ERROR);
    }

}
