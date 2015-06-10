package com.zebone.dnode.etl.check.pojo;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 数据验证参数对象
 *
 * @author 杨英
 * @version 2014-02-14 下午02:20
 */
public class CheckParameter {

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
