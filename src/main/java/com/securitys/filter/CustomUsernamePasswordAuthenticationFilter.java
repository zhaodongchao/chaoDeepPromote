package com.securitys.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import com.securitys.exceptions.ErrorVerifycodeException;


public class CustomUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
  
	  //~ Static fields/initializers =====================================================================================

    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "login_username";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "login_password";
    public static final String SPRING_SECURITY_FORM_ACTION_KEY = "/erp_spring_login_url";
    public static final String SPRING_SECURITY_ENCRYPT_KEY = "EncryptKey";
    public static final String SPRING＿SECURITY_ENCRYPT_IV = "EncryptIv" ;
    public static final String SPRING_SECURITY_FORM_VERIFYCODE = "login_verifycode"; 
    
    public static final String ERR_VAILDATECODE = "验证码输入错误";
    public static final String ERR_USERNAMEANDPASSWORD = "用户或密码错误" ;
   
    public static final String SPRING_SECURITY_CURRENT_USERNAME = "currentUsername";

    private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
    private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
    private boolean continueChainBeforeSuccessfulAuthentication = false;
    private SessionAuthenticationStrategy sessionStrategy = new NullAuthenticatedSessionStrategy();
    private boolean postOnly = true;
    
    
	protected CustomUsernamePasswordAuthenticationFilter() {
		super(SPRING_SECURITY_FORM_ACTION_KEY);
		
	}
    
	/***
	 * 执行身份认证操作,认证成功之后就还回认证信息，失败就抛出异常
	 * 
	 * 
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		  if (postOnly && !request.getMethod().equals("POST")) {
	            throw new AuthenticationServiceException("身份认证方法必为POST，当前的提交方式为: " + request.getMethod());
	        }
		
		    String username = obtainUsername(request);
	        String password = obtainPassword(request);
		     
	        
	        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		
	       
	        
	        // Place the last username attempted into HttpSession for views
	        HttpSession session = request.getSession(false);
	 
	        if (session != null || getAllowSessionCreation()) {
	            request.getSession().setAttribute(SPRING_SECURITY_CURRENT_USERNAME,username);
	        }
	        
	     // Allow subclasses to set the "details" property
	        setDetails(request, authRequest);
	        
	        return this.getAuthenticationManager().authenticate(authRequest);
	}
	 /**
     * Invokes the {@link #requiresAuthentication(HttpServletRequest, HttpServletResponse) requiresAuthentication}
     * method to determine whether the request is for authentication and should be handled by this filter.
     * If it is an authentication request, the
     * {@link #attemptAuthentication(HttpServletRequest, HttpServletResponse) attemptAuthentication} will be invoked
     * to perform the authentication. There are then three possible outcomes:
     * <ol>
     * <li>An <tt>Authentication</tt> object is returned.
     * The configured {link SessionAuthenticationStrategy} will be invoked followed by the
     * {@link #successfulAuthentication(HttpServletRequest, HttpServletResponse, Authentication)
     * successfulAuthentication} method</li>
     * <li>An <tt>AuthenticationException</tt> occurs during authentication.
     * The {@link #unsuccessfulAuthentication(HttpServletRequest, HttpServletResponse, AuthenticationException)
     * unsuccessfulAuthentication} method will be invoked</li>
     * <li>Null is returned, indicating that the authentication process is incomplete.
     * The method will then return immediately, assuming that the subclass has done any necessary work (such as
     * redirects) to continue the authentication process. The assumption is that a later request will be received
     * by this method where the returned <tt>Authentication</tt> object is not null.
     * </ol>
     */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		  HttpServletRequest request = (HttpServletRequest) req;
	      HttpServletResponse response = (HttpServletResponse) res;
		    /*Indicates whether this filter should attempt to process a login request for the current invocation. */
	      if (!requiresAuthentication(request, response)) {
	            chain.doFilter(request, response);
	            return;
	        }
	      
	      Authentication authResult;
	      
	      try {
	    	    //验证用户名与密码是否正确。不正确就返回一个null
	            authResult = attemptAuthentication(request, response);
	            if (authResult == null) {
	                // return immediately as subclass has indicated that it hasn't completed authentication
	                return;
	            }
	            
	           // judgeVerifyCode(request);//验证登陆的验证码
	            
	            //判断session是否过期、把当前用户放入session（这句是关键）
	            sessionStrategy.onAuthentication(authResult, request, response);
	        } catch(InternalAuthenticationServiceException failed) {
	            logger.error("An internal error occurred while trying to authenticate the user.", failed);
	            unsuccessfulAuthentication(request, response, failed);
	            return;
	        }
	        catch (AuthenticationException failed) {
	            // Authentication failed
	        	
	        	
	        	if(failed.getMessage().equals(ERR_VAILDATECODE)){
	        		request.getSession().setAttribute("error", ERR_VAILDATECODE);
	        	}else if(failed instanceof BadCredentialsException){
	        		request.getSession().setAttribute("error", ERR_USERNAMEANDPASSWORD);
	        	}else if(failed instanceof SessionAuthenticationException){
	        		request.getSession().setAttribute("error", failed.getMessage());
	        	}
	        	
	            unsuccessfulAuthentication(request, response, failed);
	            return;
	        }

	        // Authentication success
	        if (continueChainBeforeSuccessfulAuthentication) {
	            chain.doFilter(request, response);
	        }

	        successfulAuthentication(request, response, chain, authResult);
	      
	}
	
    /**
     * The session handling strategy which will be invoked immediately after an authentication request is
     * successfully processed by the <tt>AuthenticationManager</tt>. Used, for example, to handle changing of the
     * session identifier to prevent session fixation attacks.
     *
     * @param sessionStrategy the implementation to use. If not set a null implementation is
     * used.默认的是：NullAuthenticatedSessionStrategy
     *  如果要实现Session的防固化攻击，并发控制必须要为sessionStrategy注入ConcurrentSessionControlStrategy
     */
    public void setSessionAuthenticationStrategy(SessionAuthenticationStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }
	
    private void judgeVerifyCode(HttpServletRequest request){
     	 String validateCode_index = obtainVerifycode(request);
   	    
     	 String verifycode="";
     	 try{
     	 verifycode = request.getSession().getAttribute("ValidateCode").toString();
     	   }catch(NullPointerException e){
     		 verifycode="";
     		 throw new ErrorVerifycodeException(ERR_VAILDATECODE);
     	 }
     	 if(!validateCode_index.toUpperCase().equalsIgnoreCase(verifycode)){
	         throw new ErrorVerifycodeException(ERR_VAILDATECODE);
		
     	 }
		
	}
	
	/**
	 * 从登陆请求中获取登陆密码，并且解密成铭文
	 * @param request
	 * @return
	 */
	protected String obtainPassword(HttpServletRequest request) {
	    	String password = "" ;
	    	try {  
	    		String password_form = request.getParameter(passwordParameter);
	 		  /*  String key = request.getParameter(SPRING_SECURITY_ENCRYPT_KEY);
	 	    	String iv  = request.getParameter(SPRING＿SECURITY_ENCRYPT_IV);
				password = MyAesUtil.decodeAesByCBC(password_form, key, iv);*/
			    password = password_form ;
	    	} catch (Exception e) {
				password = "" ;
				e.printStackTrace();
			}
		return password;
	}
    /**
     * 从登陆请求中获取用户名，并且解密成铭文
     * @param request
     * @return
     */
	protected String obtainUsername(HttpServletRequest request) {
	    	String username = "" ;
	    	try {
	    		String username_form = request.getParameter(usernameParameter);
			   /* String key = request.getParameter(SPRING_SECURITY_ENCRYPT_KEY).toString();
		    	String iv  = request.getParameter(SPRING＿SECURITY_ENCRYPT_IV).toString();
				username = MyAesUtil.decodeAesByCBC(username_form, key, iv);*/
	    		username = username_form ;
	    	} catch (Exception e) {
				username = "" ;
				e.printStackTrace();
			}
		return username;
	}
	/**
	 * 从请求中获取验证码，并解析成明文
	 * @param request
	 * @return
	 */
	protected String obtainVerifycode(HttpServletRequest request) {
    	String verifycode = "" ;
    	try {  
    		String verifycode_form = request.getParameter(SPRING_SECURITY_FORM_VERIFYCODE);
 		   /* String key = request.getParameter(SPRING_SECURITY_ENCRYPT_KEY);
 	    	String iv  = request.getParameter(SPRING＿SECURITY_ENCRYPT_IV);
			verifycode = MyAesUtil.decodeAesByCBC(verifycode_form, key, iv);*/
		} catch (Exception e) {
			verifycode = "" ;
			e.printStackTrace();
		}
	     return verifycode;
     }
	 /**
     * Provided so that subclasses may configure what is put into the authentication request's details
     * property.
     *
     * @param request that an authentication request is being created for
     * @param authRequest the authentication request object that should have its details set
     */
	 protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
	        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	    }
	  
	public final String getUsernameParameter() {
		return usernameParameter;
	}

	public void setUsernameParameter(String usernameParameter) {
		this.usernameParameter = usernameParameter;
	}

	public final String getPasswordParameter() {
		return passwordParameter;
	}

	public void setPasswordParameter(String passwordParameter) {
		this.passwordParameter = passwordParameter;
	}

	public boolean isPostOnly() {
		return postOnly;
	}

	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}




	@Override
	public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
		// TODO Auto-generated method stub
		super.setAuthenticationSuccessHandler(successHandler);
	}

	@Override
	public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
		// TODO Auto-generated method stub
		super.setAuthenticationFailureHandler(failureHandler);
	}

	@Override
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		// TODO Auto-generated method stub
		super.setAuthenticationManager(authenticationManager);
	}



	
	
    
	



	
	
	
}
