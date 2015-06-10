package com.zebone.btp.empi.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.btp.empi.vo.EmpiCard;
import com.zebone.btp.empi.vo.EmpiUser;
import com.zebone.btp.empi.vo.QueryInfo;

@Mapper
public interface EmpiCardMapper {

	// EmpiCard
	int addEmpiCard(EmpiCard empiCard);

	List<EmpiCard> queryEmpiCardList(RowBounds rowBounds, EmpiCard empiCard);

	int queryEmpiCardCount(EmpiCard empiCard);

	int updateEmpiCard(EmpiCard empiCard);

	EmpiCard loadEmpiCard(EmpiCard empiCard);
	
	int removeEmpiCard(QueryInfo info);
	
	int updateSynEmpiCards(EmpiUser empiUser);
}
