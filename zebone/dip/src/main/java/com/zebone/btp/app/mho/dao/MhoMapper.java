package com.zebone.btp.app.mho.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.app.mho.vo.Mho;
import com.zebone.btp.core.mybatis.Mapper;
@Mapper
public interface MhoMapper {
	/**
	 * 
	 * @author 范雄磊
	 * @date 2012-11-28
	 * @description 删除
	 * @param mhoId
	 * @return int
	 */
	int deleteById(String mhoId);
    /**
     * 
     * @author 范雄磊
     * @date 2012-11-28
     * @description 新增
     * @param record
     * @return int
     */
	int insert(Mho record);

	int insertSelective(Mho record);
     /**
      * 
      * @author 范雄磊
      * @date 2012-11-28
      * @description 查询
      * @param mhoId
      * @return Mho
      */
	Mho findById(String mhoId);
     /**
      * 
      * @author 范雄磊
      * @date 2012-11-28
      * @description 更新
      * @param record
      * @return int
      */
	int updateById(Mho record);

	int updateByPrimaryKey(Mho record);
	/**
	 * 
	 * @author dell
	 * @date 2012-11-28
	 * @description 统计每页条数
	 * @param mho
	 * @return Integer
	 */
	Integer queryMhoCount(Mho mho);
	/**
	 * 
	 * @author 范雄磊
	 * @date 2012-11-28
	 * @description 分页
	 * @param rowBounds
	 * @param mho
	 * @return List<Mho>
	 */
	List<Mho> queryMhoList(RowBounds rowBounds,Mho mho);
	
	/**
	 * 
	 * @author 范雄磊
	 * @date 2012-11-28
	 * @description 根据parentId查询此机构下最大层级码
	 * @param parentId
	 * @return List<Mho>
	 */
	String findMaxLevelCode(String parentId);
	/**
	 * 
	 * @author dell
	 * @date 2012-11-28
	 * @description 根据mhoId查询此机构下机构码数组
	 * @param parentId
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
	 * @date 2012-11-28
	 * @description 条件查询
	 * @param mhoCode
	 * @param mhoName
	 * @return List<Mho>
	 */
	List<Mho> queryMho(String mhoCode,String mhoName);
	
	/**
	 * 
	 * @author 范雄磊
	 * @date 2012-11-28
	 * @description 根据层级码查询机构
	 * @param levelCode
	 * @return List<Mho>
	 */
	List<Mho> findByLevelCode(String levelCode);
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
	 * @description 名称的校验
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