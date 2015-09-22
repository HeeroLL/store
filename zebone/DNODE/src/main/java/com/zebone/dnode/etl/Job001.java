package com.zebone.dnode.etl;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.zebone.taskscheduling.quartz.SequenceJob;

public class Job001 extends SequenceJob implements Job {
      
	
	private String org;
	
	private String fuck;
	
	@Override
	public void execute(JobExecutionContext jc) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println(jc.getJobDetail().getKey().toString() +" execut job 001 "+ fuck);
		execteChained(jc);
	}
   
	
	public void setOrg(String org) {
		this.org = org;
	}


	public void setFuck(String fuck) {
		this.fuck = fuck;
	}
	
	
	

}
