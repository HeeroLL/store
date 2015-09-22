package com.zebone.dip.log.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.log.dao.EmpiLogMapper;
import com.zebone.dip.log.dao.EmpiMatchLogMapper;
import com.zebone.dip.log.dao.ResidentInfoLogMapper;
import com.zebone.dip.log.service.EmpiLogService;
import com.zebone.dip.log.vo.EmpiResult;
import com.zebone.dip.log.vo.EmpiSearchObj;

@Service
public class EmpiLogServiceImpl implements EmpiLogService {

	@Resource
	private EmpiLogMapper empiLogMapper;
	@Resource
	private EmpiMatchLogMapper empiMatchLogMapper;
	@Resource
	private ResidentInfoLogMapper residentInfoLogMapper;
	
	@Override
	public Pagination<EmpiResult> searchAddUpdateLog(EmpiSearchObj empiSearchObj, Pagination<EmpiResult> page) {
		//如果 状态未输入或者输入为0，则查询成功的结果；（0：注册更新成功，1：注册更新失败）
		if(empiSearchObj.getStatus()==null ||empiSearchObj.getStatus().equals("0")){
			List<EmpiResult> list = residentInfoLogMapper.searchResidentInfoLog(empiSearchObj, page.getRowBounds());
			
			for(EmpiResult er : list){
				er.setEvent("注册更新");
                er.setStatus("成功");
//				if(er.getStatus()!=null && er.getStatus().equals("0")){
//					er.setStatus("成功");
//				}else{
//					er.setStatus("失败");
//				}
			}
			int count = residentInfoLogMapper.searchResidentInfoLogCount(empiSearchObj);
			page.setData(list);
			page.setTotalCount(count);
			return page;
		}else{
			List<EmpiResult> list = empiLogMapper.searchEmpiLog(empiSearchObj,  page.getRowBounds());
			int count = empiLogMapper.searchEmpiLogCount(empiSearchObj);
			for(EmpiResult er : list){
				er.setEvent("注册更新");
				Timestamp tm = er.getCreateTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				er.setEventTime(sdf.format(tm));
                er.setStatus("失败");
//				if(er.getStatus()!=null && er.getStatus().equals("0")){
//					er.setStatus("成功");
//				}else{
//					er.setStatus("失败");
//				}
			}
			page.setData(list);
			page.setTotalCount(count);
			return page;
		}
	}

	@Override
	public Pagination<EmpiResult> searchQueryLog(EmpiSearchObj empiSearchObj, Pagination<EmpiResult> page) {
		//状态- 查询：0 empi未上传,1 已匹配,2 未匹配
		List<EmpiResult> list = empiMatchLogMapper.searchEmpiMatchLog(empiSearchObj, page.getRowBounds());
		for(EmpiResult er : list){
			er.setEvent("查询");
			if(er.getStatus()!=null && er.getStatus().equals("0")){
				er.setStatus("未上传");
			}else if(er!=null && er.getStatus().equals("1")){
				er.setStatus("已匹配");
			}else{
				er.setStatus("未匹配");
			}
		}
		int count = empiMatchLogMapper.searchEmpiMatchLogCount(empiSearchObj);
		page.setData(list);
		page.setTotalCount(count);
		return page;
	}

}
