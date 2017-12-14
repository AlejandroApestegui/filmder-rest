package com.cibertec.filmder.utils;

public class ResponseHelper {

	private Integer result;
	private String message;
	private Object data;
	
	public ResponseHelper(){}
	
	public ResponseHelper(Integer result, String message, Object data) {
		this.result = result;
		this.message = message;
		this.data = data;
	}

	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
}
