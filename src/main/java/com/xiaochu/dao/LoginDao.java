package com.xiaochu.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.springframework.stereotype.Component;

import com.base.dao.BaseDao;
@Component
public class LoginDao extends BaseDao{
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> validateLogin(String username,String password)throws Exception{
		String sql = "SELECT t.employee_id,t.first_name,t.last_name,t.email,t.phone_number,t.hire_date,t.salary FROM employees t WHERE t.first_name =:username" ;
		return (Map<String, Object>) getSession().createSQLQuery(sql).setParameter("username", username).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getMenus(String node)throws Exception{
		String sql = "SELECT cm.menu_no ,cm.menu_name,cm.menu_uri,cm.menu_level,cm.is_leaf,cm.menu_type,cm.ICONCLS"+
				     "  FROM hr.cf_menu cm " +
				     "  WHERE cm.parent_no =:node ";
		return getSession().createSQLQuery(sql).setParameter("node", node).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
	}
}
