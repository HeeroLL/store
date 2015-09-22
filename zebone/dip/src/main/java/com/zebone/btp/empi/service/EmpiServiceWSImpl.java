package com.zebone.btp.empi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zebone.btp.empi.mapper.EmpiUserMapper;
import com.zebone.btp.empi.vo.EmpiCard;
import com.zebone.btp.empi.vo.EmpiUser;

/**
 * EMPI 对外Web Service接口
 * 
 * @author ouyangxin CreateDate: 2013-3-8
 */
public class EmpiServiceWSImpl implements EmpiServiceWS {

	@Autowired
	private EmpiUserMapper empiUserMapper;

	@Override
	public EmpiUser getEmpiUserDetail(String empi) {

		EmpiUser user = empiUserMapper.loadEmpiUserByEmpi(empi);
		return user;
	}

	@Override
	public List<EmpiUser> queryEmpiByCode(String code, String cardType) {

		EmpiCard card = new EmpiCard();
		card.setCardNo(code);
		card.setCardType(cardType);

		List<EmpiUser> tempList = empiUserMapper.loadEmpiUserByCode(card);
		return tempList;
	}

}
