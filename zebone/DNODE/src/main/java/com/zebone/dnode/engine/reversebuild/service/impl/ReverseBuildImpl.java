package com.zebone.dnode.engine.reversebuild.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zebone.dnode.engine.reversebuild.dto.BusDataPara;
import com.zebone.dnode.engine.reversebuild.service.ReverseBuild;

@Service("reverseBuildImpl")
public class ReverseBuildImpl implements ReverseBuild {

	@SuppressWarnings("unused")
	public void build(String data) {
		// TODO Auto-generated method stub
		BusDataPara bdp = JSON.parseObject(data, BusDataPara.class);
	}

}
