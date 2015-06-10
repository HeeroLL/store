package com.zebone.btp.empi.service;

import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.empi.vo.EmpiCard;
import com.zebone.btp.empi.vo.EmpiConfig;
import com.zebone.btp.empi.vo.EmpiUser;
import com.zebone.btp.empi.vo.QueryInfo;

public interface EmpiService {

	/** EmpiUser对象*/
	Pagination<EmpiUser> queryEmpiUserList(Pagination<EmpiUser> page, EmpiUser empiUser);
	
	boolean operateEmpiUser(EmpiUser empiUser);
	
	boolean removeEmpiUser(QueryInfo info);
	
	EmpiUser loadEmpiUser(EmpiUser empiUser);
	
	boolean updateEmpiId(EmpiUser empiUser);
	
	/** EmpiCard对象*/
	Pagination<EmpiCard> queryEmpiCardList(Pagination<EmpiCard> page, EmpiCard empiCard);
	
	boolean operateEmpiCard(EmpiCard empiCard);
	
	EmpiCard loadEmpiCard(EmpiCard empiCard);
	
	boolean removeEmpiCard(QueryInfo info);
	
	/** EmpiConfig对象*/
	EmpiConfig getEmpiConfig();
	
	boolean operateEmpiConfig(EmpiConfig empiConfig);

}
