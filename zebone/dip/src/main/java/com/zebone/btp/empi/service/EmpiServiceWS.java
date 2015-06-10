package com.zebone.btp.empi.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.zebone.btp.empi.vo.EmpiUser;

/**
 * EMPI 对外Web Service接口
 * 
 * @author ouyangxin
 * 
 * CreateDate: 2013-3-8
 */
@WebService
public interface EmpiServiceWS {

	/**
	 * 根据EMPI查询基本信息和注册的卡信息
	 * 
	 * @param empi
	 * @return
	 */
	@WebMethod
	EmpiUser getEmpiUserDetail(String empi);

	/**
	 * 
	 * 根据注册的卡号查询基本信息和注册的卡信息
	 * 
	 * @param code
	 * @param cardType
	 * @return
	 */
	@WebMethod
	List<EmpiUser> queryEmpiByCode(String code, String cardType);
	

}
