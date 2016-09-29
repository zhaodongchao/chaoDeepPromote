package com.securitys.access;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.web.FilterInvocation;

import com.securitys.userdetails.Authority;
import com.securitys.userdetails.Role;
import com.xiaochu.service.UserEntityService;

/**
 * @author zhaodongchao 2016年9月15日
 */
public class CustomSecurityMetadataSource implements SecurityMetadataSource {
	@Autowired
	private UserEntityService userEntityService;
    /**
     * 提取数据库中所有的权限数据，key为该资源对应的uri,value为访问该资源所需要的角色集合
     */
	private Map<String, Collection<ConfigAttribute>> resourceMap = null;

	private void loadResourceFromDataBase() throws Exception {
		/*
		 * 应当是资源url为key， 角色名称为value。角色名称就是那些以ROLE_为前缀的值
		 */
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

		List<Role> roleList = userEntityService.listRoles();
		for (Role role : roleList) {
			ConfigAttribute configAttrbute = new SecurityConfig(role.getRoleCode());
			Set<Authority> authoritys = role.getAuthoritys();
			/*
			 * 判断资源文件和权限的对应关系，如果已经存在相关的资源url，
			 * 则要通过该url为key提取出权限集合，否则将权限增加到权限集合中
			 *  index.jsp  ROLE_AdMIN 
			 *  index.jsp  ROLE_MAN 
			 * map<index.jsp, [ROLE_AdMIN,ROLE_MAN]>
			 * 
			 */
			for (Authority authority : authoritys) {
				String uri = authority.getOperationUri();// 获取资源的uri

				if (resourceMap.containsKey(uri)) {
					Collection<ConfigAttribute> collects = resourceMap.get(uri);
					collects.add(configAttrbute);
					resourceMap.put(uri, collects);
				} else {
					Collection<ConfigAttribute> newCollects = new ArrayList<>();
					newCollects.add(configAttrbute);
					resourceMap.put(uri, newCollects);
				}
			}
		}
	}

	/*
	 * 根据用户访问的uri，加载该uri所需要角色列表 Object object:uri地址
	 * return 返回访问该URI所需要的角色集合
	 * @see org.springframework.security.access.SecurityMetadataSource#getAttributes(
	 * java.lang.Object)
	 * 
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// 最初请求的uri格式:/**/index.jsp
		// object 是一个URL ,为用户请求URL
		String url = ((FilterInvocation) object).getRequestUrl().trim();

		if ("/".equals(url)) {
			return null;
		}
		int firstQuestionMarkIndex = url.indexOf(".");
		// 判断请求是否带有参数 如果有参数就去掉后面的后缀和参数(/index.do --> /index)
		// 如果后缀是.jsp或者.html就不做修改
		if (firstQuestionMarkIndex != -1) {
            if(!url.endsWith("jsp")&&!url.endsWith("html")){
            	url = url.substring(0, firstQuestionMarkIndex);
			}
		}
		//最终形成的uri 格式:/index
    	Iterator<String> iterator = resourceMap.keySet().iterator();
		// 取到请求的URL后与上面取出来的资源做比较
		while (iterator.hasNext()) {
			String resURL = iterator.next().trim();
			if (url.equals(resURL)) {
				//获得该uri所需要的角色列表
				return resourceMap.get(resURL);
			}
		}
		return null;
	}

	/*
	 * 
	 * If available, returns all of the {@code ConfigAttribute}s defined by the
	 * implementing class. This is used by the {@link
	 * AbstractSecurityInterceptor} to perform startup time validation of each
	 * {@code ConfigAttribute} configured against it.
	 *
	 * @return the {@code ConfigAttribute}s or {@code null} if unsupported
	 */
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see
	 * org.springframework.security.access.SecurityMetadataSource#supports(java.
	 * lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
	/**
	 * 自定义方法，这个类放入到Spring容器后，web容器初始化完成后，就会执行 指定的init方法，从数据库中读取资源，放入map里面
	 *  Only one method can be annotated with this annotation
	 */
	@PostConstruct
    private void init() throws Exception{
    	this.loadResourceFromDataBase();
    }
}
