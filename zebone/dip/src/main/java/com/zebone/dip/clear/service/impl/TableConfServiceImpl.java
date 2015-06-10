/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Apr 18, 2013		表配置业务实现层
 */
package com.zebone.dip.clear.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.clear.dao.ClearConfMapper;
import com.zebone.dip.clear.dao.TableConfMapper;
import com.zebone.dip.clear.service.TableConfService;
import com.zebone.dip.clear.vo.ClearConf;
import com.zebone.dip.clear.vo.TableConf;
import com.zebone.dip.ds.dao.DsObjMapper;
@Service("tableConfService")
public class TableConfServiceImpl implements TableConfService {

	@Resource
	private TableConfMapper tableConfMapper;
	@Resource
	private ClearConfMapper clearConfMapper;
	@Resource
	private DsObjMapper dsObjMapper;
	/**
	 * @author caixl
	 * @date Apr 18, 2013
	 * @description TODO 获取表配置管理列表
	 * @param rowBounds
	 * @param tableConf
	 * @return Pagination<TableConf>
	 */
	@Override
	public Pagination<TableConf> taskInfoList(RowBounds rowBounds,
			TableConf tableConf) {
		Pagination<TableConf> page = new Pagination<TableConf>();
		List<TableConf> list = tableConfMapper.getTableConfList(rowBounds,tableConf);
		int totalCount = tableConfMapper.getTableConfTotalCount(tableConf);
		page.setData(list);
		page.setTotalCount(totalCount);
		return page;
	}
	/**
	 * @author caixl
	 * @date Apr 18, 2013
	 * @description TODO 根据标识获取配置表信息
	 * @param id
	 * @return TableConf
	 */
	@Override
	public TableConf getTableConfById(String id) {
		return tableConfMapper.findById(id);
	}
	/**
	 * @author caixl
	 * @date Apr 18, 2013
	 * @description TODO 保存表配置管理信息
	 * @param tableConf
	 * @return int
	 */
	@Override
	public int saveTableConfInfo(TableConf tableConf) {
		return tableConfMapper.insert(tableConf);
	}
	/**
	 * @author caixl
	 * @date Apr 18, 2013
	 * @description TODO 更新表配置管理信息
	 * @param tableConf
	 * @return int
	 */
	@Override
	public int updateTableConfInfo(TableConf tableConf) {
		return tableConfMapper.updateById(tableConf);
	}
	/**
	 * @author caixl
	 * @date Apr 18, 2013
	 * @description TODO 根据标识 删除 表配置相关信息
	 * @param id
	 * @return int
	 */
	@Override
	public int removeTableConfByIds(String id) {
		String[] ids = id.split(",");
		List<String> list = new ArrayList<String>();
		for(String confId : ids){
			ClearConf clearConf = clearConfMapper.getClearConfById(confId);
			if(clearConf != null && StringUtils.isEmpty(clearConf.getId())){
			}else{
				list.add(confId);
			}
		}
		if(list.size()>0){
			tableConfMapper.updateTableConfByIds(list);
			return 1;
		}
		return 0;
	}
	/**
	 * @author caixl
	 * @date Apr 18, 2013
	 * @description TODO 获取所有清洗表信息
	 * @return List<TableConf>
	 */
	@Override
	public List<TableConf> getAllTableConf() {
		return tableConfMapper.getAllTableConf();
	}
}
