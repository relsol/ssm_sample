package com.ssm.core.workflow.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssm.core.workflow.utils.WorkflowUtils;

@Service
@Transactional(readOnly = true)
public class AbstractWorkFlowService {

	@Autowired
	protected ProcessEngine processEngine;

	@Autowired
	protected RuntimeService runtimeService;

	@Autowired
	protected RepositoryService repositoryService;

	@Autowired
	protected ManagementService managementService;

	@Autowired
	protected HistoryService historyService;

	@Autowired
	protected TaskService taskService;

	private static final Logger logger = LoggerFactory.getLogger(AbstractWorkFlowService.class);

	/**
	 * 启动工作流
	 * 
	 * @param processDefinitionKey
	 *            key of process definition, cannot be null.
	 * @param processDefinitionKey
	 * @param businessKey
	 * @param variableMap
	 * @return {@code ProcessInstance}
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ProcessInstance startWorkflow(String processDefinitionKey, String businessKey,
			Map<String, Object> variableMap) {
		ProcessInstance instance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey,
				variableMap);
		logger.info("启动工作流程：the key is {}.", processDefinitionKey);
		return instance;
	}
	
	public boolean isStartProcessInstance(String instanceBusinessKey) {
		long instanceCount = this.runtimeService.createProcessInstanceQuery()
				.processInstanceBusinessKey(instanceBusinessKey).count();
		return instanceCount > 0;
	}

	/**
	 * 挂起流程
	 * @param processInstanceById
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void suspendProcessInstanceById(String processInstanceById) {
		this.runtimeService.suspendProcessInstanceById(processInstanceById);
	}
	
	/**
	 * 激活流程
	 * 
	 * @param processInstanceId
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void activateProcessInstanceById(String processInstanceId) {
		this.runtimeService.activateProcessInstanceById(processInstanceId);
	}
	

	/**
	 * 查询任务实例
	 * 
	 * @param processDefinitionKey
	 * @param businessKey
	 * @return
	 */
	public ProcessInstance findProcessInstance(String processDefinitionKey, String businessKey) {
		return this.runtimeService.createProcessInstanceQuery()
				.processInstanceBusinessKey(businessKey, processDefinitionKey).singleResult();
	}

	/**
	 * 查询任务节点
	 * 
	 * @param processInstanceId
	 * @return
	 */
	public Task findByProcessInstanceId(String processInstanceId) {
		return this.taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
	}

	protected ProcessInstance getProcessInstanceById(String processInstanceId) {
		return runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
	}

	protected ProcessDefinition getProcessDefinitionById(String processDefinitionId) {
		return repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
	}

	/**
	 * 查询任务
	 * 
	 * @param taskId
	 * @return
	 */
	public Task findByTaskId(String taskId) {
		return taskService.createTaskQuery().taskId(taskId).singleResult();
	}

	/**
	 * 签收任务
	 * 
	 * @param taskId
	 *            任务ID
	 * @param user
	 *            领取任务，格式为：用户ID:用户姓名
	 * @return Task 任务实例
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Task claimTask(String taskId, String user) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if (task == null) {
			throw new RuntimeException("相关任务实例未找到!");
		}
		taskService.claim(taskId, user);
		return task;
	}

	/**
	 * 完成任务
	 * 
	 * @param taskId
	 *            任务ID
	 * @return Task 任务实例
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Task completeTask(String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if (task == null) {
			throw new RuntimeException("相关任务实例未找到!");
		}
		taskService.complete(taskId);
		return task;
	}

	/**
	 * 签收并完成任务
	 * 
	 * @param idTask
	 * @param user
	 * @return Task 任务实例
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Task claimAndComplateTask(String idTask, String user) {
		Task task = taskService.createTaskQuery().taskId(idTask).singleResult();
		if (task == null) {
			throw new RuntimeException("相关任务实例未找到!");
		}
		taskService.claim(idTask, user);
		taskService.complete(idTask);
		return task;
	}

	/**
	 * 签收并完成任务
	 * 
	 * @param idTask
	 *            任务ID
	 * @param user
	 *            用户ID
	 * @param variables
	 *            流程定义中需要的变量
	 * @return Task 任务实例
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Task claimAndComplateTask(String idTask, String user, Map<String, ? extends Object> variables) {
		Task task = taskService.createTaskQuery().taskId(idTask).singleResult();
		if (task == null) {
			throw new RuntimeException("相关任务实例未找到!");
		}
//		taskService.claim(idTask, user); //这个流程已经指定user签收了，无需再签收
		taskService.setVariables(idTask, variables);
		taskService.complete(idTask);
		return task;
	}

	/**
	 * 部署工作流
	 * 
	 * @param exportDir
	 * @param file
	 * @throws IOException
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deploy(String exportDir, MultipartFile file) throws IOException {

		String fileName = file.getOriginalFilename();

		InputStream fileInputStream = file.getInputStream();
		Deployment deployment = null;

		String extension = FilenameUtils.getExtension(fileName);
		if (extension.equals("zip") || extension.equals("bar")) {
			ZipInputStream zip = new ZipInputStream(fileInputStream);
			deployment = repositoryService.createDeployment().addZipInputStream(zip).deploy();
		} else {
			deployment = repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
		}

		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId())
				.list();

		for (ProcessDefinition processDefinition : list) {
			WorkflowUtils.exportDiagramToFile(repositoryService, processDefinition, exportDir);
		}
	}

	/**
	 * 生成流程图或者xml
	 * @param processDefinitionId 流程定义
	 * @param resourceType 资源类型(xml|image)
	 * @return
	 */
	public InputStream doExportDiagram(String processDefinitionId, String resourceType) {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		String resourceName = "";
		if (resourceType.equals("image")) {
			resourceName = processDefinition.getDiagramResourceName();
		} else if (resourceType.equals("xml")) {
			resourceName = processDefinition.getResourceName();
		}
		InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
				resourceName);

		return resourceAsStream;
	}
}
