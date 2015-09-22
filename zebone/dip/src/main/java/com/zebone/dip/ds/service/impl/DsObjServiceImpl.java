/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Feb 26, 2013		数据源业务实现类
 */
package com.zebone.dip.ds.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.ds.dao.DsObjMapper;
import com.zebone.dip.ds.service.DsObjService;
import com.zebone.dip.ds.vo.DsObj;
@Service("dsObjService")
public class DsObjServiceImpl implements DsObjService {
	@Resource
	private DsObjMapper dsObjMapper;
	
	/**
	 * @author caixl
	 * @date Feb 26, 2013
	 * @description TODO 查询数据源管理列表
	 * @param rowBounds
	 * @param dsObj 查询条件
	 * @return Pagination<DsObj>
	 */
	@Override
	public Pagination<DsObj> searchListDsObj(RowBounds rowBounds, DsObj dsObj) {
		Pagination<DsObj> page = new Pagination<DsObj>();
		page.setData(dsObjMapper.searchListDsObj(rowBounds,dsObj));
		page.setTotalCount(dsObjMapper.searchTotalCountDsObj(dsObj));
		return page;
	}

	/**
	 * @author caixl
	 * @date Feb 26, 2013
	 * @description TODO 根据数据源标识获取数据源信息
	 * @param id 数据源标识
	 * @return DsObj
	 */
	@Override
	public DsObj getDsObjInfoById(String id) {
		return dsObjMapper.getDsObjInfoById(id);
	}

	/**
	 * @author caixl
	 * @date Feb 26, 2013
	 * @description TODO 保存数据源数据
	 * @param dsObj
	 * @return int
	 */
	@Override
	public int saveDsObjInfo(DsObj dsObj) {
		dsObjMapper.saveDsObjInfo(dsObj);
		return 1;
	}

	/**
	 * @author caixl
	 * @date Feb 26, 2013
	 * @description TODO 更新数据源数据
	 * @param dsObj
	 * @return int
	 */
	@Override
	public int updateDsObjInfo(DsObj dsObj) {
		dsObjMapper.updateDsObjInfo(dsObj);
		return 1;
	}

	/**
	 * @author caixl
	 * @date Feb 26, 2013
	 * @description TODO 根据数据源标识删除数据源相关信息
	 * @param id 数据源标识
	 * @return int
	 */
	@Override
	public int removeDsObjById(String id) {
		String[] ids = id.split(",");
		List<String> list = new ArrayList<String>(ids.length);
		for(int i=0;i<ids.length;i++){
			list.add(ids[i]);
		}
		if(list!=null && list.size()>0){
			dsObjMapper.removeDsObjByids(list);
			return 1;
		}
		return 0;
	}

	/**
	 * @author caixl
	 * @date Feb 26, 2013
	 * @description TODO 检测数据源是否通畅
	 * @param dsObj 数据源信息
	 * @return int 检测结果标识
	 */
	@Override
	public int detectDsObj(DsObj dsObj) {
		String url = dsObj.getpUrl();
		url = url.substring(url.indexOf("@"));
		url = url.substring(1, url.indexOf(":"));
		if(pingServer(url)){
			if(detectDatabase(dsObj)){
				return 1;
			}
		}
		return 0;
	}
	
	/**
	 * @author caixl
	 * @date Feb 26, 2013
	 * @description TODO 检测数据源网络是否畅通
	 * @param server 数据源网络地址
	 * @return boolean
	 */
	public boolean pingServer(String server){
		BufferedReader in = null;
		Runtime r = Runtime.getRuntime();
		String pingCommand = "ping " + server;
		try{
			Process p = r.exec(pingCommand);
			if(p == null){
				return false;
			}
			in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			while((line = in.readLine()) != null){
				if (line.startsWith("Reply from")){
					return true;
				}
			}
			in.close();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * @author caixl
	 * @date Feb 27, 2013
	 * @description TODO 检测数据库是否连接正常
	 * @param dsObj
	 * @return boolean
	 */
	public boolean detectDatabase(DsObj dsObj){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName(dsObj.getpDriver());
			conn = DriverManager.getConnection(dsObj.getpUrl(), dsObj.getpUserName(), dsObj.getpPassword());
			ps = conn.prepareStatement("SELECT * FROM DUAL");
			ps.executeQuery();
			
			ps.close();
			conn.close();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author caixl
	 * @date Mar 8, 2013
	 * @description TODO 获取 所有数据源的信息
	 * @return List<DsObj>
	 */
	@Override
	public List<DsObj> findDsObjAll() {
		return dsObjMapper.findAllInfo();
	}
}
