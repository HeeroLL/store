package com.zebone.btp.empi.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.btp.empi.vo.EmpiCard;
import com.zebone.btp.empi.vo.EmpiUser;
import com.zebone.btp.empi.vo.QueryInfo;

@Mapper
public interface EmpiUserMapper {

	int addEmpiUser(EmpiUser empiUser);

	int cancelEmpiUser(EmpiUser empiUser);

	int logicDeleteEmpiUser(EmpiUser empiUser);
	
	int updateEmpiUser(EmpiUser empiUser);

	int getEmpiUserByEmpi(String icn);
	
	int removeEmpiUser(QueryInfo info);
	
	List<EmpiUser> queryEmpiUserList(RowBounds rowBounds, EmpiUser empiUser);
	
	int queryEmpiUserCount(EmpiUser empiUser);
	
	List<EmpiUser> queryEmpiUserList(EmpiUser empiUser);

	EmpiUser loadEmpiUser(EmpiUser empiUser);
	
	int updateSynEmpiUser(EmpiUser empiUser);
	
	/* WebService方式对外开放接口 */
	/** 根据EMPI查询基本信息和注册的卡信息 */
	EmpiUser loadEmpiUserByEmpi(String empi);
	
	/** 根据注册的卡号查询基本信息和注册的卡信息 */
	List<EmpiUser> loadEmpiUserByCode(EmpiCard empiCard);
}
