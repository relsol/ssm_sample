package com.ssm.core.frame.exception;

@SuppressWarnings("serial")
public class NkThrowException extends RuntimeException {
	
	/**错误码，用于接收异常的类对某些异常进行处理**/
	private int code=-1;
	
	/**
	 * @param desc	描述
	 */
	public NkThrowException(String desc){
		super(desc);
	}
	
	/**
	 * @param desc	描述
	 * @param code	对应错误码
	 */
	public NkThrowException(String desc,int code){
		super(desc);
		this.code = code;
	}
	
	/**
	 * 
	 * @param desc 描述
	 * @param e
	 */
	public NkThrowException(String desc,Exception e){
		super(desc,e);
	}
	
	/**
	 * 
	 * @param desc 描述
	 * @param code 错误对应码
	 * @param e
	 */
	public NkThrowException(String desc,int code, Exception e){
		super(desc,e);
		this.code = code;
	}
	/**
	 * @param code	错误码
	 */
	public NkThrowException(int code){
		super();
		this.code = code;
	}
	
	/**
	 * @param e	其它异常
	 */
	public NkThrowException(Exception e){
		super(e);
	}
	
	/**
	 * @param e		其它异常
	 * @param code	错误码
	 */
	public NkThrowException(Exception e,int code){
		super(e);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
