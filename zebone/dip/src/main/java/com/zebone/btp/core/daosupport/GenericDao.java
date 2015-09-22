/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * 成刚	              	New             2012-2-13	   <p>Title: 通用数据访问接口</p>
 * 													   <p>Description: 本接口定义非泛型的通用的数据访问接口。
 * 													           通过传入数据对象的类型，可以对特定的数据对象进行操作，满足通用的数据访问需求。
 * 													          该接口不限制访问的数据类型</p>
 */
package com.zebone.btp.core.daosupport;

import org.hibernate.Session;

public interface GenericDao {
	/**
	 * 
	 * @author 成刚
	 * @date 2012-2-13
	 * @description 保存数据对象，用于新建
	 * @param obj void
	 */
    void save(Object obj);
    
    /**
     * 
     * @author 成刚
     * @date 2012-2-13
     * @description 删除数据对象
     * @param obj void
     */
    void delete(Object obj);

    /**
     * 
     * @author 成刚
     * @date 2012-2-13
     * @description 更新数据对象，用于修改
     * @param obj void
     */
    void update(Object obj);
    
    /**
     * 
     * @author 成刚
     * @date 2012-2-13
     * @description 从数据库重新读取对象
     * @param obj void
     */
    void refresh(Object obj);
    
    /**
	 * 
	 * @author 成刚
	 * @date 2012-2-29
	 * @description 拿到当前session操作数据
	 * @param obj void
	 */
    void saveObject(Object obj);
    
    /**
     * 
     * @author 成刚
     * @date 2012-5-9
     * @description 手动打开session update操作
     * @param obj void
     */
    void updateObjByOpenSession(Session session, Object obj);
    
    /**
     * 
     * @author 成刚
     * @date 2012-5-9
     * @description 手动打开session save操作
     * @param obj void
     */
    void saveObjByOpenSession(Session session, Object obj);

}
