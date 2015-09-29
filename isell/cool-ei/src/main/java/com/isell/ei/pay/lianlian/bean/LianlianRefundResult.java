package com.isell.ei.pay.lianlian.bean;

/**
 * 连连退款响应信息
 * 
 * @author lilin
 * @version [版本号, 2015年8月28日]
 */
public class LianlianRefundResult {
    /**
     * 连连支付单号
     */
    private String oid_billno;
    
    /**
     * 原收款方id
     */
    private String col_custid;
    
    /**
     * 退款金额
     */
    private String col_amt_refund;
    
    /**
     * 退款结果码
     */
    private String retcode;
    
    /**
     * 退款中文提示
     */
    private String retmsg;

    public String getOid_billno() {
        return oid_billno;
    }

    public void setOid_billno(String oid_billno) {
        this.oid_billno = oid_billno;
    }

    public String getCol_custid() {
        return col_custid;
    }

    public void setCol_custid(String col_custid) {
        this.col_custid = col_custid;
    }

    public String getCol_amt_refund() {
        return col_amt_refund;
    }

    public void setCol_amt_refund(String col_amt_refund) {
        this.col_amt_refund = col_amt_refund;
    }

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public String getRetmsg() {
        return retmsg;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }
}
