package com.zebone.dnode.etl.convert.service;

import com.zebone.dnode.etl.convert.pojo.ConvertConfig;

import java.util.Map;

/**
 * 数据清洗转换服务接口
 *
 * @author 杨英
 * @version 2014-02-18 上午08:50
 */
public interface ConvertService {

    void convertData(ConvertConfig convertConfig, Map<String,String> oMap);
}
