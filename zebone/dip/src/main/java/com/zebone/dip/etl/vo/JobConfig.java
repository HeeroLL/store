package com.zebone.dip.etl.vo;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("job_configuration")
public class JobConfig  implements Serializable{

	@XStreamAlias("job_reference")
	private Reference jobRef;

	@XStreamAlias("job_execution_configuration")
	private JobExecConfig jobExecConfig;

	public Reference getJobRef() {
		return this.jobRef;
	}

	public void setJobRef(Reference jobRef) {
		this.jobRef = jobRef;
	}

	public JobExecConfig getJobExecConfig() {
		return this.jobExecConfig;
	}

	public void setJobExecConfig(JobExecConfig jobExecConfig) {
		this.jobExecConfig = jobExecConfig;
	}
}
