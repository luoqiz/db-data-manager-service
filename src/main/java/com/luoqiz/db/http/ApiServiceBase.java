package com.luoqiz.db.http;

import com.luoqiz.db.config.ResponseCodeConstant;

public class ApiServiceBase {

	public static HttpResponseBase setError() {
		return new HttpResponseBase(ResponseCodeConstant.HTTP_RES_CODE_500,
				ResponseCodeConstant.HTTP_RES_CODE_500_VALUE, null);
	}

	public static HttpResponseBase setError(Integer code, String msg, Object data) {
		return new HttpResponseBase(code, msg, data);
	}

	public static HttpResponseBase setError(Integer code, String msg) {
		return new HttpResponseBase(code, msg, null);
	}

	public static HttpResponseBase setError(String msg, Object data) {
		return new HttpResponseBase(ResponseCodeConstant.HTTP_RES_CODE_500, msg, data);
	}

	public static HttpResponseBase setSuccess(String msg, Object data) {
		return new HttpResponseBase(ResponseCodeConstant.HTTP_RES_CODE_200, msg, data);
	}

	public static HttpResponseBase setSuccess(Integer code, String msg, Object data) {
		return new HttpResponseBase(code, msg, data);
	}

	public static HttpResponseBase setSuccess(Object data) {
		return new HttpResponseBase(ResponseCodeConstant.HTTP_RES_CODE_200,
				ResponseCodeConstant.HTTP_RES_CODE_200_VALUE, data);
	}

	public static HttpResponseBase setResult(Integer code, String msg, Object data) {
		return new HttpResponseBase(code, msg, data);
	}
}
