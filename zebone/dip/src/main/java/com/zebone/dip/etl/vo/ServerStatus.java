package com.zebone.dip.etl.vo;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("serverstatus")
public class ServerStatus  implements Serializable{
	private String statusdesc;
	private List<TransStatus> transstatuslist;
	private List<JobStatus> jobstatuslist;

	public String getStatusdesc() {
		return this.statusdesc;
	}

	public List<TransStatus> getTransstatuslist() {
		return this.transstatuslist;
	}

	public List<JobStatus> getJobstatuslist() {
		return this.jobstatuslist;
	}

	public String toString() {
		return "ServerStatus [statusdesc=" + this.statusdesc
				+ ", transstatuslist=" + this.transstatuslist
				+ ", jobstatuslist=" + this.jobstatuslist + "]";
	}
}