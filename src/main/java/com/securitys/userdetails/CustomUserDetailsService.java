package com.securitys.userdetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.xiaochu.service.UserEntityService;

/**
 * 1 通过用户名获取一个用户完整的信息 -- 程序猿手动实现
 * 2 该用户对象必须实现UserDetails接口
 * 3 此类会被注入到AbstractUserDetailsAuthenticationProvider的实现类里面去，默认为{@link org.springframework.security.authentication.dao.DaoAuthenticationProvider}
 * 4 到此，此类功能结束
 * created by zhaodongchao
 * 下午10:19:51
 *
 */
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserEntityService userEntityService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = null;
		if(username!=null&&!username.equals("")){
			try {
				user = userEntityService.findUserByName(username);
				user.setAuthorities(obtionGrantedAuthorities(user));
			} catch (Exception e) {
				user = null ;
			}
		}
		return user;
	}
    private Collection<? extends GrantedAuthority> obtionGrantedAuthorities(UserEntity user){
    	Set<GrantedAuthority> authoritys = new HashSet<GrantedAuthority>() ;
    	for(Role role : user.getRoles()){
    		authoritys.add(new SimpleGrantedAuthority(role.getRoleCode()));
    	}
    	return authoritys ;
    }
}
