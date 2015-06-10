package com.zebone.empi.dao;

import java.util.List;
import java.util.Map;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.empi.vo.ResidentCard;

/**
 * 居民卡信息数据库操作映射接口
 * @author YinCm
 * @version 2013-7-31 下午10:15:20
 */
@Mapper
public interface ResidentCardMapper {
    
	
	/**
	 * 通过卡id删除卡记录
	 * @param id
	 * @return
	 */
    int deleteResidentCardById(String id);
    
    /**
     * 插入一条卡信息
     * @param record
     * @return
     */
    int insertResidentCard(ResidentCard record);
    
    /**
     * 按照卡id查询卡记录
     * @param id
     * @return
     */
    ResidentCard selectResidentCardById(String id);

     /**
      * 查询所有卡记录
      * @param record
      * @return
      */
    List<ResidentCard> selectResidentCard(ResidentCard record);
    
    /**
     * 通过id更新卡信息
     * @param record
     * @return
     */
    int updateResidentCardById(ResidentCard record);

    /**
     * 按code和type查找card的条数
     * @param record
     * @return
     */
    int countSelectByCodeAndTypeAndEmpi(ResidentCard record);
    
    /**
     * 按code和type查找的card来更新
     * @param record
     * @return
     */
    void updateByCodeAndTypeAndEmpi(ResidentCard record);
    
    /**
     * 按code和type和Empi来查找card记录
     * @param record
     * @return
     */
    ResidentCard selectByCodeAndTypeAndEmpi(ResidentCard record);
    
    /**
     * 按照card no来查找一级标识符
     * @param cardNo
     * @return
     */
    public String selectByFirstLevelCardId(String cardNo);
    
    /**
     * 按照EMPI标识查找卡信息
     * @param empi
     * @return
     */
    public List<ResidentCard> selectByEmpi(String empi);
    
    /**
     * 按code和type查找card记录
     * @param record
     * @return
     */
    public List<ResidentCard> selectByCodeAndType(ResidentCard record);
    
    /**
     * 按code,type和机构查找card记录
     * @param record
     * @return
     */
    public List<ResidentCard> selectByCodeAndTypeAndDept(ResidentCard record);
    
    /**
     * 按code,type,name和机构查找card记录
     * @param record
     * @return
     */
    public List<ResidentCard> selectByCodeAndTypeAndDeptAndName(ResidentCard record);

    /**
     * 根据卡号和卡类型查询卡信息
     * @param parMap
     * @return
     */
    public List<ResidentCard> getResidentCardInfo (Map parMap);

    /**
     * 根据卡号查询卡信息
     * @param cardNo
     * @return
     */
    public List<ResidentCard> selectByCardNo (String cardNo);

    /**
     * 卡注销，卡状态更新为“2”
     * @param residentCard
     * @return
     */
    int updateCardStatus(ResidentCard residentCard);

    /**
     * 按照EMPI标识获取一级标识卡信息
     * @param oMap
     * @return
     */
    public List<ResidentCard> getFirstLevelCards(Map oMap);
    
    
    /**
     * 根据主键更新卡的empi
     * @param residentCard
     * @return
     * @author 陈阵 
     * @date 2014-8-7 上午10:46:12
     */
    int updateCardEmpiById(ResidentCard residentCard);
    
    /**
     * 删除卡信息
     * @param residentCard
     * @return
     * @author 陈阵 
     * @date 2014-10-24 上午9:27:48
     */
    int delByCodeAndType(ResidentCard residentCard);
}