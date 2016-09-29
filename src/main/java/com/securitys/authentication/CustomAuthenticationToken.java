package com.securitys.authentication;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * 自定义用户认证标记
 * @author 赵东朝
 * 2015年12月18日
 */
public class CustomAuthenticationToken extends AbstractAuthenticationToken{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//~ Instance fields ================================================================================================

    private final Object principal;
    private Object credentials;
    private Object verifications ;
    
    
	public CustomAuthenticationToken(Object principal,Object credentials,Object verifications) {
		     super(null);
	        this.principal = principal;
	        this.credentials = credentials;
	        this.verifications = verifications ;
	        setAuthenticated(false);
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

}
