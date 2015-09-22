package com.zebone.dnode.etl.convert.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * 字段配置对象
 *
 * @author 杨英
 * @version 2014-02-18 上午09:06
 */
public class ColumnParameter {

    @XStreamAlias("name")
    @XStreamAsAttribute
    private String columnName;

    @XStreamImplicit(itemFieldName="covert")
    private List<ConvertParameter> convertParameter;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public List<ConvertParameter> getConvertParameter() {
        return convertParameter;
    }

    public void setConvertParameter(List<ConvertParameter> convertParameter) {
        this.convertParameter = convertParameter;
    }
}
