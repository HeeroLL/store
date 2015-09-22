package com.zebone.dnode.engine.empi.dao;

import java.util.List;

import com.zebone.btp.core.mybatis.EmpiMapper;
import com.zebone.dnode.engine.empi.vo.ResidentCard;

/**
 * 主索引注册卡信息Dao
 *
 * @author 杨英
 * @version 2014-6-16 上午09:58
 */
@EmpiMapper
public interface ResidentCardMapper {

    /**
     * 按照EMPI标识查找卡信息
     * @param empiNo
     * @return
     */
    public List<ResidentCard> getResidentCardByEmpi(String empiNo);
}
