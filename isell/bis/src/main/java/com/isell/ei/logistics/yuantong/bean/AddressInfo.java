package com.isell.ei.logistics.yuantong.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * 地址信息
 * 
 * @author lilin
 * @version [版本号, 2015年9月7日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressInfo {
    /** 用户姓名 */
    private String name;
    
    /** 用户邮编 */
    private int postCode;
    
    /** 用户电话，包括区号、电话号码及分机号，中间用“-”分隔； */
    private String phone;
    
    /** 用户移动电话 */
    private String mobile;
    
    /** 用户所在省 */
    private String prov;
    
    /** 用户所在市县（区），市区中间用英文“,”分隔；注意有些市下面是没有区 */
    private String city;
    
    /** 用户详细地址 */
    private String address;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getPostCode() {
        return postCode;
    }
    
    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getProv() {
        return prov;
    }
    
    public void setProv(String prov) {
        this.prov = prov;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
}
