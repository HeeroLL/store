package com.zebone.dnode.engine.empi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zebone.dnode.engine.empi.vo.EmpiMatchLog;


/**
 * 主索引调度接口
 * @author Administrator
 *
 */
@Service
public interface DispatchService {
	/**
	 * 获取匹配上的Match log记录
	 * @return
	 */
	List<EmpiMatchLog> getMatchedLog();
	
	/**
	 * 更新ws注册成功的match log
	 */
	void updateResistedMatchLog(EmpiMatchLog empiMtchedLog);
	
	/**
	 * 执行调度工作
	 */
	void doDispatch();
	
	 
}
