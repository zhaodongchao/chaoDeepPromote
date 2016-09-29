package com.xiaochu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.export.entity.ColumnInfo;
import com.base.export.implement.ClsExport;
import com.xiaochu.dao.MenuManagerDao;
import com.xiaochu.entity.MenuEntity;
/**
 * @author zhaodongchao
 * 2016年9月22日
 */
@Service
public class MenuManagerService {
	@Autowired
	private MenuManagerDao menuManagerDao ;
	@Autowired
	private ClsExport clsExport ;
    public List<Map<String,Object>> listMenus()throws Exception{
    	return this.menuManagerDao.listMenus();
    }
    public Map<String,Object> addMenu(MenuEntity menu)throws Exception{
    	Map<String,Object> jb = new HashedMap();
    	try {
			this.menuManagerDao.addMenu(menu);
			jb.put("success", true);
			jb.put("msg", "成功添加一个菜单！");
		} catch (Exception e) {
			jb.put("success", false);
			jb.put("msg", e.getMessage());
		}
    	return jb;
    }
    public Map<String,Object> updateMenu(MenuEntity menu)throws Exception{
    	Map<String,Object> jb = new HashedMap();
    	try {
			this.menuManagerDao.updateMenu(menu);
			jb.put("success", true);
			jb.put("msg", "修改成功！");
		} catch (Exception e) {
			jb.put("success", false);
			jb.put("msg", e.getMessage());
		}
    	return jb;
    }
    public Map<String,String> export2Excel(List<ColumnInfo> listColumnInfo)throws Exception{
    	List<Object[]> newlist = new ArrayList<Object[]>();
    	List<Map<String,Object>> list = this.listMenus();
    	for (Map<String, Object> map : list) {
			Object[] newobject = new Object[7];
			String menu_name = (map.get("MENU_NAME") != null ? map.get("MENU_NAME").toString(): "");
			String  menu_level = (map.get("MENU_LEVEL") != null ? map.get("MENU_LEVEL").toString() :"");
			String menu_uri = (map.get("MENU_URI") != null ? map.get("MENU_URI").toString() : "");
			String is_leaf = (map.get("IS_LEAF") != null ? map.get("IS_LEAF").toString() : "");
			String create_time = (map.get("CREATE_TIME") != null ? map.get("CREATE_TIME").toString() : "");
			String menu_type = (map.get("MENU_TYPE") != null ? map.get("MENU_TYPE").toString() : "");
			String parent_name = (map.get("PARENT_NAME") != null ? map.get("PARENT_NAME").toString() : "");
			newobject[0] = menu_name;
			newobject[1] = menu_level;
			newobject[2] = menu_uri;
			newobject[3] = is_leaf;
			newobject[4] = create_time;
			newobject[5] = menu_type;
			newobject[6] = parent_name;
			newlist.add(newobject);
		}
		int index = 0;
		for (ColumnInfo info : listColumnInfo) {
			info.index = index++;
		}
		clsExport.getDataBolckInfo().TOTALLINE = true;
		clsExport.setTitleName("菜单管理表");
		clsExport.setColumInfoList(listColumnInfo);
		clsExport.setResultList(newlist);
		clsExport.setFILE_FIRSTNAME("菜单管理表");
		clsExport.Export();
		String fileAddress = clsExport.getFilePathOfSave();
		if (fileAddress.equals("")) {
			throw new Exception("文件初始化出错或文件路径不存在");
		} else {
			Map<String,String> mp = new HashedMap();
			mp.put("fileAddress", fileAddress);
			return mp;
		}
    }
}
