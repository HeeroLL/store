package com.isell.ei.logistics.zyb.bean;


/**
 * 转运帮结果封装类
 * 
 * @author lilin
 * @version [版本号, 2015年10月27日]
 */
public class ZybParam {
    /**
     * 用户客户代码，4位大写英文字母
     */
    private String user;
    
    /**
     * 数据内容，需要进行BASE64编码
     */
    private String content;
    
    /**
     * 数据签名（校验码）
     */
    private String sign;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
