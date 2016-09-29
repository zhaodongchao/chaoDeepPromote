package com.wpf.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wpf.bean.WorkflowBean;
import com.wpf.form.TransferBill;
import com.wpf.service.IWorkflowService;
@Service
public class WorkflowServiceImpl implements IWorkflowService {
	@Autowired
	private ProcessEngine processEngine ;
	@Override
	public void saveNewDeploye(MultipartFile deployFile, String fileName) throws Exception {
		RepositoryService repositoryService =  processEngine.getRepositoryService();
		repositoryService.createDeployment().name(fileName).addZipInputStream(new ZipInputStream(deployFile.getInputStream())).deploy();
	}
	@Override
	public List<Map<String,Object>> findDeploymentList() throws Exception {
		List<Map<String,Object>> result = new ArrayList<>();
		List<Deployment> datas =  processEngine.getRepositoryService().createDeploymentQuery().list();
		for(Deployment deployment:datas){
			Map<String,Object> mp = new HashedMap();
			mp.put("id", deployment.getId());
			mp.put("name", deployment.getName());
			mp.put("category", deployment.getCategory());
			mp.put("deploymentTime", deployment.getDeploymentTime());
			mp.put("tenantId", deployment.getTenantId());
			result.add(mp);
		}
		return result ;
	}
	@Override
	public List<Map<String,Object>> findProcessDefinitionList() throws Exception {
		List<Map<String,Object>> result = new ArrayList<>();
		List<ProcessDefinition> datas = processEngine.getRepositoryService().createProcessDefinitionQuery().orderByProcessDefinitionVersion().desc().list();
		for(ProcessDefinition processDefinition:datas){
			Map<String,Object> mp = new HashedMap();
			mp.put("id", processDefinition.getId());
			mp.put("key", processDefinition.getKey());
			mp.put("resourceName", processDefinition.getResourceName());
			mp.put("diagramResourceName", processDefinition.getDiagramResourceName());
			mp.put("version", processDefinition.getVersion());
			mp.put("category", processDefinition.getCategory());
			result.add(mp);
		}
		return result;
	}
	@Override
	public InputStream findImageInputStream(String deploymentId, String imageName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void deleteProcessDefinitionByDeploymentId(String deploymentId) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void saveStartProcess(WorkflowBean workflowBean) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Task> findTaskListByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String findTaskFormKeyByTaskId(String taskId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public TransferBill findTransferBillByTaskId(String taskId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<String> findOutComeListByTaskId(String taskId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void saveSubmitTask(WorkflowBean workflowBean) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Comment> findCommentByTaskId(String taskId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Comment> findCommentByLeaveBillId(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ProcessDefinition findProcessDefinitionByTaskId(String taskId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<String, Object> findCoordingByTask(String taskId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
