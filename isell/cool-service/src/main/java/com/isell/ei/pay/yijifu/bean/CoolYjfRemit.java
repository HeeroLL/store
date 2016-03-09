package com.isell.ei.pay.yijifu.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 易极付跨境汇款申请
 * 
 * @author lilin
 * @version [版本号, 2016年2月23日]
 */
public class CoolYjfRemit {
    /**
     * id
     */
    private Integer id;
    
    /**
     * 跨境付款批次号
     */
    private String remittranceBatchno;
    
    /**
     * 汇款金额
     */
    private BigDecimal payAmount;
    
    /**
     * 汇款申请状态
     */
    private String status;
    
    /**
     * 汇款说明信息
     */
    private String message;
    
    /**
     * 处理结果列表
     */
    private String resultinfos;
    
    /**
     * 创建时间
     */
    private Date createtime;
    
    /**
     * id
     */
    public Integer getId() {
        return this.id;
    }
    
    /**
     * id
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * 跨境付款批次号
     */
    public String getRemittranceBatchno() {
        return this.remittranceBatchno;
    }
    
    /**
     * 跨境付款批次号
     */
    public void setRemittranceBatchno(String remittranceBatchno) {
        this.remittranceBatchno = remittranceBatchno;
    }
    
    /**
     * 汇款金额
     */
    public BigDecimal getPayAmount() {
        return this.payAmount;
    }
    
    /**
     * 汇款金额
     */
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }
    
    /**
     * 汇款申请状态
     */
    public String getStatus() {
        return this.status;
    }
    
    /**
     * 汇款申请状态
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * 汇款说明信息
     */
    public String getMessage() {
        return this.message;
    }
    
    /**
     * 汇款说明信息
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * 处理结果列表
     */
    public String getResultinfos() {
        return this.resultinfos;
    }
    
    /**
     * 处理结果列表
     */
    public void setResultinfos(String resultinfos) {
        this.resultinfos = resultinfos;
    }
    
    /**
     * 创建时间
     */
    public Date getCreatetime() {
        return this.createtime;
    }
    
    /**
     * 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}