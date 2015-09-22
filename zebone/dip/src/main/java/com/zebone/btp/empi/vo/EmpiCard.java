package com.zebone.btp.empi.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Card对象 对应 居民证件表EMPI_CARD
 * 
 * @author ouyangxin 2013-1-16
 */
public class EmpiCard implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Card 证件ID */
	private String cardId;
	
	/** Card 证件号码 */
	private String cardNo;
	
	/** Card 证件序列号 */
	private String cardSid;
	
	/** Card 证件类型 */
	private String cardType;
	
	/** Card 证件状态1正常 0注销 */
	private String cardState;
	
	/** Card 对应的EMPIID */
	private String empiId;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardSid() {
		return cardSid;
	}

	public void setCardSid(String cardSid) {
		this.cardSid = cardSid;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardState() {
		return cardState;
	}

	public void setCardState(String cardState) {
		this.cardState = cardState;
	}

	public String getEmpiId() {
		return empiId;
	}

	public void setEmpiId(String empiId) {
		this.empiId = empiId;
	}

    /**
     * 重写toString方法
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }


}
