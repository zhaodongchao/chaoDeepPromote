package com.base.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author 赵东朝
 * 2015年11月29日
 */
public abstract class BaseDao {
	@Autowired
    private SessionFactory sessionFactory ;
	public Session getSession() throws Exception{
		return sessionFactory.getCurrentSession();
	}
}
