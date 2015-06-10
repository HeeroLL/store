package com.zebone.dip.etl.vo;
import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@SuppressWarnings("serial")
@XStreamAlias("transstatus")
public class TransStatus implements Serializable{

	String transname;
	String id;
	String status_desc;
	String error_desc;
	boolean paused;
	
	List<StepStatus> stepstatuslist;
	
	int first_log_line_nr;
	int last_log_line_nr;
	String logging_string;
	Result result;
	
	public TransStatus() {
		super();
	}

	public String getTransname() {
		return transname;
	}

	public void setTransname(String transname) {
		this.transname = transname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus_desc() {
		return status_desc;
	}

	public void setStatus_desc(String statusDesc) {
		status_desc = statusDesc;
	}

	public String getError_desc() {
		return error_desc;
	}

	public void setError_desc(String errorDesc) {
		error_desc = errorDesc;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	public List<StepStatus> getStepstatuslist() {
		return stepstatuslist;
	}

	public void setStepstatuslist(List<StepStatus> stepstatuslist) {
		this.stepstatuslist = stepstatuslist;
	}

	public int getFirst_log_line_nr() {
		return first_log_line_nr;
	}

	public void setFirst_log_line_nr(int firstLogLineNr) {
		first_log_line_nr = firstLogLineNr;
	}

	public int getLast_log_line_nr() {
		return last_log_line_nr;
	}

	public void setLast_log_line_nr(int lastLogLineNr) {
		last_log_line_nr = lastLogLineNr;
	}

	public String getLogging_string() {
		return logging_string;
	}

	public void setLogging_string(String loggingString) {
		logging_string = loggingString;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "TransStatus [error_desc=" + error_desc + ", first_log_line_nr="
				+ first_log_line_nr + ", id=" + id + ", last_log_line_nr="
				+ last_log_line_nr + ", logging_string=" + logging_string
				+ ", paused=" + paused + ", result=" + result
				+ ", status_desc=" + status_desc + ", stepstatuslist="
				+ stepstatuslist + ", transname=" + transname + "]";
	}
}
