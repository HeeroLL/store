/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * dell              New             2012-11-23
 */
package com.zebone.btp.app.mho.service;

import java.util.List;

import com.zebone.btp.app.mho.vo.Mho;
import com.zebone.btp.core.util.Pagination;

public interface MhoService {
	/**
	 * 
	 * @author 范雄磊
	 * @date 2012-11-27
	 * @description 根据Id查询医疗机构
	 * @param mhoId
	 * @return Mho
	 */
	Mho findById(String mhoId);
	/**
	 * 
	 * @author 范雄磊
	 * @date 2012-11-27
	 * @description 删除操作
	 * @param mhoId
	 * @return int
	 */
	int deleteById(String mhoId);
	/**
	 * 
	 * @author 范雄磊
	 * @date 2012-11-27
	 * @description 新增操作
	 * @param mho
	 * @return int
	 */
	int insert(Mho mho);
	/**
	 * 
	 * @author 范雄磊
	 * @date 2012-11-27
	 * @description 更新操作
	 * @param mho
	 * @return int
	 */
	int updateById(Mho mho);
	/**
	 * 
	 * @author 范雄磊
	 * @date 2012-11-27
	 * @description 查询
	 * @param mhoCode
	 * @param mhoName
	 * @return List<Mho>
	 */
	List<Mho> queryMho(String mhoCode,String mhoName);
	/**
	 * 
	 * @author 范雄磊
	 * @date 2012-11-27
	 * @description 分页
	 * @param mhoId
	 * @return Pagination<Mho>
	 */
	Pagination<Mho> getPagination(Pagination<Mho> pagination,Mho mho);
	
	/**
	 * 
	 * @author 范雄磊
	 * @date 2012-11-27
	 * @description 根据parentId查询此机构下最大机构层级码
	 * @return List<Mho>
	 */
	String findMaxLevelCode(String parentId);
	/**
	 * 
	 * @author 范雄磊
	 * @date 2012-11-27
	 * @description 根据mhoId查询此机构下最大机构码
	 * @return List<Mho>
	 */
	String findLevelCodeByMid(String mhoId);
	/**
	 * 
	 * @author dell
	 * @date 2012-11-28
	 * @description 根据mhoId查询此机构下机构码
	 * @param parentId
	 * @return List<Mho>
	 */
	String findLevelCodeByMhoId(String mhoId);
	/**
	 * 
	 * @author 范雄磊
	 * @date 2012-11-27
	 * @description 根据层级码查询医疗机构信息
	 * @param levelCode
	 * @return List<Mho>
	 */
	List<Mho> findByMhoIds(String[] mhoIds);
	/**
	 * 
	 * @author 范雄磊
	 * @date 2012-11-28
	 * @description 根据Id查询机构的层级码
	 * @param mhoId
	 * @return List<Mho>
	 */
	List<Mho> findLevelCodeByPid(String parentId);
	
	/**
	 * 
	 * @author 范雄磊
	 * @date 2012-11-28
	 * @description 根据Id查询机构码
	 * @param mhoId
	 * @return List<Mho>
	 */
	List<Mho> findMhoCodeById(String parentId);
	/**
	 * 
	 * @author 范雄磊
	 * @date 2012-12-5
	 * @description 查询机构树信息
	 * @return List<Mho>
	 */
	List<Mho> findAllMhoInfo(String levelCode);
	/**
	 * 
	 * @author 范雄磊
	 * @date 2012-12-7
	 * @description 根据父ID查询子机构
	 * @param parentId
	 * @return List<Mho>
	 */
	List<Mho> findMhoByParentId(String parentId);
	/**
	 * 
	 * @author 范雄磊
	 * @date 2012-12-10
	 * @description 名称重复的校验
	 * @param mhoName
	 * @return String
	 */
	String findMhoName(String mhoName);
	
	/**
	 * 根据人员id，得到此人员所属的医疗机构
	 * @param personnelId 人员id
	 * @return 医疗机构列表。一个人员可能对应多个医疗机构。
	 * @author 宋俊杰 
	 */
	List<Mho> getMhoByPersonnelId(String personnelId);

}
