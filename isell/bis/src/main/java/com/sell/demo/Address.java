package com.sell.demo;

import com.sell.core.base.BaseInfo;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("address")
public class Address extends BaseInfo {
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 7987281795739880643L;
    
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
