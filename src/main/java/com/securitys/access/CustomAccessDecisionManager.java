package com.securitys.access;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author zhaodongchao
 * 2016年9月15日
 */
public class CustomAccessDecisionManager implements AccessDecisionManager {

   /* 
    * 核心
	* 该方法：需要比较权限和权限配置  object参数是一个 URL, 同一个过滤器该url对应的权限配置被传递过来.  
    * 查看authentication是否存在权限在configAttributes中  
    * 如果没有匹配的权限, 扔出一个拒绝访问的异常  
	 * @see org.springframework.security.access.AccessDecisionManager#decide(org.springframework.security.core.Authentication, java.lang.Object, java.util.Collection)
	 */
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {

		if (null == configAttributes) {
			return;
		}
		for(ConfigAttribute configAttribute : configAttributes){//遍历访问该自然所需要的权限及ROLE_**
			String needRole = configAttribute.getAttribute();
			Collection<? extends GrantedAuthority> currAuthority = authentication.getAuthorities() ;//获取当前用户的所有权限
			for(GrantedAuthority grantedAuthority:currAuthority){
			/*	if("ROLE_ANONYMOUS".equals(grantedAuthority.getAuthority())){
					return ;
				}*/
				//如果访问该资源的角色中有一个与该用户的角色相符合的，就放行，否则抛出一个异常
				if(needRole.trim().equals(grantedAuthority.getAuthority())){
					return ;
				  }
			  }
		   }
    
		        //该用户没有权限访问该资源
				throw new AccessDeniedException("Access Denied");
	}

	/* 
	 * 在启动的时候被 AbstractSecurityInterceptor调用，  
     * 来决定AccessDecisionManager 是否可以执行传递ConfigAttribute。
	 */
	@Override
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	/* 
	 * 被安全拦截器实现调用， 安全拦截器将显示的AccessDecisionManager支持安全对象的类型。     
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

}
