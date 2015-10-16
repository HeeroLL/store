package com.isell.core.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * 数据库记录类
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-07]
 */
public class Record implements Serializable {
    
    private static final long serialVersionUID = 905784513600884082L;
    
    private Map<String, Object> columns;
    
    public Record() {
        columns = new HashMap<String, Object>();
    }
    
    public Record setColumns(Record record) {
        getColumns().putAll(record.getColumns());
        return this;
    }
    
    public Record remove(String column) {
        getColumns().remove(column);
        return this;
    }
    
    public Record removeNullValueColumns() {
        for (Iterator<Entry<String, Object>> it = getColumns().entrySet().iterator(); it.hasNext();) {
            Entry<String, Object> e = it.next();
            if (e.getValue() == null)
                it.remove();
        }
        
        return this;
    }
    
    public Record clear() {
        getColumns().clear();
        return this;
    }
    
    public Record set(String column, Object value) {
        getColumns().put(column, value);
        return this;
    }
    
    public Object get(String column) {
        return getColumns().get(column);
    }
    
    public Object get(String column, Object defaultValue) {
        Object result = getColumns().get(column);
        return result == null ? defaultValue : result;
    }
    
    public String getStr(String column) {
        return (String)getColumns().get(column);
    }
    
    public Integer getInt(String column) {
        return (Integer)getColumns().get(column);
    }
    
    public Long getLong(String column) {
        return (Long)getColumns().get(column);
    }
    
    public BigInteger getBigInteger(String column) {
        return (BigInteger)getColumns().get(column);
    }
    
    public Date getDate(String column) {
        return (Date)getColumns().get(column);
    }
    
    public Time getTime(String column) {
        return (Time)getColumns().get(column);
    }
    
    public Timestamp getTimestamp(String column) {
        return (Timestamp)getColumns().get(column);
    }
    
    public Double getDouble(String column) {
        return (Double)getColumns().get(column);
    }
    
    public Float getFloat(String column) {
        return (Float)getColumns().get(column);
    }
    
    public Boolean getBoolean(String column) {
        return (Boolean)getColumns().get(column);
    }
    
    public BigDecimal getBigDecimal(String column) {
        return (BigDecimal)getColumns().get(column);
    }
    
    public byte[] getBytes(String column) {
        return (byte[])getColumns().get(column);
    }
    
    public Number getNumber(String column) {
        return (Number)getColumns().get(column);
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(" {");
        boolean first = true;
        Entry<String, Object> e;
        Object value;
        for (Iterator<Entry<String, Object>> iterator = getColumns().entrySet().iterator(); iterator.hasNext(); sb.append((String)e.getKey())
            .append(":")
            .append(value)) {
            e = iterator.next();
            if (first)
                first = false;
            else
                sb.append(", ");
            value = e.getValue();
            if (value != null)
                value = value.toString();
        }
        
        sb.append("}");
        return sb.toString();
    }
    
    public void setColumns(Map<String, Object> columns) {
        this.columns = columns;
    }
    
    public Map<String, Object> getColumns() {
        return columns;
    }
    
}
