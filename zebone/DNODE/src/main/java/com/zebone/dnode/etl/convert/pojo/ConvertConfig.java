package com.zebone.dnode.etl.convert.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 数据清洗转换配置对象
 *
 * @author 杨英
 * @version 2014-02-18 上午08:56
 */
@XStreamAlias("cleanConf")
public class ConvertConfig {

    @XStreamAlias("clean")
    private CleanParameter cleanParameter;

    public CleanParameter getCleanParameter() {
        return cleanParameter;
    }

    public void setCleanParameter(CleanParameter cleanParameter) {
        this.cleanParameter = cleanParameter;
    }
}
