package com.github.benxincaomu.notry.utils;

import java.util.Objects;

import com.github.benxincaomu.notry.code.CommonResponseCode;
import com.github.benxincaomu.notry.code.ResponseCode;
import com.github.benxincaomu.notry.exception.CommonException;

/**
 * 断言工具，条件不成立则抛出异常
 * 
 * @author benxincaomu
 *
 */
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
	 * @param code   响应码
	 * @param params 响应码参数，参数个数应与 定义的 {@link ResponseCode#getMessage()}
	 *               的返回值中的占位符个数保持一致
	 */
	public static void error(ResponseCode code, Object... params) {
		throw new CommonException(code, params);
	}

	/**
	 * 条件不成立则抛出异常
	 * 
	 * @param bool   判断条件
	 * @param code   响应码
	 * @param params 响应码参数，参数个数应与 定义的 {@link ResponseCode#getMessage()}
	 *               的返回值中的占位符个数保持一致
	 */
	public static void isTrue(boolean bool, ResponseCode code, Object... params) {
		if (!bool) {
			error(code, params);
		}
	}

	/**
	 * 条件不成立则抛出异常
	 * 
	 * @param bool   判断条件
	 * @param code   响应码
	 * @param data   业务异常时的额外数据
	 * @param params 响应码参数，参数个数应与 定义的 {@link ResponseCode#getMessage()}
	 *               的返回值中的占位符个数保持一致
	 */
	public static void isTrue(boolean bool, Object data, ResponseCode code, Object... params) {
		if (!bool) {
			throw new CommonException(code, data, params);
		}
	}

	/**
	 * 条件成立则抛出异常
	 * 
	 * @param bool   判断条件
	 * @param code   响应码
	 * @param params 响应码参数，参数个数应与 定义的 {@link ResponseCode#getMessage()}
	 *               的返回值中的占位符个数保持一致
	 */
	public static void isFalse(boolean bool, ResponseCode code, Object... params) {
		if (bool) {
			error(code, params);
		}
	}

	/**
	 * 判断两个对象是否相同，不相同则抛出异常
	 * 
	 * @param obj0   对比对象
	 * @param obj1   对比对象
	 * @param code   响应码
	 * @param params 响应码参数，参数个数应与 定义的 {@link ResponseCode#getMessage()}
	 *               的返回值中的占位符个数保持一致
	 */
	public static void equals(Object obj0, Object obj1, ResponseCode code, Object... params) {
		isTrue(Objects.equals(obj0, obj1), code, params);
	}

}
