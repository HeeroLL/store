package com.zebone.dnode.engine.empi.dao;

import java.util.List;

import com.zebone.btp.core.mybatis.EmpiMapper;
import com.zebone.dnode.engine.empi.vo.ResidentInfo;

/**
 * 主索引注册信息Dao
 *
 * @author 杨英
 * @version 2014-6-16 上午09:33
 */
@EmpiMapper
public interface ResidentInfoMapper {

    /**
     * 获取需要进行推送的信息列表
     * @return
     */
    public List<ResidentInfo> getPushInfo();

    /**
     * 根据主索引更新推送标志
     */
    void updatePushStatus(String empiNo);
}
