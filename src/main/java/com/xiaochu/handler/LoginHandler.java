package com.xiaochu.handler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiaochu.entity.Employee;
import com.xiaochu.service.LoginService;
@RequestMapping(value={"/sm/"})
@Controller
public class LoginHandler {
	@Autowired
	private LoginService loginService ;
	
	@RequestMapping(value={"judgeLogin"})
	public ModelAndView judgeLogin(String username,String password) throws Exception{
		ModelAndView mv = new ModelAndView();
		Employee emp = loginService.validateLogin(username, password);
		if(null==emp){
			mv.setViewName("index");
			mv.addObject("error", "用户或者密码错误");
		}else{
			mv.setViewName("sm/login/main");
			mv.addObject("emp", emp);
		}
		return mv ;
	}
	@RequestMapping(value={"menu"})
    @ResponseBody
    public List<Map<String,Object>> getMenus(String node)throws Exception{
    	return loginService.getMenus(node) ;
    }
}
