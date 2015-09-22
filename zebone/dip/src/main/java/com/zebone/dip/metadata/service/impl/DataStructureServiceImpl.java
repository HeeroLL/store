package com.zebone.dip.metadata.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.metadata.dao.FieldColumnConfMapper;
import com.zebone.dip.metadata.dao.FieldTableConfMapper;
import com.zebone.dip.metadata.service.DataStructureService;
import com.zebone.dip.metadata.vo.FieldColumnConf;
import com.zebone.dip.metadata.vo.FieldTableConf;
import com.zebone.util.DateUtil;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-6-26
 * Time: 上午11:17
 * To change this template use File | Settings | File Templates.
 */

@Service("dataStructureService")
public class DataStructureServiceImpl implements DataStructureService{

    @Resource
    private FieldTableConfMapper fieldTableConfMapper;

    @Resource
    private FieldColumnConfMapper fieldColumnConfMapper;

    @Override
    public Pagination<FieldTableConf> tableStruPage(Pagination<FieldTableConf> page, FieldTableConf fieldTableConf) {
        List<FieldTableConf> list = fieldTableConfMapper.tableSturList(page.getRowBounds(),fieldTableConf);
        int totalCount = fieldTableConfMapper.tableStruTotalCount(fieldTableConf);
        page.setData(list);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public List<FieldTableConf> getTableStruInfo() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public FieldTableConf getTableStruInfoById(String id) {
        return fieldTableConfMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveTableStruInfo(FieldTableConf fieldTableConf) {
        int countTableName = fieldTableConfMapper.countTableName(fieldTableConf);
        if (countTableName > 0) { //说明该表名已经存在，不能再保存相同的表名
            return -1;
        } else {
            String createTime = DateUtil.getCurrentDate("yyyyMMddHHmmss");
            fieldTableConf.setCreateTime(createTime);
            return fieldTableConfMapper.saveTableStruInfo(fieldTableConf);
        }
    }

    @Override
    public int updateTableStruInfo(FieldTableConf fieldTableConf) {
        int countTableName = fieldTableConfMapper.countTableName(fieldTableConf);
        if(countTableName > 0){  //说明该表名已经存在，不能再保存相同的表名
            return -1;
        } else {
            String createTime = DateUtil.getCurrentDate("yyyyMMddHHmmss");
            fieldTableConf.setCreateTime(createTime);
            return fieldTableConfMapper.updateTableStruInfo(fieldTableConf);
        }
    }

    @Override
    public int removeTableStruByIds(String[] ids) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < ids.length; i++) {
            int count = fieldTableConfMapper.columnExistsDataSet(ids[i]);
            if (count < 1) {
                list.add(ids[i]);
            }
        }
        if (list.size() < 1) {
            return 2;
        } else {
            int count1 = fieldTableConfMapper.deleteTableStruByIds(list);
            if (count1 > 0) return 1;
        }
        return 0;
    }

    @Override
    public Pagination<FieldColumnConf> tableFieldPage(Pagination<FieldColumnConf> page, FieldColumnConf fieldColumnConf) {
        List<FieldColumnConf> list = fieldColumnConfMapper.tableFieldList(page.getRowBounds(),fieldColumnConf);
        int totalCount = fieldColumnConfMapper.tableFieldTotalCount(fieldColumnConf);
        page.setData(list);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public int saveTableFieldInfo(FieldColumnConf fieldColumnConf) {
        int columnNameCount = fieldColumnConfMapper.columnNameCount(fieldColumnConf);
        if(columnNameCount > 0){  //说明该字段名已经存在，不能再保存相同的字段
            return -1;
        } else {
            String createTime = DateUtil.getCurrentDate("yyyyMMddHHmmss");
            fieldColumnConf.setCreateTime(createTime);
            return fieldColumnConfMapper.saveTableFieldInfo(fieldColumnConf);
        }
    }

    @Override
    public int updateTableFieldInfo(FieldColumnConf fieldColumnConf) {
        int columnNameCount = fieldColumnConfMapper.columnNameCount(fieldColumnConf);
        if (columnNameCount > 0) {  //说明该字段名已经存在，不能再保存相同的字段
            return -1;
        } else {
            String createTime = DateUtil.getCurrentDate("yyyyMMddHHmmss");
            fieldColumnConf.setCreateTime(createTime);
            return fieldColumnConfMapper.updateTableFieldInfo(fieldColumnConf);
        }
    }

    @Override
    public FieldColumnConf getTableFieldInfoById(String id) {
        return fieldColumnConfMapper.selectByPrimaryKey(id);
    }

    @Override
    public int removeTableFieldByIds(String[] ids) {
        List<String> list = new ArrayList<String>(Arrays.asList(ids));
        if (list.size() < 1) {
            return 2;
        } else {
            int count1 = fieldColumnConfMapper.deleteTableFieldByIds(list);
            if (count1 > 0) return 1;
        }
        return 0;
    }

    @Override
    public List<FieldColumnConf> getTableFieldInfo() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
