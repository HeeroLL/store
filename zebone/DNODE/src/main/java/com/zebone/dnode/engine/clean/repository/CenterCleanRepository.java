package com.zebone.dnode.engine.clean.repository;

import java.util.List;


/**
 * 清洗Repository 
 * @author cz
 *
 */
public interface CenterCleanRepository {
	
    /**
     * 执行sql
     * @param sqls
     */
	void execute(List<String> sqls);

}
