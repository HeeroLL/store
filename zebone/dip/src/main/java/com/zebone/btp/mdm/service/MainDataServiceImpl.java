package com.zebone.btp.mdm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zebone.btp.Constant;
import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.mdm.MDMUtils;
import com.zebone.btp.mdm.dao.MainDataDao;
import com.zebone.btp.mdm.mapper.MainDataMapper;
import com.zebone.btp.mdm.vo.DBQuery;
import com.zebone.btp.mdm.vo.MDObject;
import com.zebone.btp.mdm.vo.MainDataTypeVO;
import com.zebone.util.UUIDUtil;

/**
 * 主数据 Service层 实现
 * 
 * @author ouyangxin
 * 
 * CreateDate: 2012-11-22
 */

@Service("mainDataService")
public class MainDataServiceImpl implements MainDataService {

	private static Logger log = Logger.getLogger(MainDataServiceImpl.class);

	@Autowired
	private MainDataMapper mainDataMapper;

	@Autowired
	private MainDataDao mainDataDao;

	@Override
	public List<MainDataTypeVO> queryMDTList(MainDataTypeVO mainDataType) {
		List<MainDataTypeVO> list = mainDataMapper.queryMDTList(mainDataType);
		if (list.isEmpty()) {
			list = new ArrayList<MainDataTypeVO>();
		}
		return list;
	}

	@Override
	public MainDataTypeVO findMDTByCode(String code) {
		MainDataTypeVO vo = mainDataMapper.findByCode(code);
		if (null == vo) {
			vo = new MainDataTypeVO();
		}
		// 处理字段对象集合
		MDMUtils.processMainDataTypeVO(vo);
		return vo;
	}

	@Override
	public Pagination<MainDataTypeVO> mdtQueryPage(
			Pagination<MainDataTypeVO> page, MainDataTypeVO mainDataType) {

		// 如果是根类型则查询所有的主数据类型
		if (Constant.MDT_TYPE.equals(mainDataType.getMdtType())) {
			mainDataType.setMdtType("");
		}
		// 添加数据正常标识
		mainDataType.setMdtDel(Constant.MDT_FLAG_NORNAL);

		// 封装到对应的Page对象
		page.setData(mainDataMapper.queryMDTPageList(page.getRowBounds(),
				mainDataType));
		page.setTotalCount(mainDataMapper.queryMDT2otal(mainDataType));
		return page;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Pagination<MDObject> mtDetailPage(Pagination<MDObject> page,
			DBQuery dbQuery) {

		// 传递查询条件 暂时只支持名称
		if (StringUtils.isNotEmpty(dbQuery.getMdNameValue())) {
			String[] fields = dbQuery.getDbFields().toLowerCase().split(",");
			if (null != fields && fields.length > 0) {
				dbQuery.setMdName(fields[1]);
			}
		}

		// 查询结果
		List<HashMap<String, String>> tempList = mainDataMapper
				.queryDetailObject(page.getRowBounds(), dbQuery);

		// 封装到对应的Page对象
		page.setData(MDMUtils.process(dbQuery, tempList));
		page.setTotalCount(mainDataMapper.queryDetailObjectTotal(dbQuery));
		return page;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean removeMDBatch(DBQuery dbQuery) {

		// 将ID字符传转化为list
		String[] mdId = dbQuery.getId().split(",");
		for (String id : mdId) {
			dbQuery.addId(id.trim());
		}
		return (mainDataMapper.removeMDBatch(dbQuery) > 0) ? true : false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public MDObject loadMD(DBQuery dbQuery) {

		// 查询结果
		List<HashMap<String, String>> tempList = mainDataMapper
				.queryDetailObjectByID(dbQuery);

		return (MDObject) MDMUtils.processObject(dbQuery, tempList);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean operateMDData(DBQuery dbQuery) {

		// 新增
		int a = mainDataMapper.addMDData(dbQuery);
		int d = 0;

		// 通过新增 删除达到更新的目的
		if (StringUtils.isNotEmpty(dbQuery.getPrimaryKeyValue())) {
			d = mainDataMapper.deleteMDData(dbQuery);

		}

		return (a + d >= 1) ? true : false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean operateMDTData(MainDataTypeVO vo) {

		try {
			// 处理自动生成Code
			String maxCode = mainDataMapper.getMaxCode();

			// 对象的处理 完成条件检查和拼装
			boolean bool = MDMUtils.processMainDataType(vo, maxCode);

			if (bool) {
				// 创建对应的对象
				// DB2JavaBean db2java = new DB2JavaBean(vo);
				// db2java.init();

				// 数据库中创建对应的表
				bool = mainDataDao.createTableSql(vo);

				if (bool) {
					// 插入数据
					// 删除标识值 空则插入默认值
					if (StringUtils.isEmpty(vo.getMdtDel())) {
						vo.setMdtDel(Constant.MDT_FLAG_NORNAL);
					}
					int a = mainDataMapper.addMDTData(vo);

					// 更新
					int d = 0;
					bool = (a + d >= 1) ? true : false;
				}
			}
			return bool;
		} catch (Exception e) {
			log.error("class error", e);
			return false;
		}
	}

	@Override
	public List<MainDataTypeVO> queryTypeList() {
		List<MainDataTypeVO> list = mainDataMapper.queryTypeList();
		if (list.isEmpty()) {
			list = new ArrayList<MainDataTypeVO>();
		}
		return list;
	}

	@Override
	public boolean operateMDType(MainDataTypeVO vo) {

		boolean bool = true;
		try {
			// 处理自动生成type值 为空返回失败
			String nextTypeValue = MDMUtils.getNextType(mainDataMapper
					.getMaxTypeValue());
			if (StringUtils.isEmpty(nextTypeValue)) {
				bool = false;
			}

			if (bool) {
				vo.setMdtId(UUIDUtil.getUuid());
				vo.setMdtType(nextTypeValue);
				vo.setMdtParentId(Constant.MDT_ROOT);
				vo.setMdtDel(Constant.MDT_FLAG_NORNAL);
				int a = mainDataMapper.addMDType(vo);

				bool = (a >= 1) ? true : false;
			}
		} catch (Exception e) {
			log.error("class error", e);
			bool = false;
		}
		return bool;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean updateMDTData(MainDataTypeVO vo) {

		// 更新 重新组装字段、字段类型、字段释义和是否可见值
		boolean bool = MDMUtils.processMainData(vo);
		if (bool) {

			// 更新表 只负责插入字段
			if(StringUtils.isNotEmpty(vo.getSqlCreate()))
			{
				bool = mainDataDao.createAddColumnSql(vo);
			}

			// 更新值
			if (bool) {
				int a = mainDataMapper.updateMDTByCode(vo);
				bool = (a >= 1) ? true : false;
			}
		}
		return bool;
	}

	@Override
	public boolean removeMDType(DBQuery dbQuery) {
		
		boolean bool = false;
		
		//探寻该表是否有数据
		int count = mainDataMapper.queryDataExists(dbQuery);
		
		//该表没有数据则删除表 后删除数据
		if(count < 1)
		{
			bool = mainDataDao.dropTable(dbQuery);
			if(bool)
			{
				mainDataMapper.removeMDType(dbQuery);
			}
		}
		return bool;
	}

	/*@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean operateMainData(MainDataTypeVO vo) {

		boolean bool = false;

		// mdtcode有值则为更新 否则 新增
		if (StringUtils.isNotEmpty(vo.getMdtCode())) {
			// bool = mainDataService.updateMDTData(vo);
		} else {
			// type值是根类型则新增type自身信息 否则根据type值新建表信息
			if (Constant.MDT_TYPE.equals(vo.getMdtType())) {
				// bool = mainDataService.operateMDType(vo);
			} else {
				// bool = mainDataService.operateMDTData(vo);
			}
		}
		// 处理自动生成Code
		String maxCode = mainDataMapper.getMaxCode();

		// 对象的处理 完成条件检查和拼装
		// boolean bool = MDMUtils.processMainDataType(vo, maxCode);

		if (bool) {
			// 创建对应的对象
			// DB2JavaBean db2java = new DB2JavaBean(vo);
			// db2java.init();

			// 数据库中创建对应的表
			bool = mainDataDao.createTableSql(vo);

			if (bool) {
				// 插入数据
				// 删除标识值 空则插入默认值
				if (StringUtils.isEmpty(vo.getMdtDel())) {
					vo.setMdtDel(Constant.MDT_FLAG_NORNAL);
				}
				int a = mainDataMapper.addMDTData(vo);

				// 更新
				int d = 0;
				bool = (a + d >= 1) ? true : false;
			}
		}
		// 处理自动生成type值 为空返回失败
		String nextTypeValue = MDMUtils.getNextType(mainDataMapper
				.getMaxTypeValue());
		if (StringUtils.isEmpty(nextTypeValue)) {
			bool = false;
		}

		if (bool) {
			vo.setMdtId(UUIDUtil.getUuid());
			vo.setMdtType(nextTypeValue);
			vo.setMdtParentId(Constant.MDT_ROOT);
			vo.setMdtDel(Constant.MDT_FLAG_NORNAL);
			int a = mainDataMapper.addMDType(vo);

			bool = (a >= 1) ? true : false;
		}
		try {
			// TODO 判断是何种操作

			// 检查是否符合条件

			// 组装条件

			// 执行sql

			// 操作数据

		} catch (Exception e) {
			log.error(e);
			bool = false;
		}
		return bool;
	}*/
}
