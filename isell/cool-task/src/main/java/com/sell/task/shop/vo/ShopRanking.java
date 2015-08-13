package com.sell.task.shop.vo;


/**
 * 酷店排名信息
 * 
 * @author lilin
 * @version [版本号, 2015年8月1日]
 */
public class ShopRanking {
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
     * 总收入
     */
    private double totalIncome;
    
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
     * 总单数排名
     */
    private int orderRanking;
    
    /**
     * 昨日单数排名
     */
    private int lastOrderRanking;
    
    /**
     * 当日成交金额
     */
    private double dayAmount;
    
    /**
     * 当日成交单数
     */
    private int dayOrders;
    
    /**
     * 当日收入
     */
    private double dayIncome;
    
    /**
     * 统计日期(格式：yyyyMMdd)
     */
    private String createdate;
    
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

    public int getOrderRanking() {
        return orderRanking;
    }

    public void setOrderRanking(int orderRanking) {
        this.orderRanking = orderRanking;
    }

    public int getLastOrderRanking() {
        return lastOrderRanking;
    }

    public void setLastOrderRanking(int lastOrderRanking) {
        this.lastOrderRanking = lastOrderRanking;
    }

    public double getDayAmount() {
        return dayAmount;
    }

    public void setDayAmount(double dayAmount) {
        this.dayAmount = dayAmount;
    }

    public int getDayOrders() {
        return dayOrders;
    }

    public void setDayOrders(int dayOrders) {
        this.dayOrders = dayOrders;
    }

    public double getDayIncome() {
        return dayIncome;
    }

    public void setDayIncome(double dayIncome) {
        this.dayIncome = dayIncome;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
}
