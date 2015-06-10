package com.zebone.dip.etl.controller;


/**
 * ETL服务器节点管理
 * 
 * @author 宋俊杰
 *@date 2013-3-5
 */
//@Controller
public class ETLManagerController {
    /*
	private static final Log log = LogFactory.getLog(ETLManagerController.class);
	private static final String REPOSITORY_NAME = "中心资源库";
	private static final String REPOSITORY_LOGINNAME = "admin";
	private static final String REPOSITORY_PASSWORD = "admin";
	
	// etl服务器默认的用户名和密码
	private static final String DEFAULT_USERNAME = "tietl";
	private static final String DEFAULT_PASSWORD = "tietl";
	
	@Resource(name="nodeService")
	private NodeService nodeService;
	@Resource(name="nodeETLService")
	private NodeETLService nodeETLService;
	
	/**
	 * ETL管理页面首页
	 * @param request
	 * @return
	 *
	@RequestMapping("etl/index")
	public String index(HttpServletRequest request){
		List<Node> list = nodeService.getAllNodeInfo();
		String str = JsonUtil.writeValueAsString(list);
		request.setAttribute("list", str);
		return "dip/etl/index";
	}
	
	/**
	 * 部署一个转换到制定的节点上。
	 * 
	 * @param nodeId
	 *            节点ID
	 * @param tranName
	 *            转换名字
	 * @return WebResult的json数据
	 *
	@RequestMapping("etl/addTrans")
	@ResponseBody
	public WebResult addTrans(@RequestParam("nodeId")String nodeId, @RequestParam("name")String tranName) {
		TIETLServerClient client = getTIETLServerClient(nodeId);
		WebResult webResult = new WebResult();
		try {
			TransConfig transConfig = createTransConfig(tranName);
			webResult = client.addTrans(transConfig);
			if(webResult.getResult().equals(WebResult.OK)){
				Integer transId = this.nodeETLService.getTansIdByName(tranName);
				nodeETLService.addTrans(nodeId, transId);
			}
			
		} catch (IOException e) {
			log.error("",e);
			webResult.setResult(WebResult.ERROR);
			webResult.setMessage("连接节点时出现IO异常\n"+e.getMessage());
		}catch(Exception e){
			log.error("",e);
			webResult.setResult(WebResult.ERROR);
			webResult.setMessage("部署转换出现异常\n"+e.getMessage());
		}
		return webResult;
	}
	
	
	/**
	 * 启动一个转换
	 * @param nodeId 节点ID
	 * @param transName 转换名字
	 * @param id 转换的服务器id
	 * @return
	 *
	@RequestMapping("etl/startTrans")
	@ResponseBody
	public WebResult startTrans(@RequestParam("nodeId")String nodeId,@RequestParam("transName")String transName){
		TIETLServerClient client = getTIETLServerClient(nodeId);
		WebResult webResult = new WebResult();
		try {
			webResult = client.startTrans(transName);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			webResult.setResult(WebResult.ERROR);
			webResult.setMessage(e.getMessage());
		} catch (IOException e) {
			log.error("",e);
			webResult.setResult(WebResult.ERROR);
			webResult.setMessage("连接节点时出现IO异常\n"+e.getMessage());
		}catch(Exception e){
			log.error("",e);
			webResult.setResult(WebResult.ERROR);
			webResult.setMessage("启动转换出现异常\n"+e.getMessage());
		}
		
		return webResult;
	}
	

	/**
	 * 获取转换状态
	 * @param nodeId 节点ID
	 * @param transname 节点名字
	 * @return  TransStatus 对象的json字符串，如果发生异常，则返回的是WebResult对象。
	 *
	@RequestMapping("etl/getTransStatus")
	@ResponseBody
	public Object getTransStatus(@RequestParam("nodeId")String nodeId, @RequestParam("transname")String transname){
		TIETLServerClient client = getTIETLServerClient(nodeId);
		TransStatus transStatus = new TransStatus();
		try {
			transStatus = client.getTransStatus(transname);
		} catch (TIETLServerException e) {
			log.error("",e);
			WebResult webResult = new WebResult();
			webResult.setResult(WebResult.ERROR);
			webResult.setMessage(e.getMessage());
			return webResult;
		} catch (IOException e) {
			WebResult webResult = new WebResult();
			webResult.setResult(WebResult.ERROR);
			webResult.setMessage("连接节点时出现IO异常\n"+e.getMessage());
			return webResult;
		}catch(Exception e){
			log.error("",e);
			WebResult webResult = new WebResult();
			webResult.setResult(WebResult.ERROR);
			webResult.setMessage("查询转换信息出现异常\n"+e.getMessage());
			return webResult;
		}
		return transStatus;
	}
	
	/**
	 * 获取转换状态只返回
	 * @param nodeId 节点ID
	 * @param transname 节点名字
	 * @return  TransStatus 对象的json字符串，如果发生异常，则返回的是WebResult对象。
	 *
	@RequestMapping("etl/getTransStatus2")
	@ResponseBody
	public Object getTransStatus2(@RequestParam("nodeId")String nodeId, @RequestParam("transname")String transname){
		TIETLServerClient client = getTIETLServerClient(nodeId);
		TransStatus transStatus = new TransStatus();
		try {
			transStatus = client.getTransStatus(transname);
		} catch (TIETLServerException e) {
			log.error("",e);
			WebResult webResult = new WebResult();
			webResult.setResult(WebResult.ERROR);
			webResult.setMessage(e.getMessage());
			return webResult;
		} catch (IOException e) {
			WebResult webResult = new WebResult();
			webResult.setResult(WebResult.ERROR);
			webResult.setMessage("连接节点时出现IO异常\n"+e.getMessage());
			return webResult;
		}catch(Exception e){
			log.error("",e);
			WebResult webResult = new WebResult();
			webResult.setResult(WebResult.ERROR);
			webResult.setMessage("查询转换信息出现异常\n"+e.getMessage());
			return webResult;
		}
		JsonGrid jsonGrid = new JsonGrid();
		jsonGrid.setData(transStatus.getStepstatuslist());
		jsonGrid.setSuccess("success");
		jsonGrid.setTotal(5);
		return jsonGrid;
	}
	
	/**
	 * 停止一个转换。
	 * @param nodeId 节点ID
	 * @param transname 转换名字
	 * @return
	 *
	@RequestMapping("etl/stopTrans")
	@ResponseBody
	public WebResult stopTrans(@RequestParam("nodeId")String nodeId,@RequestParam("transname")String transname){
		TIETLServerClient client = getTIETLServerClient(nodeId);
		WebResult webResult = new WebResult();
		try {
			webResult = client.stopTrans(transname);
		} catch (Exception e) {
			log.error("",e);
			webResult.setResult(WebResult.ERROR);
			webResult.setMessage("停止转换时出现错误\n"+e.getMessage());
		}
		return webResult;
	}

	/**
	 * 移除转换
	 * @param nodeId
	 * @param transname
	 * @return
	 *
	@RequestMapping("etl/removeTrans")
	@ResponseBody
	public WebResult removeTrans(@RequestParam("nodeId")String nodeId,@RequestParam("transname")String transname){
		TIETLServerClient client = getTIETLServerClient(nodeId);
		WebResult webResult = new WebResult();
		try {
			webResult = client.removeTrans(transname);
			if(webResult.getResult().equals(WebResult.OK)){
				Integer transId = this.nodeETLService.getTansIdByName(transname);
				nodeETLService.removeTrans(nodeId, transId);
			}
		} catch (Exception e) {
			log.error("",e);
			webResult.setResult(WebResult.ERROR);
			webResult.setMessage("移除转换时出现错误\n"+e.getMessage());
		}
		return webResult;
	}
	
	/**
	 * 增加一个任务
	 * @param nodeId 节点ID。任务要添加到此节点。
	 * @param jobName 任务名字
	 * @return
	 *
	@RequestMapping("etl/addJob")
	@ResponseBody
	public WebResult addJob(@RequestParam("nodeId")String nodeId, @RequestParam("name")String jobName){
		TIETLServerClient client = this.getTIETLServerClient(nodeId);
		JobConfig jobConfig = this.createJobConfig(jobName);
		WebResult webResult = new WebResult();
		try {
			webResult = client.addJob(jobConfig);
			if(webResult.getResult().equals(WebResult.OK)){
				Integer jobId = this.nodeETLService.getJobIdByName(jobName);
				nodeETLService.addJob(nodeId, jobId);
			}
		} catch (IOException e) {
			log.error("",e);
			webResult.setResult(WebResult.ERROR);
			webResult.setMessage("连接节点时出现IO异常\n"+e.getMessage());
		}
		return webResult;
	}
	
	
	/**
	 * 启动一个任务
	 * @param nodeId 节点id
	 * @param jobname 任务名字
	 * @return
	 *
	@RequestMapping("etl/startJob")
	@ResponseBody
	public WebResult startJob(@RequestParam("nodeId")String nodeId,@RequestParam("jobname")String jobname){
		TIETLServerClient client = this.getTIETLServerClient(nodeId);
		WebResult webResult = new WebResult();
		try {
			webResult = client.startJob(jobname);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			webResult.setResult(WebResult.ERROR);
			webResult.setMessage(e.getMessage());
		} catch (IOException e) {
			log.error("",e);
			webResult.setResult(WebResult.ERROR);
			webResult.setMessage("连接节点时出现IO异常\n"+e.getMessage());
		}catch(Exception e){
			log.error("",e);
			webResult.setResult(WebResult.ERROR);
			webResult.setMessage("启动任务出现异常\n"+e.getMessage());
		}
		return webResult;
	}
	
	/**
	 * 查询任务的状态信息
	 * @param nodeId
	 * @param jobname
	 * @return JobStatus 对象的json字符串，如果发生异常，则返回的是WebResult对象。
	 *
	@RequestMapping("etl/getJobStatus")
	@ResponseBody
	public Object getJobStatus(String nodeId, String jobname){
		TIETLServerClient client = getTIETLServerClient(nodeId);
		JobStatus jobStatus = new JobStatus();
		try {
			jobStatus = client.getJobStatus(jobname);
		} catch (TIETLServerException e) {
			log.error("",e);
			WebResult webResult = new WebResult();
			webResult.setResult(WebResult.ERROR);
			webResult.setMessage(e.getMessage());
			return webResult;
		} catch (IOException e) {
			WebResult webResult = new WebResult();
			webResult.setResult(WebResult.ERROR);
			webResult.setMessage("连接节点时出现IO异常\n"+e.getMessage());
			return webResult;
		}catch(Exception e){
			log.error("",e);
			WebResult webResult = new WebResult();
			webResult.setResult(WebResult.ERROR);
			webResult.setMessage("查询任务信息出现异常\n"+e.getMessage());
			return webResult;
		}
		return jobStatus;
	}
	
	/**
	 * 查询任务的状态信息
	 * @param nodeId
	 * @param jobname
	 * @return JobStatus 对象的json字符串，如果发生异常，则返回的是WebResult对象。
	 *
	@SuppressWarnings("unchecked")
	@RequestMapping("etl/getJobStatus2")
	@ResponseBody
	public JsonGrid getJobStatus2(@RequestParam("nodeId")String nodeId, @RequestParam("jobname")String jobname){
		JsonGrid jg = new JsonGrid();
		List data = new ArrayList();
		TIETLServerClient client = getTIETLServerClient(nodeId);
		JobStatus jobStatus = new JobStatus();
		try {
			jobStatus = client.getJobStatus(jobname);
			Map map = new HashMap();
			map.put("jobname", jobStatus.getJobname());
			map.put("status_desc", jobStatus.getStatus_desc());
			map.put("nodeId", nodeId);
			data.add(map);
			jg.setData(data);
		} catch(Exception e){
			log.error("",e);
			jg.setSuccess("false");
			jg.setData(data);
			return jg;
		}
		return jg;
	}
	
	/**
	 * 停止任务
	 * @param nodeId 节点ID
	 * @param jobname 任务名字
	 * @return
	 *
	@RequestMapping("etl/stopJob")
	@ResponseBody
	public WebResult stopJob(@RequestParam("nodeId")String nodeId ,@RequestParam("jobname")String jobname){
		TIETLServerClient client = getTIETLServerClient(nodeId);
		WebResult webResult = new WebResult();
		try {
			webResult = client.stopJob(jobname);
		} catch (Exception e) {
			log.error("",e);
			webResult.setResult(WebResult.ERROR);
			webResult.setMessage("停止任务时出现错误\n"+e.getMessage());
		} 
		return webResult;
	}
	
	/**
	 * 移除任务
	 * @param nodeId 节点ID
	 * @param jobname 节点名字
	 * @return
	 *
	@RequestMapping("etl/removeJob")
	@ResponseBody
	public WebResult removeJob(@RequestParam("nodeId")String nodeId , @RequestParam("jobname")String jobname){
		TIETLServerClient client = getTIETLServerClient(nodeId);
		WebResult webResult = new WebResult();
		try {
			webResult = client.removeJob(jobname);
			if(webResult.getResult().equals(WebResult.OK)){
				Integer jobId = this.nodeETLService.getJobIdByName(jobname);
				nodeETLService.removeJob(nodeId, jobId);
			}
		} catch (Exception e) {
			log.error("",e);
			webResult.setResult(WebResult.ERROR);
			webResult.setMessage("移除任务时出现错误\n"+e.getMessage());
		} 
		return webResult;
	}
	
	/** 
	 * 根据节点ID得到服务器状态。包括每个任务或者转换的状态。
	 * @param nodeId
	 * @return ServerStatus 对象的json字符串，如果发生异常，则返回的是WebResult对象。
	 *
	@RequestMapping("etl/getServerStatus")
	@ResponseBody
	public Object getServerStatus(String nodeId){
		TIETLServerClient client = getTIETLServerClient(nodeId);
		try {
			ServerStatus  serverStatus = client.getServerStatus();
			return serverStatus;
		} catch (IOException e) {
			log.error("连接节点时出现IO异常",e);
			WebResult webResult = new WebResult();
			webResult.setResult(WebResult.ERROR);
			webResult.setMessage("连接节点时出现IO异常\n"+e.getMessage());
			return webResult;
		}
	}
	
	/**
	 * 得到节点下面的任务和转换列表
	 * @param nodeId
	 * @return
	 *
	@SuppressWarnings("unchecked")
	@RequestMapping("etl/getTransAndJob")
	@ResponseBody
	public JsonGrid getTransAndJob(@RequestParam("nodeId")String nodeId){
		TIETLServerClient client = getTIETLServerClient(nodeId);
		JsonGrid jg = new JsonGrid();
		try {
			ServerStatus  serverStatus = client.getServerStatus();
			List<TransStatus> transList = serverStatus.getTransstatuslist();
			List<JobStatus> jobList = serverStatus.getJobstatuslist();
			List result = new ArrayList();
			for(TransStatus ts : transList){
				Map transMap = new HashMap();
				transMap.put("name", ts.getTransname());
				transMap.put("type", "trans");
				transMap.put("typeName", "转换");
				transMap.put("status_desc", ts.getStatus_desc());
				transMap.put("nodeId", nodeId);
				result.add(transMap);
			}
			
			for(JobStatus js : jobList){
				Map jobMap = new HashMap();
				jobMap.put("name", js.getJobname());
				jobMap.put("type", "job");
				jobMap.put("typeName", "任务");
				jobMap.put("status_desc", js.getStatus_desc());
				jobMap.put("nodeId", nodeId);
				result.add(jobMap);
			}
			jg.setData(result);
			
			return jg;
		} catch (IOException e) {
			log.error("连接节点时出现IO异常",e);
			jg.setSuccess("false");
			jg.setData(new ArrayList());
			return jg;
		}
	}
	
	/**
	 * 得到所有的节点。
	 * @return
	 *
	@RequestMapping("etl/getNodeList")
	@ResponseBody
	public List<Node> getNodeList(){
		List<Node> nodeList =  this.nodeService.getAllNodeInfo();
		return nodeList;
	}
	
	/**
	 * 得到未部署的转换和任务。
	 * @return
	 *
	@SuppressWarnings("unchecked")
	@RequestMapping("etl/getTranAndJobForDeploy")
	@ResponseBody
	public String getTranAndJobForDeploy(){
		List<String> transList = this.nodeETLService.getTransForNotDeploy();
		List<String> jobList = this.nodeETLService.getJobForNotDeploy();
		Map<String,List> map = new HashMap<String,List>();
		map.put("job", jobList);
		map.put("trans", transList);
		StringBuilder sb = new StringBuilder();
		sb.append("<option value=''>请选择 </option>");
		String temp = "<option value ='#val'>#name</option>";
		for(String transName : transList){
			String option = temp.replace("#val", "trans_"+transName);
			option = option.replace("#name", transName+"[转换]");
			sb.append(option);
		}
		for(String jobName : jobList){
			String option = temp.replace("#val", "job_"+jobName);
			option = option.replace("#name", jobName+"[任务]");
			sb.append(option);
		}
		return sb.toString();
	}
	
	
	
//private========================================================================	
	
	/**
	 * 创建转换配置信息
	 * @param tranId
	 * @return
	 *
	private TransConfig createTransConfig(String tranName) {
		TransConfig transConfig = new TransConfig();

		Reference transRef = new Reference();
		//设置引用方式。“rep_name”：资源库中的目录和名称；“rep_ref”：资源库中的id。
		transRef.setSpecification_method("rep_name");
		transRef.setName(tranName);
		transRef.setDirectory("/");
		transConfig.setTransRef(transRef);

		TransExecConfig execConfig = new TransExecConfig();
		//TODO 日志级别应该可配置
		execConfig.setLog_level("Basic");
		Repository repository = createRepository();

		execConfig.setRepository(repository);
		transConfig.setTransExecConfig(execConfig);
		return transConfig;
	}
	
	
	/**
	 * 创建一个JobConfig
	 * @param jobName 
	 * @return
	 *
	private JobConfig createJobConfig(String jobName){
		JobConfig jobConfig = new JobConfig();
		Reference transRef = new Reference();
		//设置引用方式。“rep_name”：资源库中的目录和名称；“rep_ref”：资源库中的id。
		transRef.setSpecification_method("rep_name");
		transRef.setName(jobName);
		jobConfig.setJobRef(transRef);
		
		JobExecConfig jobExecConfig = new JobExecConfig();
		jobExecConfig.setLog_level("Basic");
		Repository repository = createRepository();
		jobExecConfig.setRepository(repository);
		jobConfig.setJobExecConfig(jobExecConfig);
		return jobConfig;
	}
	
	
	/**
	 * 创建一个 Repository
	 * @return
	 *
	private Repository createRepository(){
		Repository repository = new Repository();
		repository.setName(REPOSITORY_NAME);
		repository.setLogin(REPOSITORY_LOGINNAME);
		repository.setPassword(TIETLServerUtil.encryptPassword(REPOSITORY_PASSWORD));
		return repository;
	}
	
	/**
	 * 根据节点id。查出此节点的ip地址和对应的etl服务器的
	 * 端口号，用户名，密码，用这些参数创建一个TIETLServerClient
	 * @param nodeId 节点id
	 * @return
	 *
	private TIETLServerClient getTIETLServerClient(String nodeId){
		
		Node node = this.nodeService.getNodeInfoById(nodeId);
//		String nodeIp = "127.0.0.1";
		String nodeIp = node.getNodeIp();
		// TODO Node表需要增加 用户名，密码字段
		String username = null;
		String password = null;
		String portStr = node.getNodePort();
//		Integer port = 8090;
		Integer port = Integer.parseInt(portStr);
		if (StringUtils.isBlank(username)) {
			username = DEFAULT_USERNAME;
		}
		if (StringUtils.isBlank(password)) {
			password = DEFAULT_PASSWORD;
		}
		TIETLServerClient client = new TIETLServerClient(nodeIp, port, username, password);
		return client;
	} 
	*/
}
