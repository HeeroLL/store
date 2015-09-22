package com.zebone.empi.vo;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ResidentCard {
  
    private String id;

    private String empi;

    @Size(min = 1, max = 50, message="CardNo")
    private String cardNo;

    @Size( max = 2, message="CardType")
    private String cardType;
  
    @Size(max=50, message="CardSerialNo")
    private String cardSerialNo;

    @NotNull
    @Size(max = 22, message="CardOrg")
    private String cardOrg;

    @Size(max = 1, message="CardLevel")
    private String cardLevel;

    private Date createDate;
    //卡状态 1 正常  2 注销
    private String cardStatus;
    //注销时间
    private Date cancelDate;
    //上传机构
    private String deptCode;
    //操作 1 新增， 2 更新
    private String operState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEmpi() {
        return empi;
    }

    public void setEmpi(String empi) {
        this.empi = empi == null ? null : empi.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getCardSerialNo() {
        return cardSerialNo;
    }

    public void setCardSerialNo(String cardSerialNo) {
        this.cardSerialNo = cardSerialNo == null ? null : cardSerialNo.trim();
    }

    public String getCardOrg() {
        return cardOrg;
    }

    public void setCardOrg(String cardOrg) {
        this.cardOrg = cardOrg == null ? null : cardOrg.trim();
    }

    public String getCardLevel() {
        return cardLevel;
    }

    public void setCardLevel(String cardLevel) {
        this.cardLevel = cardLevel == null ? null : cardLevel.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getOperState() {
        return operState;
    }

    public void setOperState(String operState) {
        this.operState = operState;
    }
}