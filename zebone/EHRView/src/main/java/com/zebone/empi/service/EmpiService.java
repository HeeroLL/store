package com.zebone.empi.service;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * EMPI接口
 * @author YinCm
 * @version 2013-7-31 下午10:10:20
 */
@WebService
public interface EmpiService {
	
	/**
	 * empi注册
	 * @param doc EMPI注册文档
	 */
	//public void registerEmpi(ResidentInfo residentInfo);
	
	/**
	 * 更新主索引,EmpiInfo必须要含有Empi值，
	 * card如果不含有id且数据库中没有对应的type和id_code则不进行操作，
	 * 含有id的执行补空更新
	 * @param doc 参见EMPI注册文档
	 */
	//public void updateEmpi(ResidentInfo residentInfo);
	
	/**
	 * 注册更新服务
	 * @param empiInfo 参见EMPI注册文档
	 * @return 格式: 错误代码|错误信息|错误位置 。 错误代码0为失败，错误代码1为成功 
	 */
	public String registerOrUpdate(@WebParam(name="registerXml")String empiInfo);
	
	/**
	 * 根据查询条件查询出EMPI主标识
	 * @param paramXml
	 * @return
	 * @throws Exception 
	 */
	public String searchEmpiId(@WebParam(name="paramXml")String paramXml)  throws Exception;
	
	/**
	 * 根据查询条件查询出EMPI对象的xml字符串
	 * @param paramXml
	 * @return
	 */
	public String searchEmpiInfo(@WebParam(name="paramXml")String paramXml)  throws Exception;
}
