package com.star.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.support.JdbcUtils;

/**
 * 
 * 处理MYSQL的TINYINT类型转为boolean型的问题
 * 
 * @author lilin
 * @version [版本号, 2016年3月31日]
 */
public class MyColumnMapRowMapper extends ColumnMapRowMapper {

    @Override
    public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        Map<String, Object> mapOfColValues = createColumnMap(columnCount);
        for (int i = 1; i <= columnCount; i++) {

            String key = getColumnKey(JdbcUtils.lookupColumnName(rsmd, i));
            Object obj = null;
            if (rsmd.getColumnTypeName(i).equals("TINYINT")) {
                obj = rs.getInt(i);
            } else {
                obj = getColumnValue(rs, i);
            }
            mapOfColValues.put(key, obj);

        }
        return mapOfColValues;
    }
}
