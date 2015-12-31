package com.isell.service.common;

import java.math.BigDecimal;


/**
 * 常量定义接口
 * 
 * @author wangpeng
 *
 */
public class GeneralDef {
	
	/**圆通B模式拉取电子面单号 待修改*/
    public static String YUANTONG_SURFACE_URL;
    
    /**
     * 推荐店铺分佣比例
     */
    public static BigDecimal RECOMMEND_RATE = new BigDecimal("0.25");
    

	/**
	 * 每日最大提现金额
	 */
	public static final BigDecimal MAX_WITHDRAW_MONEY = new BigDecimal(1000);
	
	/**
	 * 通用标示
	 */
	public static final Byte BYTE_0 = 0;
	
	/**
	 * 通用标示
	 */
	public static final Byte BYTE_1 = 1;
	
	

}
