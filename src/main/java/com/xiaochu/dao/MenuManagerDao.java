package com.xiaochu.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.xiaochu.entity.MenuEntity;

/**
 * @author zhaodongchao
 * 2016年9月22日
 */
@Repository
public class MenuManagerDao extends BaseDao{
    public List<Map<String,Object>> listMenus()throws Exception{
    	String sql = "select cm.menu_no ,"+
					      " cm.menu_name,"+
					      " cm.menu_uri,"+
					      " cm.menu_level,"+
					      " cm.is_leaf ,"+
					      " TO_CHAR(cm.create_time,'yyyy-mm-dd hh24:mi:ss') as create_time,"+
					      " cm.menu_type,"+
					      " cm.parent_no,"+
					      " cmp.menu_name as parent_name "+
					      " from hr.cf_menu cm "+
					      " left join hr.cf_menu cmp on cm.parent_no = cmp.menu_no "+
					      " order by cm.menu_level " ;
    	return getSession().createSQLQuery(sql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
    }
    public int addMenu(MenuEntity menu)throws Exception{
    	String sql = "insert into hr.cf_menu (menu_no,menu_name,menu_type,menu_uri,menu_level,is_leaf,create_time,parent_no) "+
                           " values (sys_guid(),:menuName,:menuUri,:menuLevel,:isLeaf,sysdate,:parentNo)" ;
    	return getSession().createSQLQuery(sql).setParameter("menuName", menu.getMenuName())
    			                               .setParameter("menuType", menu.getMenuType())
    			                               .setParameter("menuUri", menu.getMenuUri())
    			                               .setParameter("menuLevel", menu.getMenuLevel())
    			                               .setParameter("isLeaf", menu.getIsLeaf())
    			                               .setParameter("parentNo", menu.getParent().getMenuId())
    			                               .executeUpdate();
    }
    public int updateMenu(MenuEntity menu)throws Exception{
    	String sql = "UPDATE hr.cf_menu cm set cm.menu_type=:menuType,cm.menu_level=:menuLevel,cm.is_leaf=:isLeaf,cm.parent_no=:parentId "+
    			     "       WHERE cm.menu_no=:menuId" ;
    	
    	return getSession().createSQLQuery(sql).setParameter("menuType", menu.getMenuType())
    			                               .setParameter("menuLevel", menu.getMenuLevel())
    			                               .setParameter("isLeaf", menu.getIsLeaf())
    			                               .setParameter("parentId", menu.getParent().getMenuId())
    			                               .setParameter("menuId", menu.getMenuId()).executeUpdate();
    }
}
