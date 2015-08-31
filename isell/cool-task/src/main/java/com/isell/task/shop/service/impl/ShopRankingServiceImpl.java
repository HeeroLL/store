package com.isell.task.shop.service.impl;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isell.task.shop.dao.ShopRankingDao;
import com.isell.task.shop.service.ShopRankingService;
import com.isell.task.shop.vo.ShopRanking;
import com.isell.task.util.DateUtil;
import com.isell.task.util.Identities;

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
        // 统计日期
        String createdate = DateUtil.getAdvanceDay(-1);
        // 已统计过就不再统计
        if (shopRankingDao.isExistDate(createdate)) {
            return;
        }
        
        // 统计总销量，总单数，总收入
        List<ShopRanking> shopRankingList = shopRankingDao.getAllRanking();
        if (shopRankingList == null || shopRankingList.isEmpty()) {
            return;
        }
        
        // 获取上次统计信息
        List<ShopRanking> lastList = shopRankingDao.getLastRanking(DateUtil.getAdvanceDay(-2)); // 当天是定时任务执行时间-1天，昨天就是-2天
        Map<String, ShopRanking> lastMap = new HashMap<String, ShopRanking>();
        if (lastList != null) {
            for (ShopRanking shopRanking : lastList) {
                lastMap.put(shopRanking.getsId(), shopRanking);
            }
        }
        // 获取当天的统计信息
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        List<ShopRanking> todayList = shopRankingDao.getRankingByDate(cal.getTime());
        Map<String, ShopRanking> todayMap = new HashMap<String, ShopRanking>();
        if (todayList != null) {
            for (ShopRanking shopRanking : todayList) {
                todayMap.put(shopRanking.getsId(), shopRanking);
            }
        }
        // 按交易总额排序
        Collections.sort(shopRankingList, new Comparator<ShopRanking>() {
            @Override
            public int compare(ShopRanking o1, ShopRanking o2) {
                return (int)((o2.getTurnoverAmount() - o1.getTurnoverAmount()) * 100);
            }
        });
        // 上一位酷店
        ShopRanking lastOne = null;
        // 组装参数
        int index = 1; // 排名
        for (ShopRanking shopRanking : shopRankingList) {
            shopRanking.setId(Identities.uuid()); // 主键
            shopRanking.setRanking(index); // 排名
            ShopRanking lastShopRanking = lastMap.get(shopRanking.getsId()); // 上次排名
            // 设置上次排名
            if (lastShopRanking != null) {
                shopRanking.setLastRanking(lastShopRanking.getRanking());
                shopRanking.setLastOrderRanking(lastShopRanking.getOrderRanking());
            }
            if (lastOne != null) { // 计算与上一名差距
                shopRanking.setGapAmount(lastOne.getTurnoverAmount() - shopRanking.getTurnoverAmount());
            }
            // 设置当日信息
            ShopRanking todayShopRanking = todayMap.get(shopRanking.getsId());
            if (todayShopRanking != null) {
                shopRanking.setDayAmount(todayShopRanking.getTurnoverAmount());
                shopRanking.setDayOrders(todayShopRanking.getTurnoverOrders());
                shopRanking.setDayIncome(todayShopRanking.getTotalIncome());
            }
            shopRanking.setCreatedate(createdate);
            
            lastOne = shopRanking;
            index++;
        }
        
        // 按交易单数排序
        Collections.sort(shopRankingList, new Comparator<ShopRanking>() {
            @Override
            public int compare(ShopRanking o1, ShopRanking o2) {
                return o2.getTurnoverOrders() - o1.getTurnoverOrders();
            }
        });
        index = 1; // 排名
        for (ShopRanking shopRanking : shopRankingList) {
            shopRanking.setOrderRanking(index); // 排名
            index++;
        }
        
        try {
            // 批量新增
            shopRankingDao.batchSave(shopRankingList);
            // 批量更新酷店表信息
            shopRankingDao.batchUpdate(shopRankingList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
