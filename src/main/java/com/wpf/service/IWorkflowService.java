package com.wpf.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.web.multipart.MultipartFile;

import com.wpf.bean.WorkflowBean;
import com.wpf.form.TransferBill;

public interface IWorkflowService {
	/**
	 * 部署一个新的流程
	 * @param deployFile 流程部署文件
	 * @param fileName 流程名称
	 * @throws Exception
	 */
	void saveNewDeploye(MultipartFile deployFile, String fileName) throws Exception;
    /**
     * 查询已经部署的流程部署信息集合
     * @return
     * @throws Exception
     */
	List<Map<String,Object>> findDeploymentList()throws Exception;
    /**
     * 查询流程定义信息集合
     * @return
     * @throws Exception
     */
	List<Map<String,Object>> findProcessDefinitionList()throws Exception;

	InputStream findImageInputStream(String deploymentId, String imageName)throws Exception;

	void deleteProcessDefinitionByDeploymentId(String deploymentId)throws Exception;

	void saveStartProcess(WorkflowBean workflowBean)throws Exception;

	List<Task> findTaskListByName(String name)throws Exception;

	String findTaskFormKeyByTaskId(String taskId)throws Exception;

	TransferBill findTransferBillByTaskId(String taskId)throws Exception;

	List<String> findOutComeListByTaskId(String taskId)throws Exception;

	void saveSubmitTask(WorkflowBean workflowBean)throws Exception;

	List<Comment> findCommentByTaskId(String taskId)throws Exception;

	List<Comment> findCommentByLeaveBillId(Long id)throws Exception;

	ProcessDefinition findProcessDefinitionByTaskId(String taskId)throws Exception;

	Map<String, Object> findCoordingByTask(String taskId)throws Exception;
}
