package com.zebone.dip.empi.dao;

import com.zebone.btp.core.mybatis.EmpiMapper;
import com.zebone.dip.empi.vo.EmpiInfo;
import com.zebone.dip.empi.vo.ResidentCard;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 主索引信息查询DAO
 * @author 杨英
 * @version 2014-7-15 下午3:19
 */
@EmpiMapper
public interface EmpiInfoMapper {

    /**
     * 获取主索引信息列表
     * @param residentCard
     * @return
     */
    List<EmpiInfo> empiInfoList(RowBounds rowBounds, ResidentCard residentCard);

    /**
     * 获取主索引信息列表总数
     * @param residentCard
     * @return
     */
    int empiInfoTotalCount(ResidentCard residentCard);

    /**
     * 根据主索引号获取主索引详情
     * @param empiNo
     * @return
     */
    EmpiInfo getEmpiDetail(String empiNo);

}
