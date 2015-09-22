/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Apr 19, 2013		清洗配置管理业务实现层
 */
package com.zebone.dip.clear.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.zebone.dip.clear.dao.ClearCloumnMapper;
import com.zebone.dip.clear.dao.ClearConfMapper;
import com.zebone.dip.clear.service.ClearConfService;
import com.zebone.dip.clear.vo.ClearCloumn;
import com.zebone.dip.clear.vo.ClearConf;
import com.zebone.dip.clear.vo.TableConf;
import com.zebone.dip.ds.dao.DsObjMapper;
import com.zebone.dip.ds.vo.DsObj;
import com.zebone.dip.task.dao.TaskMapper;
import com.zebone.dip.task.vo.Task;
import com.zebone.util.UUIDUtil;
@Service("clearConfService")
public class ClearConfServiceImpl implements ClearConfService {

	@Resource
	private ClearConfMapper clearConfMapper;
	@Resource
	private DsObjMapper dsObjMapper;
	@Resource
	private ClearCloumnMapper clearCloumnMapper;
	@Resource
	private TaskMapper taskMapper;
	/**
	 * @author caixl
	 * @date Apr 19, 2013
	 * @description TODO get information of ClearConf by tableId 
	 * @param id
	 * @return ClearConf
	 */
	@Override
	public ClearConf getClearConfByTableId(String id) {
		ClearConf clearConf = clearConfMapper.getClearConfById(id);
		return clearConf;
	}

	/**
	 * @author caixl
	 * @date Apr 19, 2013
	 * @description TODO get columns of table
	 * @param tableConf
	 * @return List<String>
	 */
	@Override
	public List<String> getColsByTableConfInfo(TableConf tableConf) {
		DsObj dsObj = dsObjMapper.findById(tableConf.getDsId());
		List<String> list = getColsByTable(dsObj,tableConf.getTableName());
		return list;
	}
	
	public List<String> getColsByTable(DsObj dsObj,String tableName){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		try{
			Class.forName(dsObj.getpDriver());
			conn = DriverManager.getConnection(dsObj.getpUrl(), dsObj.getpUserName(), dsObj.getpPassword());
			ps = conn.prepareStatement("SELECT T.COLUMN_NAME FROM USER_TAB_COLUMNS T WHERE T.TABLE_NAME = ?");
			ps.setString(1, tableName);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(rs.getString(1));
			}
			rs.close();
			ps.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch(Exception e){}
		}
		return list;
	}

	/**
	 * @author caixl
	 * @date Apr 19, 2013
	 * @description TODO 获取配置字段相关信息
	 * @param id
	 * @return List<ClearCloumn>
	 */
	@Override
	public List<ClearCloumn> getClearCloumnsByClearId(String id) {
		List<ClearCloumn> list = clearCloumnMapper.getClearCloumnsByClearId(id);
		return list;
	}

	/**
	 * @author caixl
	 * @date Apr 20, 2013
	 * @description TODO 保存清洗表配置任务信息
	 * @param task
	 * @param clearConf
	 * @param clearData
	 * @return int
	 */
	@Override
	public int saveClearInfo(Task task, ClearConf clearConf, String clearData) {
		clearConfMapper.insert(clearConf);
		taskMapper.saveTaskInfo(task);
		if(!StringUtils.isEmpty(clearData)){
			String[] strs = clearData.split("\\^");
			//List<ClearCloumn> list = new ArrayList<ClearCloumn>();
			for(int i=0;i < strs.length;i++){
				String[] str = strs[i].split("~");
				String cloumnName = str[0];
				
				for(int j=1 ; j <str.length ;j++ ){
					ClearCloumn cc = new ClearCloumn();
					cc.setId(UUIDUtil.getUuid());
					cc.setClearId(clearConf.getId());
					cc.setCloumnName(cloumnName);
					cc.setClearType(str[j].split(";")[0]);
					cc.setClearContent(str[j].split(";")[1]);
					
					clearCloumnMapper.insert(cc);
					//list.add(cc);
				}
			}
			//clearCloumnMapper.insertClearInfos(list);
		}
		return 1;
	}

	/**
	 * @author caixl
	 * @date Apr 20, 2013
	 * @description TODO 更新清洗表配置任务信息
	 * @param task
	 * @param clearConf
	 * @param clearData
	 * @return int
	 */
	@Override
	public int updateClearInfo(Task task, ClearConf clearConf, String clearData) {
		clearConfMapper.updateById(clearConf);
		taskMapper.updateTaskInfo(task);
		clearCloumnMapper.deleteByClearId(clearConf.getId());
		if(!StringUtils.isEmpty(clearData)){
			String[] strs = clearData.split("\\^");
			//List<ClearCloumn> list = new ArrayList<ClearCloumn>();
			for(int i=0;i < strs.length;i++){
				String[] str = strs[i].split("~");
				String cloumnName = str[0];
				
				for(int j=1 ; j <str.length ;j++ ){
					ClearCloumn cc = new ClearCloumn();
					cc.setId(UUIDUtil.getUuid());
					cc.setClearId(clearConf.getId());
					cc.setCloumnName(cloumnName);
					cc.setClearType(str[j].split(";")[0]);
					cc.setClearContent(str[j].split(";")[1]);
					
					clearCloumnMapper.insert(cc);
					//list.add(cc);
				}
			}
			//clearCloumnMapper.insertClearInfos(list);
		}
		return 1;
	}

	/**
	 * @author caixl
	 * @date Apr 20, 2013
	 * @description TODO 更新清洗配置发布状态
	 * @param id
	 * @return int
	 */
	@Override
	public int updateClearDeployFlag(String id) {
		ClearConf cc = clearConfMapper.findById(id);
		Task task = new Task();
		task.setId(cc.getTaskId());
		if("1".equals(cc.getDeployFlag())){
			cc.setDeployFlag("0");
			task.setTaskDeploy("0");
		}else{
			cc.setDeployFlag("1");
			task.setTaskDeploy("1");
		}
		clearConfMapper.updateById(cc);
		taskMapper.updateTaskState(task);
		return 1;
	}

	/**
	 * @author caixl
	 * @date Apr 20, 2013
	 * @description TODO 获取配置表 信息
	 * @param id
	 * @return ClearConf
	 */
	@Override
	public ClearConf getClearConfById(String id) {
		return clearConfMapper.findById(id);
	}
}
