package com.zebone.dnode.engine.empi.service;

import org.springframework.stereotype.Service;

/**
 * 主索引信息推送接口
 *
 * @author 杨英
 * @version 2014-06-16 上午09:15
 */
@Service
public interface EmpiPushService {

    /**
     * 推送主索引信息
     */
    public void pushEmpiInfo();
}
