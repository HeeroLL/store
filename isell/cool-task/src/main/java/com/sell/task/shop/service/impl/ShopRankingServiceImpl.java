package com.sell.task.shop.service.impl;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sell.task.shop.dao.ShopRankingDao;
import com.sell.task.shop.service.ShopRankingService;
import com.sell.task.shop.vo.ShopRanking;

/**
 * 酷店排名服务接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年8月1日]
 */
@Service("shopRankingService")
public class ShopRankingServiceImpl implements ShopRankingService {
    
    /**
     * 酷店排名信息dao层
     */
    @Resource
    private ShopRankingDao shopRankingDao;
    
    @Transactional("cool")
    @Override
    public void batchSave() {
        List<ShopRanking> shopRankingList = shopRankingDao.getAllRanking();
        if (shopRankingList == null || shopRankingList.isEmpty()) {
            return;
        }
        Collections.sort(shopRankingList);
        
        // 获取上次统计信息
        List<ShopRanking> lastList = shopRankingDao.getLastRanking();
        Map<String, Integer> lastMap = new HashMap<String, Integer>();
        if (lastList != null) {
            for (ShopRanking shopRanking : lastList) {
                lastMap.put(shopRanking.getsId(), shopRanking.getRanking());
            }
        }
        // 获取昨天的统计信息
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        List<ShopRanking> yestodayList = shopRankingDao.getRankingByDate(cal.getTime());
        Map<String, Double> yestodayMap = new HashMap<String, Double>();
        if (yestodayList != null) {
            for (ShopRanking shopRanking : yestodayList) {
                yestodayMap.put(shopRanking.getsId(), shopRanking.getIncome());
            }
        }
        
        // 上一位酷店
        ShopRanking lastOne = null;
        // 组装参数
        int index = 1; // 排名
        for (ShopRanking shopRanking : shopRankingList) {
            shopRanking.setRanking(index); // 排名
            Integer lastRanking = lastMap.get(shopRanking.getsId()); // 上次排名
            
            if (lastRanking != null) {
                shopRanking.setLastRanking(lastRanking);
            }
            if (lastOne != null) { // 计算与上一名差距
                shopRanking.setGapAmount(lastOne.getTurnoverAmount() - shopRanking.getTurnoverAmount());
            }
            // 昨日收入
            Double yestodayIncome = yestodayMap.get(shopRanking.getsId());
            if (yestodayIncome != null) {
                shopRanking.setYestodayIncome(yestodayIncome);
            }
            
            lastOne = shopRanking;
            index++;
        }
        // 批量新增
        shopRankingDao.batchDelete();
        shopRankingDao.batchSave(shopRankingList);
        // 批量更新酷店表信息
        shopRankingDao.batchUpdate(shopRankingList);
    }
}
