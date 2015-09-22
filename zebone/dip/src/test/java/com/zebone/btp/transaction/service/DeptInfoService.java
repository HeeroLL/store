package com.zebone.btp.transaction.service;

import com.zebone.btp.transaction.pojo.DeptInfo;

public interface DeptInfoService {
	public void add() ;

	public DeptInfo get(String id);

	public void update() ;

	public void delete(String id );
	
	public void saveTwoDept();
}
