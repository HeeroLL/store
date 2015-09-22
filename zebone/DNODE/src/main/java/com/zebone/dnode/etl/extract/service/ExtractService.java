package com.zebone.dnode.etl.extract.service;

import com.zebone.dnode.etl.extract.pojo.ExtractConfig;

public interface ExtractService {
	
	public static final int BATCH_NUM = 1000;
	
	void extractDataInDB(ExtractConfig extractConfig);

}
