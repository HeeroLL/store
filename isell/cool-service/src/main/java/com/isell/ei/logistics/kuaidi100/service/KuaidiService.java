package com.isell.ei.logistics.kuaidi100.service;

import com.isell.core.util.JsonData;

/**
 * 快递100查询接口
 * 
 * @author lilin
 * @version [版本号, 2015年8月9日]
 */
public interface KuaidiService {
    /**
     * 快递100身份授权key
     */
    String KEY = "0eb82b5972514d80";
    
    /**
     * api接口URL
     */
    String API_URL = "http://api.kuaidi100.com/api";
    
    /**
     * web页面URL
     */
    String WEB_URL = "http://www.kuaidi100.com/applyurl";
    
    /**
     * wap页面URL
     */
    String WAP_URL = "http://m.kuaidi100.com/index_all.html";
    
    /**
     * 快递查询接口，返回json格式
     * 
     * @param com 快递公司代码
     * @param nu 快递单号
     * @return 查询结果
     */
    @Deprecated
    JsonData jsonService(String com, String nu);
    
    /**
     * 根据邮寄公司 返回邮寄公司编码
     * 
     * @param name 快递公司名称
     * @return 快递公司编号
     */
    String queryPostTypeByName(String name);
    
    /**
     * 快递查询接口，返回web页面
     * 
     * @param com 快递公司代码
     * @param nu 快递单号
     * @return 页面
     */
    String webService(String com, String nu);
    
    /**
     * 已弃用。快递100不支持这种调用方式，请直接通过get方式访问快递100wap查询界面 <br>
     * 快递查询接口，返回wap页面(html5页面)
     * 
     * @param type 快递公司代码
     * @param postid 快递单号
     * @param callbackurl 点击"返回"跳转的地址
     * @return 页面
     */
    String wapService(String type, String postid, String callbackurl);
}
