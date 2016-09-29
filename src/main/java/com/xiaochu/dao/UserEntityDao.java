package com.xiaochu.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.springframework.stereotype.Component;
import com.base.dao.BaseDao;
/**
 * @author zhaodongchao
 * 2016年9月16日
 */
@Component
public class UserEntityDao extends BaseDao{
	 /**
	  * 查询系统中所有的角色
	  * @return
	  * @throws Exception
	  */
     public List<Map<String,String>> listRoles()throws Exception{
    	 String sql = "select cr.role_id,cr.role_name,cr.role_code,cr.description from hr.cf_role cr ";
         return getSession().createSQLQuery(sql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
    }
     public List<Map<String,String>> listAutoritysByRoleId(String roleId)throws Exception{
    	 String sql =  "select ca.authority_id,"
			    	 		+ "ca.authority_name,"
			    	 		+ "ca.operation_uri,"
			    	 		+ "ca.descriptions "
			    	 		+ "from hr.cf_authority ca "
							+ "inner join hr.cf_role_authority cra on cra.authority_id = ca.authority_id "
							+ "where cra.role_id =:roleId " ;
    	 return getSession().createSQLQuery(sql).setParameter("roleId", roleId).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
     }
     public Map<String,Object> findUserByName(String username)throws Exception{
    	 String sql = "select cu.user_id,cu.login_name,cu.login_password,cu.real_name,cu.selfdeclare,"
					     +   "cu.city,cu.address,cu.age,cu.sex,cu.birthday,cu.telphone ,cu.email,"
					     +   "cu.create_time,cu.login_count,cu.last_login_time,cu.is_lock,"
					     +   "cu.is_expired,cu.expired_time "
					     +   "from hr.cf_user cu "
					     +   "where cu.login_name=:username" ;
    	 return (Map<String, Object>) getSession().createSQLQuery(sql).setParameter("username", username).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list().get(0);
     }
     public List<Map<String,String>> findRolesByUserId(String userId)throws Exception{
    	 String sql = "  select cr.role_id,cr.role_name,cr.role_code,cr.description "+
				      "     from hr.cf_role cr "+
				      "     inner join hr.cf_user_role cur on cr.role_id = cur.role_id "+
				      "     where cur.user_id =:userId ";
    	 return getSession().createSQLQuery(sql).setParameter("userId", userId).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
     }
}
