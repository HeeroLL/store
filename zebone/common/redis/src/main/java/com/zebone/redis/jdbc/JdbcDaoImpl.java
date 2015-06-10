package com.zebone.redis.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.zebone.util.ReflectUtil;

/**
 * 
 * 查询关系型数据库接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年4月28日]
 */
public class JdbcDaoImpl implements JdbcDao {
    /**
     * log4j日志
     */
    private static final Logger log = Logger.getLogger(JdbcDaoImpl.class);
    
    /**
     * jdbcTemplate
     */
    private JdbcTemplate jdbcTemplate;
    
    /**
     * stringRowMapper
     */
    private final RowMapper<String> stringRowMapper = new RowMapper<String>() {
        @Override
        public String mapRow(ResultSet rs, int arg1)
            throws SQLException {
            // select name from table where id = ?
            return rs.getString(1);
        }
    };
    
    /**
     * stringRowMapper
     */
    private final RowMapper<Map<String, String>> mapRowMapper = new RowMapper<Map<String, String>>() {
        @Override
        public Map<String, String> mapRow(ResultSet rs, int arg1)
            throws SQLException {
            // select name, id from table where id in (?) 或 select name, id from table
            Map<String, String> map = new HashMap<String, String>();
            map.put(rs.getString(2), rs.getString(1));
            return map;
        }
    };
    
    /**
     * 根据sql和id获取对应的value
     * 
     * @param sql sql
     * @param id id
     * @return value
     */
    @Override
    public String getStringValue(String sql, String id) {
        try {
            return jdbcTemplate.queryForObject(sql, stringRowMapper, id);
        }
        catch (EmptyResultDataAccessException e) {
            log.warn("Sql '" + sql + "' return null data, id is " + id, e);
            return null;
        }
    }
    
    /**
     * 根据sql和id获取对应的对象
     * 
     * @param sql sql
     * @param id id
     * @param c class
     * @return object
     */
    @Override
    public Object getObjectValue(String sql, String id, Class<?> c) {
        try {
            Map<String, Object> map = jdbcTemplate.queryForMap(sql, id);
            return ReflectUtil.convertMapToObject(map, c);
        }
        catch (EmptyResultDataAccessException e) {
            log.warn("Sql '" + sql + "' return null data, id is " + id, e);
            return null;
        }
    }
    
    /**
     * 根据sql和参数批量获取数据
     * 
     * @param sql sql
     * @param args 参数
     * @return List
     */
    @Override
    public List<Map<String, String>> getMapList(String sql, Object... args) {
        return jdbcTemplate.query(sql, mapRowMapper, args);
    }
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
