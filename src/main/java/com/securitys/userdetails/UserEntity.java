package com.securitys.userdetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author zhaodongchao
 * 2016年9月14日
 */
public class UserEntity implements UserDetails{
	private static final long serialVersionUID = -1252834371498415332L;
	private String userId ;
    private String loginName ;
    private String loginPassword ;
    private String realName ;
    private String selfDeclare ;
    private String city ;
    private String address ;
    private String age ;
    private String sex ;
    private String birthday ;
    private String telphone ;
    private String email ;
    private String isLock ;
    private String isExpired ;
    private String createTime ;
    private Integer loginCount ;
    private String lastLoginTime ;
    private String expiredTime ;
    //一个集合roles，初始容量为0
    private Set<Role> roles = new HashSet<Role>();
    //一个权限集合，这个用户所有的权限
    private Collection<? extends GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    
   

	public String getUserId() {
		return userId;
	}

	public String getAddress() {
		return address;
	}

	public String getAge() {
		return age;
	}

	/* 
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getCity() {
		return city;
	}

	public String getEmail() {
		return email;
	}

	public String getIsExpired() {
		return isExpired;
	}

	public String getIsLock() {
		return isLock;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}
  
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(String expiredTime) {
		this.expiredTime = expiredTime;
	}

	/* 
	 *这个方法会在@see 
	 *org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider.additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
     *中调用，用于比较数据库中存的密码与登录界面输入的密码，
     *如果不匹配就会抛出AuthenticationException
	 */
	@Override
	public String getPassword() {
	
		return this.loginPassword;
	}

	public String getRealName() {
		return realName;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public String getSelfDeclare() {
		return selfDeclare;
	}

	public String getSex() {
		return sex;
	}

	public String getTelphone() {
		return telphone;
	}

	/*
	 * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return loginName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((expiredTime == null) ? 0 : expiredTime.hashCode());
		result = prime * result + ((isExpired == null) ? 0 : isExpired.hashCode());
		result = prime * result + ((isLock == null) ? 0 : isLock.hashCode());
		result = prime * result + ((lastLoginTime == null) ? 0 : lastLoginTime.hashCode());
		result = prime * result + ((loginCount == null) ? 0 : loginCount.hashCode());
		result = prime * result + ((loginName == null) ? 0 : loginName.hashCode());
		result = prime * result + ((loginPassword == null) ? 0 : loginPassword.hashCode());
		result = prime * result + ((realName == null) ? 0 : realName.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((selfDeclare == null) ? 0 : selfDeclare.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((telphone == null) ? 0 : telphone.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (authorities == null) {
			if (other.authorities != null)
				return false;
		} else if (!authorities.equals(other.authorities))
			return false;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (expiredTime == null) {
			if (other.expiredTime != null)
				return false;
		} else if (!expiredTime.equals(other.expiredTime))
			return false;
		if (isExpired == null) {
			if (other.isExpired != null)
				return false;
		} else if (!isExpired.equals(other.isExpired))
			return false;
		if (isLock == null) {
			if (other.isLock != null)
				return false;
		} else if (!isLock.equals(other.isLock))
			return false;
		if (lastLoginTime == null) {
			if (other.lastLoginTime != null)
				return false;
		} else if (!lastLoginTime.equals(other.lastLoginTime))
			return false;
		if (loginCount == null) {
			if (other.loginCount != null)
				return false;
		} else if (!loginCount.equals(other.loginCount))
			return false;
		if (loginName == null) {
			if (other.loginName != null)
				return false;
		} else if (!loginName.equals(other.loginName))
			return false;
		if (loginPassword == null) {
			if (other.loginPassword != null)
				return false;
		} else if (!loginPassword.equals(other.loginPassword))
			return false;
		if (realName == null) {
			if (other.realName != null)
				return false;
		} else if (!realName.equals(other.realName))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (selfDeclare == null) {
			if (other.selfDeclare != null)
				return false;
		} else if (!selfDeclare.equals(other.selfDeclare))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (telphone == null) {
			if (other.telphone != null)
				return false;
		} else if (!telphone.equals(other.telphone))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	/* 
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		/*
		 * 表示当前帐号没过期
		 */
		return true;
	}

	/* 
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		/*
		 * 改为true,表示当前账户没锁定
		 */
		return true;
	}

	/* 
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		/*
		 * 设置为true,表示密码没过期
		 */
		return true;
	}

	/* 
	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		/*
		 * 设置为true ,使当前用户可用
		 */
		return true;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setIsExpired(String isExpired) {
		this.isExpired = isExpired;
	}

	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setSelfDeclare(String selfDeclare) {
		this.selfDeclare = selfDeclare;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", loginName=" + loginName + ", loginPassword=" + loginPassword
				+ ", realName=" + realName + ", selfDeclare=" + selfDeclare + ", city=" + city + ", address=" + address
				+ ", age=" + age + ", sex=" + sex + ", birthday=" + birthday + ", telphone=" + telphone + ", email="
				+ email + ", isLock=" + isLock + ", isExpired=" + isExpired + ", createTime=" + createTime
				+ ", loginCount=" + loginCount + ", lastLoginTime=" + lastLoginTime + ", expiredTime=" + expiredTime
				+ ", roles=" + roles + ", authorities=" + authorities + "]";
	}
    
    
}
