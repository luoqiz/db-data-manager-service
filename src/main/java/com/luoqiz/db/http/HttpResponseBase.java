package com.luoqiz.db.http;

import java.io.Serializable;

public class HttpResponseBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1879381784055514895L;

	private Integer status;
	private String msg;
	private Object results;

	HttpResponseBase() {
	}

	HttpResponseBase(Integer reCode, String msg, Object data) {
		this.status = reCode;
		this.msg = msg;
		this.results = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResults() {
		return results;
	}

	public void setResults(Object results) {
		this.results = results;
	}
}
