package com.zebone.server;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 文档注册代理类。将请求转发到数据中心
 * @author songjunjie
 * @version 2013-8-21 下午03:30:06
 */
@WebService
public interface EmpiRegisterProxy {
	/**
	 * 注册或更新文档
	 * @param registerXml
	 */
	public String empiRegister(@WebParam(name="registerXml")String registerXml);


    /**
     * 主索引查询
     * @param searchXml
     */
    public String searchEmpiInfo(@WebParam(name="searchXml")String searchXml);
}
