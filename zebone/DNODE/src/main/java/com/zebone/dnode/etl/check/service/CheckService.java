package com.zebone.dnode.etl.check.service;

import com.zebone.dnode.etl.check.pojo.CheckConfig;

import java.util.Map;

/**
 * 数据验证服务接口
 *
 * @author 杨英
 * @version 2014-02-13 下午02:36
 */
public interface CheckService {

    void checkSourceData(CheckConfig checkConfig,Map<String,String> oMap);
}
