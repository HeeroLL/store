package com.zebone.dip.etl.vo;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("jobstatus")
public class JobStatus  implements Serializable{
	private String jobname;
	private String id;
	private String status_desc;
	private String error_desc;
	private int first_log_line_nr;
	private int last_log_line_nr;
	private String logging_string;
	private Result result;

	public String getJobname() {
		return this.jobname;
	}

	public String getId() {
		return this.id;
	}

	public String getStatus_desc() {
		return this.status_desc;
	}

	public String getError_desc() {
		return this.error_desc;
	}

	public int getFirst_log_line_nr() {
		return this.first_log_line_nr;
	}

	public int getLast_log_line_nr() {
		return this.last_log_line_nr;
	}

	public String getLogging_string() {
		return this.logging_string;
	}

	public void setLogging_string(String logString) {
		this.logging_string = logString;
	}

	public Result getResult() {
		return this.result;
	}

	public String toString() {
		return "JobStatus [error_desc=" + this.error_desc
				+ ", first_log_line_nr=" + this.first_log_line_nr + ", id="
				+ this.id + ", last_log_line_nr=" + this.last_log_line_nr
				+ ", logging_string=" + this.logging_string + ", result="
				+ this.result + ", status_desc=" + this.status_desc
				+ ", jobname=" + this.jobname + "]";
	}
}