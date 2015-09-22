package com.zebone.dip.metadata.service;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.metadata.vo.FieldColumnConf;
import com.zebone.dip.metadata.vo.FieldTableConf;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yang ying
 * Date: 13-6-26
 * Time: 上午11:16
 * To change this template use File | Settings | File Templates.
 */
public interface DataStructureService {

    //表结构管理相关方法
    Pagination<FieldTableConf> tableStruPage(Pagination<FieldTableConf> page,FieldTableConf fieldTableConf);

    List<FieldTableConf> getTableStruInfo();

    FieldTableConf getTableStruInfoById (String id);

    int saveTableStruInfo(FieldTableConf fieldTableConf);

    int updateTableStruInfo(FieldTableConf fieldTableConf);

    int removeTableStruByIds (String[] ids);

    //表字段管理相关方法
    Pagination<FieldColumnConf> tableFieldPage(Pagination<FieldColumnConf> page,FieldColumnConf fieldColumnConf);

    int saveTableFieldInfo(FieldColumnConf fieldColumnConf);

    int updateTableFieldInfo(FieldColumnConf fieldColumnConf);

    FieldColumnConf getTableFieldInfoById (String id);

    int removeTableFieldByIds(String[] ids);

    List<FieldColumnConf> getTableFieldInfo();

}
