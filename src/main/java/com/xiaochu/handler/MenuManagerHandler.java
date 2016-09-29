package com.xiaochu.handler;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.export.entity.ColumnInfo;
import com.xiaochu.entity.MenuEntity;
import com.xiaochu.service.MenuManagerService;

/**
 * @author zhaodongchao
 * 2016年9月22日
 */
@RequestMapping(value={"menu/"})
@Controller
public class MenuManagerHandler {
	@Autowired
    private MenuManagerService menuManagerService;
	@RequestMapping(value={"list"})
	@ResponseBody
	public Map<String,Object> listMenus()throws Exception{
		Map<String,Object> job = new HashedMap();
		job.put("result", menuManagerService.listMenus());
		return job ;
	}
	@RequestMapping(value={"add"})
	@ResponseBody
	public Map<String,Object> addMenus(MenuEntity menu)throws Exception{
		return this.menuManagerService.addMenu(menu) ;
	}
	@RequestMapping(value={"edit"})
	@ResponseBody
	public Map<String,Object> updateMenus(MenuEntity menu)throws Exception{
		return this.menuManagerService.updateMenu(menu) ;
	}
	@RequestMapping(value={"export"})
	@ResponseBody
	public Map<String,String> export2Excel(@RequestParam("listColumnInfo") ArrayList<ColumnInfo> listColumnInfo)throws Exception{
		return this.menuManagerService.export2Excel(listColumnInfo);
	}
}

