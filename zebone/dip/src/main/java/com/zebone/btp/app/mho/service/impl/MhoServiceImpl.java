/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * dell              New             2012-11-23
 */
package com.zebone.btp.app.mho.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zebone.btp.app.mho.dao.MhoMapper;
import com.zebone.btp.app.mho.service.MhoService;
import com.zebone.btp.app.mho.vo.Mho;
import com.zebone.btp.core.util.Pagination;

@Service("mhoService")
public class MhoServiceImpl implements MhoService {

	@Resource
	private MhoMapper mhoMapper;

	@Override
	public int deleteById(String mhoId) {
		// TODO Auto-generated method stub
		return this.mhoMapper.deleteById(mhoId);
	}

	@Override
	public Mho findById(String mhoId) {
		// TODO Auto-generated method stub
		return this.mhoMapper.findById(mhoId);
	}

	@Override
	public int insert(Mho mho) {
		// TODO Auto-generated method stub
		return this.mhoMapper.insert(mho);
	}

	@Override
	public Pagination<Mho> getPagination(Pagination<Mho> pagination, Mho mho) {
		Pagination<Mho> page = new Pagination<Mho>();
		List<Mho> list = this.mhoMapper.queryMhoList(pagination.getRowBounds(),
				mho);
		int count = this.mhoMapper.queryMhoCount(mho);
		page.setData(list);
		page.setTotalCount(count);
		return page;
	}

	@Override
	public List<Mho> queryMho(String mhoCode, String mhoName) {
		// TODO Auto-generated method stub
		return this.mhoMapper.queryMho(mhoCode, mhoName);
	}

	@Override
	public int updateById(Mho mho) {
		// TODO Auto-generated method stub
		return this.mhoMapper.updateById(mho);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zebone.btp.app.mho.service.MhoService#findMho()
	 */
	@Override
	public String findMaxLevelCode(String parentId) {
		// TODO Auto-generated method stub
		return this.mhoMapper.findMaxLevelCode(parentId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zebone.btp.app.mho.service.MhoService#findByLevelCode(java.lang.String)
	 */
	/**
	 * 根据levelcode查询机构信息
	 */
	@Override
	public List<Mho> findByMhoIds(String[] mhoIds) {
		Set<Mho> set = new HashSet<Mho>();
		for (int i = 0; i < mhoIds.length; i++) {
			String levelCode = mhoMapper.findLevelCodeByMid(mhoIds[i]);
			List<Mho> list1 = mhoMapper.findByLevelCode(levelCode);
			for (Mho o : list1) {
				set.add(o);
			}
		}
		List<Mho> list = new ArrayList<Mho>(set);
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zebone.btp.app.mho.service.MhoService#findLevelCodeById(java.lang.String)
	 */
	@Override
	public List<Mho> findLevelCodeByPid(String parentId) {
		// TODO Auto-generated method stub
		return this.mhoMapper.findLevelCodeByPid(parentId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zebone.btp.app.mho.service.MhoService#findMaxMhoCode(java.lang.String)
	 */
	@Override
	public String findLevelCodeByMid(String mhoId) {
		// TODO Auto-generated method stub
		return this.mhoMapper.findLevelCodeByMid(mhoId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zebone.btp.app.mho.service.MhoService#findMhoCodeById(java.lang.String)
	 */
	@Override
	public List<Mho> findMhoCodeById(String parentId) {
		// TODO Auto-generated method stub
		return this.mhoMapper.findMhoCodeById(parentId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zebone.btp.app.mho.service.MhoService#findAllMhoInfo(String
	 *      levelCode)
	 */
	@Override
	public List<Mho> findAllMhoInfo(String levelCode) {
		// TODO Auto-generated method stub
		return this.mhoMapper.findAllMhoInfo(levelCode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zebone.btp.app.mho.service.MhoService#findMhoByParentId(java.lang.String)
	 */
	@Override
	public List<Mho> findMhoByParentId(String parentId) {
		// TODO Auto-generated method stub
		return this.mhoMapper.findMhoByParentId(parentId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zebone.btp.app.mho.service.MhoService#findMhoName()
	 */
	@Override
	public String findMhoName(String mhoName) {
		// TODO Auto-generated method stub
		return this.mhoMapper.findMhoName(mhoName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zebone.btp.app.mho.service.MhoService#findLevelCodeByMhoId(java.lang.String)
	 */
	@Override
	public String findLevelCodeByMhoId(String mhoId) {
		// TODO Auto-generated method stub
		return this.mhoMapper.findLevelCodeByMhoId(mhoId);
	}

	/**
	 * @see com.zebone.btp.app.mho.service.MhoService#getMhoByPersonnelId(java.lang.String)
	 */
	@Override
	public List<Mho> getMhoByPersonnelId(String personnelId) {
		return this.mhoMapper.getMhoByPersonnelId(personnelId);
	}

}
