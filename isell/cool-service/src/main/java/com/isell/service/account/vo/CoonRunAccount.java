package com.isell.service.account.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.isell.core.mybatis.page.PageConfig;

/**
 * 
 * 账单流水记录表vo
 * 
 * @author wangpegn
 * @version [版本号, 2015-10-04]
 */
public class CoonRunAccount extends PageConfig{
	/**
     * 0.收入 1.支出
     */
    public static final Integer TYPE_0 = 0;
    
    /**
     * 0.收入 1.支出
     */
    public static final Integer TYPE_1 = 1;
    
    /**
     * 提现状态 0.未提现.1已提现3.收入4转到体现中5.退款6.奖励
     */
    public static final Integer WITHDRAW_STATE_0 = 0;
    
    /**
     * 提现状态 0.未提现.1已提现3.收入4转到体现中5.退款6.奖励
     */
    public static final Integer WITHDRAW_STATE_1 = 1;
    
    /**
     * 提现状态 0.未提现.1已提现3.收入4转到体现中5.退款6.奖励
     */
    public static final Integer WITHDRAW_STATE_3 = 3;
    
    /**
     * 提现状态 0.未提现.1已提现3.收入4转到体现中5.退款6.奖励
     */
    public static final Integer WITHDRAW_STATE_4 = 4;
    
    /**
     * 提现状态 0.未提现.1已提现3.收入4转到体现中5.退款6.奖励
     */
    public static final Integer WITHDRAW_STATE_5 = 5;
    
    /**
     * 提现状态 0.未提现.1已提现3.收入4转到体现中5.退款6.奖励
     */
    public static final Integer WITHDRAW_STATE_6 = 6;
    
    /**
     * 0 支付宝
     */
    public static final Integer WITHDRAW_TYPE_0 = 0;
    
    /**
     * 0未冻结1冻结
     */
    public static final Byte IS_FREEZE_0 = 0;
    
    /**
     * 0未冻结1冻结
     */
    public static final Byte IS_FREEZE_1 = 1;
	
    /**
     * 主键ID
     */
    private String id;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 0.收入 1.支出
     */
    private Integer type;
    /**
     * 交易金额
     */
    private BigDecimal amount;
    /**
     * 手续费
     */
    private BigDecimal fee;
    /**
     * 交易时间
     */
    private Date createTime;
    /**
     * 交易流水号
     */
    private String serialNumber;
    /**
     * 0.未提现.1已提现3.收入4转到体现中5.退款6.奖励
     */
    private Integer withdrawState;
    /**
     * 0 支付宝 
     */
    private Integer withdrawType;
    /**
     * 提现姓名
     */
    private String withdrawName;
    /**
     * 完成时间
     */
    private Date finishTime;
    /**
     * 支付账号
     */
    private String withdrawNum;
    /**
     * 批量付款批次号
     */
    private String batchNo;
    /**
     * 订单来源
     */
    private String orderNo;
    /**
     * 备注
     */
    private String maker;
    /**
     * 0未冻结1冻结
     */
    private Byte isFreeze;
    /**
     * 冻解时间
     */
    private Date freezeTime;
    /**
     * 订单店铺主键
     */
    private String shopId;
    /**
     * 订单店铺名称
     */
    private String shopName;
    /**
     * 是否显示 0或空：显示；1：不显示
     */
    private Byte isDel;
    
    /**
     * 1级分销2级分销3级分销
     */
    private String devide;
    
    /**
     * 流水状态 1：可提现 2：即将可用
     */
    private String state;

    /**
     * 主键ID
     */
    public String getId(){
        return this.id;
    }

    /**
     * 主键ID
     */
    public void setId(String id){
        this.id = id;
    }    
    /**
     * 用户ID
     */
    public String getUserId(){
        return this.userId;
    }

    /**
     * 用户ID
     */
    public void setUserId(String userId){
        this.userId = userId;
    }    
    /**
     * 0.收入 1.支出
     */
    public Integer getType(){
        return this.type;
    }

    /**
     * 0.收入 1.支出
     */
    public void setType(Integer type){
        this.type = type;
    }    
    /**
     * 交易金额
     */
    public BigDecimal getAmount(){
        return this.amount;
    }

    /**
     * 交易金额
     */
    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }    
    /**
     * 手续费
     */
    public BigDecimal getFee(){
        return this.fee;
    }

    /**
     * 手续费
     */
    public void setFee(BigDecimal fee){
        this.fee = fee;
    }    
    /**
     * 交易时间
     */
    public Date getCreateTime(){
        return this.createTime;
    }

    /**
     * 交易时间
     */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }    
    /**
     * 交易流水号
     */
    public String getSerialNumber(){
        return this.serialNumber;
    }

    /**
     * 交易流水号
     */
    public void setSerialNumber(String serialNumber){
        this.serialNumber = serialNumber;
    }    
    /**
     * 0.未提现.1已提现3.收入4转到体现中5.退款6.奖励
     */
    public Integer getWithdrawState(){
        return this.withdrawState;
    }

    /**
     * 0.未提现.1已提现3.收入4转到体现中5.退款6.奖励
     */
    public void setWithdrawState(Integer withdrawState){
        this.withdrawState = withdrawState;
    }    
    /**
     * 0 支付宝 
     */
    public Integer getWithdrawType(){
        return this.withdrawType;
    }

    /**
     * 0 支付宝 
     */
    public void setWithdrawType(Integer withdrawType){
        this.withdrawType = withdrawType;
    }    
    /**
     * 提现姓名
     */
    public String getWithdrawName(){
        return this.withdrawName;
    }

    /**
     * 提现姓名
     */
    public void setWithdrawName(String withdrawName){
        this.withdrawName = withdrawName;
    }    
    /**
     * 完成时间
     */
    public Date getFinishTime(){
        return this.finishTime;
    }

    /**
     * 完成时间
     */
    public void setFinishTime(Date finishTime){
        this.finishTime = finishTime;
    }    
    /**
     * 支付账号
     */
    public String getWithdrawNum(){
        return this.withdrawNum;
    }

    /**
     * 支付账号
     */
    public void setWithdrawNum(String withdrawNum){
        this.withdrawNum = withdrawNum;
    }    
    /**
     * 批量付款批次号
     */
    public String getBatchNo(){
        return this.batchNo;
    }

    /**
     * 批量付款批次号
     */
    public void setBatchNo(String batchNo){
        this.batchNo = batchNo;
    }    
    /**
     * 订单来源
     */
    public String getOrderNo(){
        return this.orderNo;
    }

    /**
     * 订单来源
     */
    public void setOrderNo(String orderNo){
        this.orderNo = orderNo;
    }    
    /**
     * 备注
     */
    public String getMaker(){
        return this.maker;
    }

    /**
     * 备注
     */
    public void setMaker(String maker){
        this.maker = maker;
    }    
    /**
     * 0未冻结1冻结
     */
    public Byte getIsFreeze(){
        return this.isFreeze;
    }

    /**
     * 0未冻结1冻结
     */
    public void setIsFreeze(Byte isFreeze){
        this.isFreeze = isFreeze;
    }    
    /**
     * 冻解时间
     */
    public Date getFreezeTime(){
        return this.freezeTime;
    }

    /**
     * 冻解时间
     */
    public void setFreezeTime(Date freezeTime){
        this.freezeTime = freezeTime;
    }    
    /**
     * 订单店铺主键
     */
    public String getShopId(){
        return this.shopId;
    }

    /**
     * 订单店铺主键
     */
    public void setShopId(String shopId){
        this.shopId = shopId;
    }    
    /**
     * 订单店铺名称
     */
    public String getShopName(){
        return this.shopName;
    }

    /**
     * 订单店铺名称
     */
    public void setShopName(String shopName){
        this.shopName = shopName;
    }    
    /**
     * 是否显示 0或空：显示；1：不显示
     */
    public Byte getIsDel(){
        return this.isDel;
    }

    /**
     * 是否显示 0或空：显示；1：不显示
     */
    public void setIsDel(Byte isDel){
        this.isDel = isDel;
    }

	public String getDevide() {
		return devide;
	}

	public void setDevide(String devide) {
		this.devide = devide;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}    
}