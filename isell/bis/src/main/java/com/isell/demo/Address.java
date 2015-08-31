package com.isell.demo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("address")
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
    
    private String street;
    
    private int houseNo;
    
    public String getStreet() {
        return street;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
    
    public int getHouseNo() {
        return houseNo;
    }
    
    public void setHouseNo(int houseNo) {
        this.houseNo = houseNo;
    }
}
