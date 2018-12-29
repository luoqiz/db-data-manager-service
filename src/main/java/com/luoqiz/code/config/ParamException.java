package com.luoqiz.code.config;

public class ParamException extends Exception {

	private static final long serialVersionUID = 1L;

	private String code = "500";

	// 提供无参数的构造方法
	public ParamException() {
	}

	// 提供一个有参数的构造方法，可自动生成
	public ParamException(String message) {
		super(message);// 把参数传递给Throwable的带String参数的构造方法
	}

	// 提供一个有参数的构造方法，可自动生成
	public ParamException(String code, String message) {
		super(message);// 把参数传递给Throwable的带String参数的构造方法
		this.setCode(code);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
