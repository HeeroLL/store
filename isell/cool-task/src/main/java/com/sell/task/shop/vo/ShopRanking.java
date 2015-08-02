package com.sell.task.shop.vo;


/**
 * 酷店排名信息
 * 
 * @author lilin
 * @version [版本号, 2015年8月1日]
 */
public class ShopRanking implements Comparable<ShopRanking> {
    /**
     * 酷店id
     */
    private String sId;
    
    /**
     * 成交金额
     */
    private double turnoverAmount;
    
    /**
     * 成交单数
     */
    private int turnoverOrders;
    
    /**
     * 排名
     */
    private int ranking;
    
    /**
     * 上次排名
     */
    private int lastRanking;
    
    /**
     * 和上一位差距
     */
    private double gapAmount;
    
    /**
     * 收入
     */
    private double income;
    
    /**
     * 昨天收入
     */
    private double yestodayIncome;
    
    /**
     * 按交易总额从大到小排序
     * 
     * @param other other
     * @return int
     */
    @Override
    public int compareTo(ShopRanking other) {
        return (int)((other.getTurnoverAmount() - this.turnoverAmount) * 100);
    }
    
    public String getsId() {
        return sId;
    }
    
    public void setsId(String sId) {
        this.sId = sId;
    }
    
    public double getTurnoverAmount() {
        return turnoverAmount;
    }
    
    public void setTurnoverAmount(double turnoverAmount) {
        this.turnoverAmount = turnoverAmount;
    }
    
    public int getTurnoverOrders() {
        return turnoverOrders;
    }
    
    public void setTurnoverOrders(int turnoverOrders) {
        this.turnoverOrders = turnoverOrders;
    }
    
    public int getRanking() {
        return ranking;
    }
    
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
    
    public int getLastRanking() {
        return lastRanking;
    }
    
    public void setLastRanking(int lastRanking) {
        this.lastRanking = lastRanking;
    }
    
    public double getGapAmount() {
        return gapAmount;
    }
    
    public void setGapAmount(double gapAmount) {
        this.gapAmount = gapAmount;
    }
    
    public double getIncome() {
        return income;
    }
    
    public void setIncome(double income) {
        this.income = income;
    }

    public double getYestodayIncome() {
        return yestodayIncome;
    }

    public void setYestodayIncome(double yestodayIncome) {
        this.yestodayIncome = yestodayIncome;
    }
}
