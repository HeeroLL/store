package com.zebone.dip.empi.dao;

import com.zebone.btp.core.mybatis.EmpiMapper;
import com.zebone.dip.empi.vo.ResidentCard;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 主索引注册卡信息查询DAO
 * @author 杨英
 * @version 2014-7-15 下午3:45
 */
@EmpiMapper
public interface ResidentCardMapper {

    /**
     * 获取主索引号列表
     * @param residentCard
     * @return
     */
    List<String> getEmpiNoList(ResidentCard residentCard);

    /**
     * 根据主索引号获取标识信息列表
     * @param empiNo
     * @return
     */
    List<ResidentCard> getCardList(String empiNo);
    
    
    /**
     * 根据卡类型和卡号获取卡信息
     * @param cardNo
     * @param cardType
     * @return
     * @author 陈阵 
     * @date 2014-10-10 下午4:09:44
     */
    ResidentCard getCardByNoAndType(@Param("cardNo") String cardNo,@Param("cardType") String cardType);
}
