package com.sell.task.shop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import com.sell.task.shop.dao.ShopRankingDao;
import com.sell.task.shop.vo.ShopRanking;

/**
 * 酷店排名信息dao层实现类
 * 
 * @author lilin
 * @version [版本号, 2015年8月1日]
 */
@Repository("shopRankingDao")
public class ShopRankingDaoImpl implements ShopRankingDao {
    
    /**
     * rowMapper
     */
    private static RowMapper<ShopRanking> rowMapper = new RowMapper<ShopRanking>() {
        @Override
        public ShopRanking mapRow(ResultSet rs, int i)
            throws SQLException {
            ShopRanking shopRanking = new ShopRanking();
            shopRanking.setsId(rs.getString("supplier"));
            shopRanking.setTurnoverAmount(rs.getDouble("total"));
            shopRanking.setTurnoverOrders(rs.getInt("orderCount"));
            shopRanking.setTotalIncome(rs.getDouble("profit"));
            
            return shopRanking;
        }
    };
    
    /**
     * lastRowMapper
     */
    private static RowMapper<ShopRanking> lastRowMapper = new RowMapper<ShopRanking>() {
        @Override
        public ShopRanking mapRow(ResultSet rs, int i)
            throws SQLException {
            ShopRanking shopRanking = new ShopRanking();
            shopRanking.setsId(rs.getString("s_id"));
            shopRanking.setRanking(rs.getInt("ranking"));
            shopRanking.setOrderRanking(rs.getInt("order_ranking"));
            
            return shopRanking;
        }
    };
    
    /**
     * coolJdbcTemplate
     */
    @Resource
    private NamedParameterJdbcTemplate coolJdbcTemplate;
    
    @Override
    public List<ShopRanking> getAllRanking() {
        // 统计订单信息
        String sql =
            "select supplier,sum(total) as total,sum(supplier_profit) as profit,count(*) as orderCount from cool_order where (state=3 or state=4) and supplier is not null group by supplier";
        return coolJdbcTemplate.query(sql, rowMapper);
    }
    
    @Override
    public List<ShopRanking> getRankingByDate(Date date) {
        // 将时间设置为当天的0时0分0秒
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Timestamp today = new Timestamp(cal.getTimeInMillis());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Timestamp tomorrow = new Timestamp(cal.getTimeInMillis());
        
        // 统计订单信息
        String sql =
            "select supplier,sum(total) as total,sum(supplier_profit) as profit,count(*) as orderCount from cool_order "
                + "where finish_time>=:today and finish_time<:tomorrow and (state=3 or state=4) and supplier is not null group by supplier";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("today", today);
        paramMap.put("tomorrow", tomorrow);
        
        return coolJdbcTemplate.query(sql, paramMap, rowMapper);
    }
    
    @Override
    public List<ShopRanking> getLastRanking(String datetime) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("datetime", datetime);
        return coolJdbcTemplate.query("select s_id, ranking, order_ranking from coon_shop_ranking where createdate=:datetime",
            paramMap,
            lastRowMapper);
    }
    
    @Override
    public void batchSave(List<ShopRanking> shopRankingList) {
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(shopRankingList.toArray());
        coolJdbcTemplate.batchUpdate("insert into coon_shop_ranking(id,s_id,turnover_amount,turnover_orders,total_income,ranking,last_ranking,gap_amount,"
            + "order_ranking,last_order_ranking,day_amount,day_orders,day_income,createdate)"
            + " values(:id,:sId,:turnoverAmount,:turnoverOrders,:totalIncome,:ranking,:lastRanking,:gapAmount,:orderRanking,:lastOrderRanking,:dayAmount,:dayOrders,:dayIncome,:createdate)",
            batch);
    }
    
    @Override
    public void batchDelete() {
        coolJdbcTemplate.update("delete from coon_shop_ranking", new EmptySqlParameterSource());
    }
    
    @Override
    public void batchUpdate(List<ShopRanking> shopRankingList) {
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(shopRankingList.toArray());
        coolJdbcTemplate.batchUpdate("update coon_shop set y_income=:dayIncome,all_amount=:totalIncome,nwd_amount=:totalIncome-wd_amount where id=:sId",
            batch);
    }
    
    @Override
    public boolean isExistDate(String createdate) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("createdate", createdate);
        Integer count =
            coolJdbcTemplate.queryForObject("select count(*) from coon_shop_ranking where createdate=:createdate",
                paramMap,
                Integer.class);
        
        return count > 0;
    }
}
