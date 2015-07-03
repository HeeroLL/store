package com.sell.demo;

import java.util.ArrayList;
import java.util.List;

import com.sell.core.base.BaseInfo;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

//设置Person类在xml中的别名
@XStreamAlias("person")
public class Person extends BaseInfo {
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -1575749176223446630L;
    
    // 将name设置为XML person 元素的 attribute
    @XStreamAsAttribute()
    private String name;
    
    // 不写默认会作为person的子元素
    private int phoneNuber;
    
    // 将此字段名在XML中去掉
    @XStreamImplicit()
    private List<Address> addresses = new ArrayList<Address>();
    
    public List<Address> getAddresses() {
        return addresses;
    }
    
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getPhoneNuber() {
        return phoneNuber;
    }
    
    public void setPhoneNuber(int phoneNuber) {
        this.phoneNuber = phoneNuber;
    }
    
}