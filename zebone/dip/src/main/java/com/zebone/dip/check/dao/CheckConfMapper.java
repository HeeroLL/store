package com.zebone.dip.check.dao;

import java.util.List;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.check.vo.CheckConf;

/**
 * 接口描述：校验开关数据访问层接口
 * @author: caixl
 * @date： 日期：Sep 3, 2013
 * @version 1.0
 */
@Mapper
public interface CheckConfMapper {

	/**
	 * 方法描述: 获取所有文档的校验开关的信息
	 * @author caixl
	 * @date Sep 3, 2013
	 * @return 
	 * List<CheckConf>
	 */
	List<CheckConf> getCheckConfAll();

	/**
	 * 方法描述: 删除以前的校验开关的相关数据
	 * @author caixl
	 * @date Sep 3, 2013
	 * @return 
	 * int
	 */
	int delete();

	/**
	 * 方法描述: 批量插入校验开关信息
	 * @author caixl
	 * @date Sep 3, 2013
	 * @param list
	 * @return 
	 * int
	 */
	int saveBatch(List<CheckConf> list);
	
}
