package com.zebone.store;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 文档查询接口
 * 蔡祥龙
 * 2013-08-24
 */
@WebService
public interface DocQuery {
	/**
	 * @author caixl
	 * @date Aug 24, 2013
	 * @description TODO 查询文档数据
	 * @param xml 
	 * @return String 返回调阅数据
	 */
	String DocumentRepository_RetrieveDocumentSet(@WebParam(name="xml")String xml);
}
