/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * 成刚	                New             2012-2-13	   <p>Title：指定泛型的数据访问接口</p>
 * 													   <p>Description：本接口定义指定泛型的通用的数据访问接口。
 * 														通过已经指定的泛型类型，可以对特定类型的数据对象进行操作，满足通用的数据访问需求。
 * 														推荐使用该接口访问的指定泛型的数据对象，非该泛型的数据对象建议使用GenericDao访问</p>
 */
package com.zebone.btp.core.daosupport;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

public interface Dao<T> extends GenericDao {
	/**
	 * 
	 * @author 成刚
	 * @date 2012-2-13
	 * @description 根据ID获取数据对象
	 * @param id
	 * @return T
	 */
    T get(Serializable id);
    
    /**
     * 
     * @author 成刚
     * @date 2012-2-13
     * @description 根据ID删除数据对象
     * @param id void
     */
    void deleteById(Serializable id);

    /**
     * 
     * @author 成刚
     * @date 2012-2-13
     * @description 查询全部数据
     * @return List<T>
     */
    List<T> list();
    
    Session openSession();
    
    void colseSession(Session session);

}
