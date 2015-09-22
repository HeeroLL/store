/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * wangpeng             New             2012-11-23     字典管理测试
 */
package com.zebone.btp.dictionary;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zebone.btp.app.dict.pojo.DictionaryType;
import com.zebone.btp.app.dict.service.DictionaryTypeService;
import com.zebone.btp.core.util.Pagination;

/**
 * UT
 * 2012-11-22]
 */
public class DictionaryTest
{
	protected ApplicationContext context;
	
	private DictionaryTypeService dictionaryTypeService;

	/**
	 * 得到spring 上下文
	 */
	@Before
	public void setUp() {
		context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		setUpService();
	}
	
	/**
     * 装载需要测试的类
     */
	public void setUpService() {
		try {
			dictionaryTypeService = context.getBean("dictionaryTypeService", DictionaryTypeService.class);
		} catch (BeansException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	@Test
	public void testPage(){
		DictionaryType dictionaryType = new DictionaryType();
		Pagination<DictionaryType> page = dictionaryTypeService.findPage(dictionaryType);
		List<DictionaryType> list = page.getData();
		for(DictionaryType d : list){
			System.out.println(d.getTypeCode());
		}
		System.out.println(page.getData().size());
//		System.out.println(page.getCountEveryPage());
//		System.out.println(page.getCurPage());
//		System.out.println(page.getOffset());
//		System.out.println(page.getPageCount());
	}

}
