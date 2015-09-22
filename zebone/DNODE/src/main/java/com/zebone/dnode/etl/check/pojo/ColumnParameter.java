package com.zebone.dnode.etl.check.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * 字段配置对象
 *
 * @author 杨英
 * @version 2014-02-14 下午02:15
 */
public class ColumnParameter {

    @XStreamAlias("name")
    @XStreamAsAttribute
    private String columnName;

    @XStreamImplicit(itemFieldName="check")
    private List<CheckParameter> checkParameter;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public List<CheckParameter> getCheckParameter() {
        return checkParameter;
    }

    public void setCheckParameter(List<CheckParameter> checkParameter) {
        this.checkParameter = checkParameter;
    }
}
