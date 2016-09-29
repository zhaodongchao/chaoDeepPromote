package com.xiaochu.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.securitys.userdetails.Authority;
import com.securitys.userdetails.Role;
import com.securitys.userdetails.UserEntity;
import com.xiaochu.dao.UserEntityDao;

/**
 * @author zhaodongchao
 * 2016年9月16日
 */
@Component
public class UserEntityService {
	 @Autowired
     private UserEntityDao userEntityDao ;
	 public List<Role> listRoles()throws Exception{
		 List<Role> roleList = new ArrayList<>();
		 List<Map<String,String>> roles = userEntityDao.listRoles();
		 for(Map<String,String> role :roles){
			 Role re = new Role();
			 String roleId  = role.get("ROLE_ID")!=null? role.get("ROLE_ID"):"";
			 String roleName = role.get("ROLE_NAME")!=null ? role.get("ROLE_NAME"):"";
			 String roleCode =  role.get("ROLE_CODE")!=null ? role.get("ROLE_CODE"):"";
			 String description = role.get("DESCRIPTION")!=null ? role.get("DESCRIPTION"):"";
			 Set<Authority> authoritySet  = new HashSet<>();
			 re.setRoleId(roleId);
			 re.setRoleName(roleName);
			 re.setRoleCode(roleCode);
			 re.setDescription(description);
			 List<Map<String,String>> authoritys = userEntityDao.listAutoritysByRoleId(roleId);
			 for(Map<String,String> authority : authoritys){
				 Authority ay = new Authority();
				 String authorityId = authority.get("AUTHORITY_ID")!=null? authority.get("AUTHORITY_ID"):"";
				 String authorityName = authority.get("AUTHORITY_NAME")!=null? authority.get("AUTHORITY_NAME"):"";
				 String operationUri = authority.get("OPERATION_URI")!=null? authority.get("OPERATION_URI"):"";
				 String descriptions = authority.get("DESCRIPTIONS")!=null? authority.get("DESCRIPTIONS"):"";
			     ay.setAuthorityId(authorityId);
			     ay.setAuthorityName(authorityName);
			     ay.setOperationUri(operationUri);
			     ay.setDescriptions(descriptions);
			     authoritySet.add(ay);
			 }
			 re.setAuthoritys(authoritySet);
			 roleList.add(re);
		 }
		 return roleList ;
	 }
	 public UserEntity findUserByName(String username)throws Exception{
		 Map<String,Object> userMap = userEntityDao.findUserByName(username);
		 UserEntity user = null;
		 if(!userMap.isEmpty()){
			 user = new UserEntity();
			 String userId = null==userMap.get("USER_ID")?"":userMap.get("USER_ID").toString();
		     String loginName = null==userMap.get("LOGIN_NAME")?"":userMap.get("LOGIN_NAME").toString();
		     String loginPassword =null==userMap.get("LOGIN_PASSWORD")?"":userMap.get("LOGIN_PASSWORD").toString();
		     String realName = null==userMap.get("REAL_NAME")?"":userMap.get("REAL_NAME").toString();
		     String selfDeclare =null==userMap.get("SELFDECLARE")?"":userMap.get("SELFDECLARE").toString();
		     String city = null==userMap.get("CITY")?"":userMap.get("CITY").toString();
		     String address =null==userMap.get("ADDRESS")?"":userMap.get("ADDRESS").toString();
		     String age = null==userMap.get("AGE")?"":userMap.get("AGE").toString();
		     String sex =null==userMap.get("SEX")?"":userMap.get("SEX").toString();
		     String birthday =null==userMap.get("BIRTHDAY")?"":userMap.get("BIRTHDAY").toString() ;
		     String telphone =null==userMap.get("TELPHONE")?"":userMap.get("TELPHONE").toString();
		     String email =null==userMap.get("EMAIL")?"":userMap.get("EMAIL").toString();
		     String isLock =null==userMap.get("IS_LOCK")?"":userMap.get("IS_LOCK").toString();
		     String isExpired = null==userMap.get("IS_EXPIRED")?"":userMap.get("IS_EXPIRED").toString();
		     String createTime =null==userMap.get("CREATE_TIME")?"":userMap.get("CREATE_TIME").toString();
		     Integer loginCount =null==userMap.get("LOGIN_COUNT")?-1:Integer.parseInt(userMap.get("LOGIN_COUNT").toString());
		     String lastLoginTime =null==userMap.get("LAST_LOGIN_TIME")?"":userMap.get("LAST_LOGIN_TIME").toString();
		     String expiredTime  =null==userMap.get("EXPIRED_TIME")?"":userMap.get("EXPIRED_TIME").toString();
			 user.setUserId(userId);
			 user.setLoginName(loginName);
			 user.setLoginPassword(loginPassword);
			 user.setRealName(realName);
			 user.setSelfDeclare(selfDeclare);
			 user.setCity(city);
		     user.setAddress(address);
		     user.setAge(age);
		     user.setSex(sex);
		     user.setBirthday(birthday);
		     user.setTelphone(telphone);
		     user.setEmail(email);
		     user.setIsLock(isLock);
		     user.setIsExpired(isExpired);
		     user.setCreateTime(createTime);
		     user.setLoginCount(loginCount);
		     user.setLastLoginTime(lastLoginTime);
		     user.setExpiredTime(expiredTime);
		     Set<Role> roleSet = new HashSet<>();
		     List<Map<String,String>>  roles = userEntityDao.findRolesByUserId(userMap.get("USER_ID").toString());
			 for(Map<String,String> role :roles){
				 Role re = new Role();
				 String roleId  = role.get("ROLE_ID")!=null? role.get("ROLE_ID"):"";
				 String roleName = role.get("ROLE_NAME")!=null ? role.get("ROLE_NAME"):"";
				 String roleCode =  role.get("ROLE_CODE")!=null ? role.get("ROLE_CODE"):"";
				 String description = role.get("DESCRIPTION")!=null ? role.get("DESCRIPTION"):"";
				 re.setRoleId(roleId);
				 re.setRoleName(roleName);
				 re.setRoleCode(roleCode);
				 re.setDescription(description);
				 roleSet.add(re);
			 }
		     user.setRoles(roleSet);
		 }
		 
	     
	    return user ;
	 }
}
