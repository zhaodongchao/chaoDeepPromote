package com.wpf.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.wpf.service.IWorkflowService;

import net.sf.json.JSONObject;

@RequestMapping(value = { "workflowManager/" })
@Controller
public class WorkFlowHandler {
	@Autowired
    private IWorkflowService workflowService ;
	/**
	 * 部署新流程
	 * @return
	 */
	@RequestMapping(value = { "newDeploy" },method={RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> newdeploy(MultipartFile deployFile, String fileName) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("filename", deployFile.getOriginalFilename());
		map.put("size", deployFile.getSize() / (1024.0 * 1024) + "M");
		try {
			if(map.get("filename").toString().endsWith(".zip")){
				workflowService.saveNewDeploye(deployFile, fileName);
				map.put("success", true);
			}
		} catch (Exception e) {
			map.put("success", false);
			map.put("errorInfo", e.getMessage().toString());
		}
		return map;
	}
   @RequestMapping(value = { "findDeployments" },method={RequestMethod.GET})
   @ResponseBody
   public Map<String,Object> findDeploymentList() throws Exception{
	   Map<String,Object> result = new HashMap<>();
	   List<Map<String,Object>> datas = workflowService.findDeploymentList() ;
	   result.put("iTotalRecords", datas.size());
	   result.put("iTotalDisplayRecords", datas.size());
	   result.put("data", datas); 
	   result.put("sEcho", 1);
	   return result;
   }
   @RequestMapping(value = { "findProcessDefinitions" },method={RequestMethod.GET})
   @ResponseBody
   public Map<String,Object> findProcessDefinitionList()throws Exception{
	   Map<String,Object> result = new HashMap<>();
	   List<Map<String,Object>> datas = workflowService.findProcessDefinitionList();
	   result.put("iTotalRecords", datas.size());
	   result.put("iTotalDisplayRecords", datas.size());
	   result.put("data", datas); 
	   result.put("sEcho", 1);
	   return result;
   }
	/**
	 * 删除部署信息
	 */
	public String delDeployment() {
		return "list";
	}

	/**
	 * 查看流程图
	 */
	public String viewImage() {
		return null;
	}

	// 启动流程
	public String startProcess() {
		return "listTask";
	}

	/**
	 * 任务管理首页显示
	 * 
	 * @return
	 */
	public String listTask() {
		return "task";
	}

	/**
	 * 打开任务表单
	 */
	public String viewTaskForm() {
		return "viewTaskForm";
	}

	// 准备表单数据
	public String audit() {
		return "taskForm";
	}

	/**
	 * 提交任务
	 */
	public String submitTask() {
		return "listTask";
	}

	/**
	 * 查看当前流程图（查看当前活动节点，并使用红色的框标注）
	 */
	public String viewCurrentImage() {
		return "image";
	}

	// 查看历史的批注信息
	public String viewHisComment() {
		return "viewHisComment";
	}
}
