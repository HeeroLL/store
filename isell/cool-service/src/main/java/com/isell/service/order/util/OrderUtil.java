package com.isell.service.order.util;

import java.util.Date;

import com.isell.core.util.DateUtil;

/**
 * 订单工具类
 * 
 * @author lilin
 * @version [版本号, 2016年2月3日]
 */
public final class OrderUtil {
    /**
     * 生成订单号
     * 
     * @param gid 商品或规格id
     * @return 订单号
     */
    public static String generateOrderNo(Integer gid) {
        String date = DateUtil.dateToStr(DateUtil.yyyyMMddHHmmssSSS, new Date());
        
        StringBuilder builder = new StringBuilder();
        builder.append("CO"); // CO开头
        builder.append(date.substring(0, 8)); // 年月日
        String str = null;
        if (gid == null) {
            str = (int)(Math.random() * 9000 + 1000) + "";
        } else {
            str = gid + "";
            while (str.length() < 4) {
                str = "0" + str;
            }
            if (str.length() > 4) {
                str = str.substring(str.length() - 4);
            }
        }
        builder.append(str); // 4位商品编码
        builder.append(date.substring(8)); // 时分秒
        builder.append((int)(Math.random() * 9000 + 1000)); // 4位随机数
        
        return builder.toString();
    }
}
