package com.zebone.dip.compare.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.compare.dao.MainDataOrgMapper;
import com.zebone.dip.compare.vo.MDstdInfo;
import com.zebone.dip.compare.vo.MainInfo;

/**
 * 主数据对照管理业务实现
 */
@Service("mdCompareService")
public class MDCompareServiceImpl implements MDCompareService {

	@Resource
	private MainDataOrgMapper mainDataOrgMapper;

	/**
	 * 获取主数据对照列表信息
	 * @param page
	 * @param mainInfo
	 * @return 
	 * Pagination<MainInfo>
	 */
	@Override
	public Pagination<MainInfo> getListByInfo(Pagination<MainInfo> page,MainInfo mainInfo) {
		List<MainInfo> data = mainDataOrgMapper.getListByInfo(page.getRowBounds(),mainInfo);
		int totalCount = mainDataOrgMapper.getCountByInfo(mainInfo);
		page.setData(data);
		page.setTotalCount(totalCount);
		return page;
	}

	/**
	 * 获取机构主数据信息
	 * @param mainInfo
	 * @return 
	 * List<MainInfo>
	 */
	@Override
	public List<MainInfo> getOrgMainInfoByName(MainInfo mainInfo) {
		
		return mainDataOrgMapper.getOrgMainInfoByName(mainInfo);
	}

	/**
	 * 获取某个机构主数据对照信息
	 * @param mainInfo
	 * @return 
	 * MainInfo
	 */
	@Override
	public MainInfo getInfoById(MainInfo mainInfo) {
		return mainDataOrgMapper.getInfoById(mainInfo);
	}

	/**
	 * 获取标准主数据列表信息
	 * @param page
	 * @param mainInfo
	 * @return 
	 * Pagination<MDstdInfo>
	 */
	@Override
	public Pagination<MDstdInfo> getStdListByInfo(Pagination<MDstdInfo> page,MainInfo mainInfo) {
		String col = "";
		if(!StringUtils.isBlank(mainInfo.getName())){
			col =  mainInfo.getName().split(";")[0];
		}
		String[] cols = col.split(",");
		for(int i = 0;i<cols.length;i++){
			cols[i] = cols[i] + " AS col"+(i+1);
		}
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i<cols.length;i++){
			sb.append(cols[i]).append(",");
		}
		if(sb.length()>1){
			mainInfo.setCode(sb.substring(0, sb.length()-1));
		}
		
		List<MDstdInfo> data = mainDataOrgMapper.getListStd(page.getRowBounds(),mainInfo);
		int totalCount = mainDataOrgMapper.getTotalCountStd(mainInfo);
		
		page.setData(data);
		page.setTotalCount(totalCount);
		return page;
	}

	/**
	 * 获取标准主数据匹配列表
	 * @param mainInfo
	 * @return 
	 * List<MainInfo>
	 */
	@Override
	public List<MainInfo> getMainListByName(MainInfo mainInfo) {
		
		return mainDataOrgMapper.getMainListByName(mainInfo);
	}

	/**
	 * 保存主数据对照信息
	 * @param mainInfo
	 * @return 
	 * int
	 */
	@Override
	public int saveMdCompare(MainInfo mainInfo) {
		int count = mainDataOrgMapper.getCountByCode(mainInfo);
		if(count > 0) return 2;
		mainDataOrgMapper.saveMainDataInfo(mainInfo);
		return 1;
	}

	/**
	 * 更新主数据对照信息
	 * @param mainInfo 
	 * void
	 */
	@Override
	public void updateMdCompare(MainInfo mainInfo) {
		mainDataOrgMapper.updateMainDataInfo(mainInfo);
	}

	/**
	 * 删除主数据对照信息
	 * @param mainInfo 
	 * void
	 */
	@Override
	public void removeMdCompare(MainInfo mainInfo) {
		String[] ids = mainInfo.getId().split(",");
		MainInfo info = new MainInfo();
		info.setTableName(mainInfo.getTableName());
		for(String id: ids){
			info.setId(id);
			mainDataOrgMapper.removeMainDataInfo(info);
		}
	}

}
