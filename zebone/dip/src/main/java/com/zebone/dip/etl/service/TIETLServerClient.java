package com.zebone.dip.etl.service;


/**
 * ti_etl-server-api.jar 中已经提供此类。这里重写是为了修改方法的返回值。
 * 包中提供的返回值对象并没有实现Serializable接口。同时重写了返回值对象
 * 目的是为了实现这个接口，一遍可以转换成json格式数据。
 * @author 宋俊杰
 *
 */
public class TIETLServerClient {
    /*
	private XStream xstream = null;
	private SimpleHTTPClient client = null;

	public TIETLServerClient(String hostname) {
		if ((hostname == null) || (hostname.length() == 0)) {
			throw new IllegalArgumentException("参数hostname为null或空串");
		}
		this.client = new SimpleHTTPClient(hostname);

		NoNameCoder nameCoder = new NoNameCoder();
		this.xstream = new XStream(new DomDriver("UTF-8", nameCoder));
		this.xstream.processAnnotations(WebResult.class);
		this.xstream.processAnnotations(TransStatus.class);
		this.xstream.processAnnotations(ServerStatus.class);
		this.xstream.processAnnotations(JobConfig.class);
		this.xstream.processAnnotations(TransConfig.class);
		this.xstream.processAnnotations(JobStatus.class);
		this.xstream.processAnnotations(StepStatus.class);
	}

	public TIETLServerClient(String hostname, int port, String username,String password) {
		this(hostname);
		if ((port < 0) || (port > 65535)) {
			throw new IllegalArgumentException("参数port小于0或大于65535");
		}
		setPort(port);
		if ((username == null) || (username.length() == 0)) {
			throw new IllegalArgumentException("参数username为null或空串");
		}
		setUsername(username);
		if ((password == null) || (password.length() == 0)) {
			throw new IllegalArgumentException("参数password为null或空串");
		}
		setPassword(password);
	}

	public int getPort() {
		return this.client.getPort();
	}

	public void setPort(int port) {
		this.client.setPort(port);
	}

	public String getUsername() {
		return this.client.getUsername();
	}

	public void setUsername(String username) {
		this.client.setUsername(username);
	}

	public String getPassword() {
		return this.client.getPassword();
	}

	public void setPassword(String password) {
		this.client.setPassword(password);
	}

	public ServerStatus getServerStatus() throws IOException {
		ServerStatus serverStatus = null;
		String xml = null;

		xml = this.client.executeHTTPGet("/tietl/status/?xml=Y");
		serverStatus = (ServerStatus) this.xstream.fromXML(xml);
		return serverStatus;
	}

	public WebResult stopServer() throws IOException {
		WebResult webResult = null;
		String xml = null;

		xml = this.client.executeHTTPGet("/tietl/stopServer/?xml=Y");
		webResult = (WebResult) this.xstream.fromXML(xml);
		return webResult;
	}

	public WebResult addTrans(TransConfig transConfig) throws IOException {
		WebResult webResult = null;
		String service = "/tietl/addTrans/?xml=Y";

		String xml = this.client.executeHTTPPost(service, this.xstream
				.toXML(transConfig));

		webResult = (WebResult) this.xstream.fromXML(xml);
		return webResult;
	}

	public WebResult removeTrans(String transname)
			throws IllegalArgumentException, IOException {
		WebResult webResult = null;
		String service = null;

		if ((transname != null) && (transname.length() != 0)) {
			service = "/tietl/removeTrans/?xml=Y&name="
					+ URLEncoder.encode(transname, "UTF-8");
		} else {
			throw new IllegalArgumentException("参数transname为null或空串");
		}
		String xml = this.client.executeHTTPGet(service);
		webResult = (WebResult) this.xstream.fromXML(xml);
		return webResult;
	} 

	public WebResult removeTrans(String transname, String id)
			throws IllegalArgumentException, IOException {
		WebResult webResult = null;
		String service = null;

		if ((id == null) || (id.length() == 0)) {
			if ((transname != null) && (transname.length() != 0)) {
				service = "/tietl/removeTrans/?xml=Y&name="
						+ URLEncoder.encode(transname, "UTF-8");
			} else
				throw new IllegalArgumentException("参数transname和id同时为null或空串");
		} else {
			if (transname == null) {
				transname = "";
			}
			service = "/tietl/removeTrans/?xml=Y&name="
					+ URLEncoder.encode(transname, "UTF-8") + "&id=" + id;
		}

		String xml = this.client.executeHTTPGet(service);
		webResult = (WebResult) this.xstream.fromXML(xml);
		return webResult;
	}

	public WebResult startTrans(String transname)
			throws IllegalArgumentException, IOException {
		WebResult webResult = null;
		String service = null;

		if ((transname != null) && (transname.length() != 0)) {
			service = "/tietl/startTrans/?xml=Y&name="
					+ URLEncoder.encode(transname, "UTF-8");
		} else {
			throw new IllegalArgumentException("参数transname为null或空串");
		}
		String xml = this.client.executeHTTPGet(service);
		webResult = (WebResult) this.xstream.fromXML(xml);
		return webResult;
	}

	public WebResult startTrans(String transname, String id)
			throws IllegalArgumentException, IOException {
		WebResult webResult = null;
		String service = null;

		if ((id == null) || (id.length() == 0)) {
			if ((transname != null) && (transname.length() != 0)) {
				service = "/tietl/startTrans/?xml=Y&name="
						+ URLEncoder.encode(transname, "UTF-8");
			} else
				throw new IllegalArgumentException("参数transname和id同时为null或空串");
		} else {
			if (transname == null) {
				transname = "";
			}
			service = "/tietl/startTrans/?xml=Y&name="
					+ URLEncoder.encode(transname, "UTF-8") + "&id=" + id;
		}

		String xml = this.client.executeHTTPGet(service);
		webResult = (WebResult) this.xstream.fromXML(xml);
		return webResult;
	}

	public WebResult stopTrans(String transname)
			throws IllegalArgumentException, IOException {
		WebResult webResult = null;
		String service = null;

		if ((transname != null) && (transname.length() != 0)) {
			service = "/tietl/stopTrans/?xml=Y&name="
					+ URLEncoder.encode(transname, "UTF-8");
		} else {
			throw new IllegalArgumentException("参数transname为null或空串");
		}
		String xml = this.client.executeHTTPGet(service);
		webResult = (WebResult) this.xstream.fromXML(xml);
		return webResult;
	}

	public WebResult stopTrans(String transname, String id)
			throws IllegalArgumentException, IOException {
		WebResult webResult = null;
		String service = null;

		if ((id == null) || (id.length() == 0)) {
			if ((transname != null) && (transname.length() != 0)) {
				service = "/tietl/stopTrans/?xml=Y&name="
						+ URLEncoder.encode(transname, "UTF-8");
			} else
				throw new IllegalArgumentException("参数transname和id同时为null或空串");
		} else {
			if (transname == null) {
				transname = "";
			}
			service = "/tietl/stopTrans/?xml=Y&name="
					+ URLEncoder.encode(transname, "UTF-8") + "&id=" + id;
		}

		String xml = this.client.executeHTTPGet(service);
		webResult = (WebResult) this.xstream.fromXML(xml);
		return webResult;
	}

	public WebResult pauseTrans(String transname)
			throws IllegalArgumentException, IOException {
		WebResult webResult = null;
		String service = null;

		if ((transname != null) && (transname.length() != 0)) {
			service = "/tietl/pauseTrans/?xml=Y&name="
					+ URLEncoder.encode(transname, "UTF-8");
		} else {
			throw new IllegalArgumentException("参数transname为null或空串");
		}
		String xml = this.client.executeHTTPGet(service);
		webResult = (WebResult) this.xstream.fromXML(xml);
		return webResult;
	}

	public WebResult pauseTrans(String transname, String id)
			throws IllegalArgumentException, IOException {
		WebResult webResult = null;
		String service = null;

		if ((id == null) || (id.length() == 0)) {
			if ((transname != null) && (transname.length() != 0)) {
				service = "/tietl/pauseTrans/?xml=Y&name="
						+ URLEncoder.encode(transname, "UTF-8");
			} else
				throw new IllegalArgumentException("参数transname和id同时为null或空串");
		} else {
			if (transname == null) {
				transname = "";
			}
			service = "/tietl/pauseTrans/?xml=Y&name="
					+ URLEncoder.encode(transname, "UTF-8") + "&id=" + id;
		}

		String xml = this.client.executeHTTPGet(service);
		webResult = (WebResult) this.xstream.fromXML(xml);
		return webResult;
	}

	//TODO
	/** 根据转换的名字，得到一个转换的状态信息
	 * @param transname 转换的名字
	 * @return
	 * @throws TIETLServerException
	 * @throws IllegalArgumentException
	 * @throws IOException
	 *
	public TransStatus getTransStatus(String transname)
			throws TIETLServerException, IllegalArgumentException, IOException {
		TransStatus transStatus = null;
		String service = null;

		if ((transname != null) && (transname.length() != 0)) {
			service = "/tietl/transStatus/?xml=Y&name="
					+ URLEncoder.encode(transname, "UTF-8");
		} else {
			throw new IllegalArgumentException("参数transname为null或空串");
		}
		String xml = this.client.executeHTTPGet(service);

		Object result = this.xstream.fromXML(xml);
		if ((result instanceof TransStatus)) {
			transStatus = (TransStatus) result;
		} else {
			WebResult webResult = (WebResult) result;
			throw new TIETLServerException(1, webResult.getMessage());
		}

		String gzippedLogString = transStatus.getLogging_string();
		String logString = TIETLServerUtil.ungzipLogString(gzippedLogString);
		transStatus.setLogging_string(logString);
		return transStatus;
	}

	public TransStatus getTransStatus(String transname, String id)
			throws TIETLServerException, IllegalArgumentException, IOException {
		TransStatus transStatus = null;
		String service = null;

		if ((id == null) || (id.length() == 0)) {
			if ((transname != null) && (transname.length() != 0)) {
				service = "/tietl/transStatus/?xml=Y&name="
						+ URLEncoder.encode(transname, "UTF-8");
			} else
				throw new IllegalArgumentException("参数transname和id同时为null或空串");
		} else {
			if (transname == null) {
				transname = "";
			}
			service = "/tietl/transStatus/?xml=Y&name="
					+ URLEncoder.encode(transname, "UTF-8") + "&id=" + id;
		}

		String xml = this.client.executeHTTPGet(service);

		Object result = this.xstream.fromXML(xml);
		if ((result instanceof TransStatus)) {
			transStatus = (TransStatus) result;
		} else {
			WebResult webResult = (WebResult) result;
			if ((id == null) || (id.length() == 0)) {
				throw new TIETLServerException(1, webResult.getMessage());
			}

			throw new TIETLServerException(1, webResult.getMessage(), webResult
					.getId());
		}

		String gzippedLogString = transStatus.getLogging_string();
		String logString = TIETLServerUtil.ungzipLogString(gzippedLogString);
		transStatus.setLogging_string(logString);

		return transStatus;
	}

	public TransStatus getTransStatus(String transname, String id, String from)
			throws TIETLServerException, IllegalArgumentException, IOException {
		TransStatus transStatus = null;
		String service = null;

		if ((id == null) || (id.length() == 0)) {
			if ((transname != null) && (transname.length() != 0)) {
				service = "/tietl/transStatus/?xml=Y&name="
						+ URLEncoder.encode(transname, "UTF-8") + "&from="
						+ from;
			} else {
				throw new IllegalArgumentException("参数transname和id同时为null或空串");
			}
		} else {
			if (transname == null) {
				transname = "";
			}
			service = "/tietl/transStatus/?xml=Y&name="
					+ URLEncoder.encode(transname, "UTF-8") + "&id=" + id
					+ "&from=" + from;
		}

		String xml = this.client.executeHTTPGet(service);

		Object result = this.xstream.fromXML(xml);
		if ((result instanceof TransStatus)) {
			transStatus = (TransStatus) result;
		} else {
			WebResult webResult = (WebResult) result;
			if ((id == null) || (id.length() == 0)) {
				throw new TIETLServerException(1, webResult.getMessage());
			}

			throw new TIETLServerException(1, webResult.getMessage(), webResult
					.getId());
		}

		String gzippedLogString = transStatus.getLogging_string();
		String logString = TIETLServerUtil.ungzipLogString(gzippedLogString);
		transStatus.setLogging_string(logString);

		return transStatus;
	}

	public WebResult addJob(JobConfig jobConfig) throws IOException {
		WebResult webResult = null;
		String service = "/tietl/addJob/?xml=Y";

		String xml = this.client.executeHTTPPost(service, this.xstream
				.toXML(jobConfig));
		webResult = (WebResult) this.xstream.fromXML(xml);

		return webResult;
	}

	public WebResult removeJob(String jobname) throws IllegalArgumentException,
			IOException {
		WebResult webResult = null;
		String service = null;

		if ((jobname != null) && (jobname.length() != 0)) {
			service = "/tietl/removeJob/?xml=Y&name="
					+ URLEncoder.encode(jobname, "UTF-8");
		} else {
			throw new IllegalArgumentException("参数jobname为null或空串");
		}
		String xml = this.client.executeHTTPGet(service);
		webResult = (WebResult) this.xstream.fromXML(xml);
		return webResult;
	}

	public WebResult removeJob(String jobname, String id)
			throws IllegalArgumentException, IOException {
		WebResult webResult = null;
		String service = null;

		if ((id == null) || (id.length() == 0)) {
			if ((jobname != null) && (jobname.length() != 0)) {
				service = "/tietl/removeJob/?xml=Y&name="
						+ URLEncoder.encode(jobname, "UTF-8");
			} else
				throw new IllegalArgumentException("参数jobname和id同时为null或空串");
		} else {
			if (jobname == null) {
				jobname = "";
			}
			service = "/tietl/removeJob/?xml=Y&name="
					+ URLEncoder.encode(jobname, "UTF-8") + "&id=" + id;
		}

		String xml = this.client.executeHTTPGet(service);
		webResult = (WebResult) this.xstream.fromXML(xml);
		return webResult;
	}

	public WebResult startJob(String jobname) throws IllegalArgumentException,
			IOException {
		WebResult webResult = null;
		String service = null;

		if ((jobname != null) && (jobname.length() != 0)) {
			service = "/tietl/startJob/?xml=Y&name="
					+ URLEncoder.encode(jobname, "UTF-8");
		} else {
			throw new IllegalArgumentException("参数jobname为null或空串");
		}
		String xml = this.client.executeHTTPGet(service);
		webResult = (WebResult) this.xstream.fromXML(xml);
		return webResult;
	}

	public WebResult startJob(String jobname, String id)
			throws IllegalArgumentException, IOException {
		WebResult webResult = null;
		String service = null;

		if ((id == null) || (id.length() == 0)) {
			if ((jobname != null) && (jobname.length() != 0)) {
				service = "/tietl/startJob/?xml=Y&name="
						+ URLEncoder.encode(jobname, "UTF-8");
			} else
				throw new IllegalArgumentException("参数jobname和id同时为null或空串");
		} else {
			if (jobname == null) {
				jobname = "";
			}
			service = "/tietl/startJob/?xml=Y&name="
					+ URLEncoder.encode(jobname, "UTF-8") + "&id=" + id;
		}

		String xml = this.client.executeHTTPGet(service);
		webResult = (WebResult) this.xstream.fromXML(xml);
		return webResult;
	}

	public WebResult stopJob(String jobname) throws IllegalArgumentException,
			IOException {
		WebResult webResult = null;
		String service = null;

		if ((jobname != null) && (jobname.length() != 0)) {
			service = "/tietl/stopJob/?xml=Y&name="
					+ URLEncoder.encode(jobname, "UTF-8");
		} else {
			throw new IllegalArgumentException("参数jobname为null或空串");
		}
		String xml = this.client.executeHTTPGet(service);
		webResult = (WebResult) this.xstream.fromXML(xml);
		return webResult;
	}

	public WebResult stopJob(String jobname, String id)
			throws IllegalArgumentException, IOException {
		WebResult webResult = null;
		String service = null;

		if ((id == null) || (id.length() == 0)) {
			if ((jobname != null) && (jobname.length() != 0)) {
				service = "/tietl/stopJob/?xml=Y&name="
						+ URLEncoder.encode(jobname, "UTF-8");
			} else
				throw new IllegalArgumentException("参数jobname和id同时为null或空串");
		} else {
			if (jobname == null) {
				jobname = "";
			}
			service = "/tietl/stopJob/?xml=Y&name="
					+ URLEncoder.encode(jobname, "UTF-8") + "&id=" + id;
		}

		String xml = this.client.executeHTTPGet(service);
		webResult = (WebResult) this.xstream.fromXML(xml);
		return webResult;
	}

	public JobStatus getJobStatus(String jobname) throws TIETLServerException,
			IllegalArgumentException, IOException {
		JobStatus jobStatus = null;
		String service = null;

		if ((jobname != null) && (jobname.length() != 0)) {
			service = "/tietl/jobStatus/?xml=Y&name="
					+ URLEncoder.encode(jobname, "UTF-8");
		} else {
			throw new IllegalArgumentException("参数jobname为null或空串");
		}
		String xml = this.client.executeHTTPGet(service);

		Object result = this.xstream.fromXML(xml);
		if ((result instanceof JobStatus)) {
			jobStatus = (JobStatus) result;
		} else {
			WebResult webResult = (WebResult) result;
			throw new TIETLServerException(1, webResult.getMessage());
		}

		String gzippedLogString = jobStatus.getLogging_string();
		String logString = TIETLServerUtil.ungzipLogString(gzippedLogString);
		jobStatus.setLogging_string(logString);

		return jobStatus;
	}

	public JobStatus getJobStatus(String jobname, String id)
			throws TIETLServerException, IllegalArgumentException, IOException {
		JobStatus jobStatus = null;
		String service = null;

		if ((id == null) || (id.length() == 0)) {
			if ((jobname != null) && (jobname.length() != 0)) {
				service = "/tietl/jobStatus/?xml=Y&name="
						+ URLEncoder.encode(jobname, "UTF-8");
			} else
				throw new IllegalArgumentException("参数jobname和id同时为null或空串");
		} else {
			if (jobname == null) {
				jobname = "";
			}
			service = "/tietl/jobStatus/?xml=Y&name="
					+ URLEncoder.encode(jobname, "UTF-8") + "&id=" + id;
		}

		String xml = this.client.executeHTTPGet(service);

		Object result = this.xstream.fromXML(xml);
		if ((result instanceof JobStatus)) {
			jobStatus = (JobStatus) result;
		} else {
			WebResult webResult = (WebResult) result;
			if ((id == null) || (id.length() == 0)) {
				throw new TIETLServerException(1, webResult.getMessage());
			}

			throw new TIETLServerException(1, webResult.getMessage(), webResult
					.getId());
		}

		String gzippedLogString = jobStatus.getLogging_string();
		String logString = TIETLServerUtil.ungzipLogString(gzippedLogString);
		jobStatus.setLogging_string(logString);

		return jobStatus;
	}

	public JobStatus getJobStatus(String jobname, String id, String from)
			throws TIETLServerException, IllegalArgumentException, IOException {
		JobStatus jobStatus = null;
		String service = null;

		if ((id == null) || (id.length() == 0)) {
			if ((jobname != null) && (jobname.length() != 0)) {
				service = "/tietl/jobStatus/?xml=Y&name="
						+ URLEncoder.encode(jobname, "UTF-8") + "&from=" + from;
			} else
				throw new IllegalArgumentException("参数jobname和id同时为null或空串");
		} else {
			if (jobname == null) {
				jobname = "";
			}
			service = "/tietl/jobStatus/?xml=Y&name="
					+ URLEncoder.encode(jobname, "UTF-8") + "&id=" + id
					+ "&from=" + from;
		}

		String xml = this.client.executeHTTPGet(service);

		Object result = this.xstream.fromXML(xml);
		if ((result instanceof JobStatus)) {
			jobStatus = (JobStatus) result;
		} else {
			WebResult webResult = (WebResult) result;
			if ((id == null) || (id.length() == 0)) {
				throw new TIETLServerException(1, webResult.getMessage());
			}

			throw new TIETLServerException(1, webResult.getMessage(), webResult
					.getId());
		}

		String gzippedLogString = jobStatus.getLogging_string();
		String logString = TIETLServerUtil.ungzipLogString(gzippedLogString);
		jobStatus.setLogging_string(logString);

		return jobStatus;
	}
	*/
}
