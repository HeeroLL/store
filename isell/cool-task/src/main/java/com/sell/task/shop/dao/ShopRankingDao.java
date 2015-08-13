package com.sell.task.shop.dao;

import java.util.Date;
import java.util.List;

import com.sell.task.shop.vo.ShopRanking;

/**
 * 酷店排名信息dao层
 * 
 * @author lilin
 * @version [版本号, 2015年8月1日]
 */
public interface ShopRankingDao {
    /**
     * 获取所有的统计信息
     *
     * @return 所有统计信息
     */
    List<ShopRanking> getAllRanking();
    
    /**
     * 按天获取的统计信息
     *
     * @return 某天的统计信息
     */
    List<ShopRanking> getRankingByDate(Date date);
    
    /**
     * 获取上次统计的所以信息
     *
     * @param datetime 统计日期
     * @return 上次统计的所以信息
     */
    List<ShopRanking> getLastRanking(String datetime);
    
    /**
     * 判断当前日期是否已统计过
     *
     * @param createdate 统计日期
     * @return 是否统计
     */
    boolean isExistDate(String createdate);
    
    /**
     * 批量删除
     */
    void batchDelete();
    
    /**
     * 批量保存酷店排名信息
     *
     * @param shopRankingList 酷店排名信息集合
     */
    void batchSave(List<ShopRanking> shopRankingList);
    
    /**
     * 批量更新酷店信息
     *
     * @param shopRankingList 酷店信息集合
     */
    void batchUpdate(List<ShopRanking> shopRankingList);
}
