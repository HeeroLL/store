package com.zebone.dnode.etl.convert.pojo;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 数据清洗转换参数对象
 *
 * @author 杨英
 * @version 2014-02-18 上午09:10
 */
public class ConvertParameter {

    @XStreamAsAttribute
    private String type;

    @XStreamAsAttribute
    private String param;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
