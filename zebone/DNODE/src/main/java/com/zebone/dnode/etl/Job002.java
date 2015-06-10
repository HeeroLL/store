package com.zebone.dnode.etl;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.zebone.dnode.etl.extract.service.ExtractService;
import com.zebone.taskscheduling.quartz.SequenceJob;

public class Job002 extends SequenceJob implements Job {
	
	
	private ExtractService extractService;

	@Override
	public void execute(JobExecutionContext jc) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println(jc.getJobDetail().getKey().toString() +" execut job 002 " + extractService);
		execteChained(jc);
	}

	public void setExtractService(ExtractService extractService) {
		this.extractService = extractService;
	}
	
	

}
