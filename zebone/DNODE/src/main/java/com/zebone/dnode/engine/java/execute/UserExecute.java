package com.zebone.dnode.engine.java.execute;

import java.util.Calendar;


public class UserExecute implements JavaEngineExecute {

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("执行任务时间：" + Calendar.getInstance().getTime());
	}

}
