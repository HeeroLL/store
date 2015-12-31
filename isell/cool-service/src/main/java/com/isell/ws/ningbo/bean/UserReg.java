package com.isell.ws.ningbo.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 宁波优贝信息--订单参数--UserReg用户注册信息
 * 
 * @author wangpeng
 * @version [版本号, 2015年11月3日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {})
public class UserReg {
    
    /**
     * 身份证号码[可空]
     */
    @XmlElement(name = "Idnum")
    private String idnum;
    
    /**
     * 真实姓名[可空]
     */
    @XmlElement(name = "Name")
    private String name;
    
    /**
     * 用户手机[可空]
     */
    @XmlElement(name = "Phone")
    private String phone;
    
    /**
     * 用户邮箱[可空]
     */
    @XmlElement(name = "Email")
    private String email;
    
    public String getIdnum() {
        return idnum;
    }
    
    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}
