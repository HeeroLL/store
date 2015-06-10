package com.zebone.dnode.etl;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.zebone.taskscheduling.quartz.SequenceJob;

public class Job003 extends SequenceJob implements Job {

	@Override
	public void execute(JobExecutionContext jc) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println(jc.getJobDetail().getKey().toString() +" execut job 003 ");
		execteChained(jc);
	}

}
