package com.xiaochu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaochu.dao.LoginDao;
import com.xiaochu.entity.Employee;

@Service
public class LoginService {
	@Autowired   
    private LoginDao loginDao ;
	public Employee validateLogin(String username,String password) throws Exception{
		Employee employee = null;
		Map<String,Object> emp = null ;
		try{
		     emp = loginDao.validateLogin(username, password);
		   }catch(Exception e){
			   
		   }
		if(!emp.isEmpty()){
			employee = new Employee();
			employee.setId(emp.get("EMPLOYEE_ID").toString());
			employee.setFirstName(emp.get("FIRST_NAME").toString());
			employee.setLastName(emp.get("LAST_NAME").toString());
			employee.setEmail(emp.get("EMAIL").toString());
			employee.setHireDate(emp.get("HIRE_DATE").toString());
			employee.setPhoneNumber(emp.get("PHONE_NUMBER").toString());
			employee.setSalary(Double.parseDouble(emp.get("SALARY").toString()));
		}
		return employee ;
	}
	public List<Map<String,Object>> getMenus(String node)throws Exception{
		List<Map<String,Object>> datas = loginDao.getMenus(node);
		List<Map<String,Object>> menus = new ArrayList<Map<String,Object>>();
		Map<String,Object> mp = null ;
		for(Map<String,Object> data:datas){
			mp = new HashMap<String, Object>();
			mp.put("id", data.get("MENU_NO"));
			mp.put("text", data.get("MENU_NAME"));
			mp.put("level",data.get("MENU_LEVEL"));
			mp.put("leaf", data.get("IS_LEAF"));
			mp.put("menuUri", data.get("MENU_URI"));
			mp.put("menuType", data.get("MENU_TYPE"));
			mp.put("icon", data.get("ICONCLS"));
			menus.add(mp);
		}
		return menus;
	}
}
