package com.zebone.dnode.etl.check.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;


public class VerifyParameter {

    @XStreamImplicit(itemFieldName="table")
    private List<TableParameter> tableParameter;

    public List<TableParameter> getTableParameter() {
        return tableParameter;
    }

    public void setTableParameter(List<TableParameter> tableParameter) {
        this.tableParameter = tableParameter;
    }
}
