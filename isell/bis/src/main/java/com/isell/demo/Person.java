package com.isell.demo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("person")
// 设置Person类在xml中的别名
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    
    // 将name设置为XML person 元素的 attribute
    @XmlAttribute
    @XStreamAsAttribute
    private String name;
    
    // 不写默认会作为person的子元素
    private int phoneNuber;
    
    @XStreamImplicit()
    // 将此字段名在XML中去掉
    @XmlElement(name = "address")
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