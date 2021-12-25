package com.sprng.comn_comman.exception;

public class ApplicationException  extends Exception {

	private static final long serialVersionUID = 1L;
	
	String msg;

	public ApplicationException() {
		super();
	}
	
	public ApplicationException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	

}
