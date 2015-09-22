package com.zebone.empi.receive.service;

import org.springframework.stereotype.Service;

/**
 * 文档订阅任务服务
 * @author 杨英
 * @version 2014-8-13 下午2:08
 */
@Service
public interface DocSubJobService {

    /**
     * 文档订阅
     */
    public void docSub();
    
    
    /**
     * 增量订阅
     * @author 陈阵 
     * @date 2014-11-7 上午9:00:05
     */
    public void incrementDocSub();
}
