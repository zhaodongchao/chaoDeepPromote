package com.securitys.exceptions;

import org.springframework.security.core.AuthenticationException;

public class ErrorVerifycodeException extends AuthenticationException{
	private static final long serialVersionUID = -5041705338556935337L;
	
	public ErrorVerifycodeException(String msg) {
		super("验证码输入错误");
	}

	


}
