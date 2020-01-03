package com.emx.platform.utils;

import java.io.Serializable;

public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer code;    //返回码
	private String message; //返回描述
	private T data;			//返回数据
	
	public Result(Integer code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public Result(Integer code, String message, T data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
