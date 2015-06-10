package com.zebone.dip.dictionary;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.zebone.btp.core.util.ChineseToPinYin;
import com.zebone.dip.dictionary.dao.DipDictionaryMapper;
import com.zebone.dip.dictionary.dao.DipDictionaryTypeMapper;
import com.zebone.dip.dictionary.service.DipDictionaryService;
import com.zebone.dip.dictionary.vo.DipDictionary;
import com.zebone.dip.dictionary.vo.DipDictionaryType;

//@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class DictionaryServiceTest {//extends AbstractJUnit4SpringContextTests{

	//@Resource
	private DipDictionaryService ds;
	
	//@Resource
	private DipDictionaryTypeMapper mapper;
	//@Resource
	private DipDictionaryMapper mapper1;
	
		@Test
		public void getTypeCodeListByTypeName(){
			DipDictionaryType dictType = new DipDictionaryType();
			dictType.setType_name("年龄");
			dictType.setParent_id("89a73a109b034d55973ed50dad10d92f");
			List<String> list = mapper.getTypeCodeListByParentIdAndTypeName(dictType);
			for(String str : list){
				System.out.println(str);
			}
		}
	
		@Test 
		public void testIndexSplit(){
			String name = "hwllo0001_1.1";
			int index = name.lastIndexOf("_");
			String lat = name.substring(0, index);
			System.out.println(lat);
		}
		
	    @Test
	    public void testInsertToDictionaryType(){
	    	DipDictionaryType dt = new DipDictionaryType();
	    	dt.setParent_id("11111222111111");
	    	dt.setRemark("rema22rk");
	    	dt.setType_code("t2ype_code");
	    	dt.setType_name("t2ype_name");
	    	dt.setVersion("ve2rsion");
	    	dt.setType_id("1111133ddd33");
	    	mapper.addDipDictionaryType(dt);
	    	ds.addDipDictionaryType(dt);
	    	System.out.println("添加成功!");
	    }
	    
	    @Test
	    public void testUpdateDictionaryType(){
	    	DipDictionaryType dt = new DipDictionaryType();
	    	dt.setParent_id("111111111111");
	    	dt.setRemark("11111111111");
	    	dt.setType_code("1111111111");
	    	dt.setType_name("11111111111111");
	    	dt.setVersion("1111111111111");
	    	dt.setType_id("111");
	    	mapper.updateDipDictionaryType(dt);
	    	System.out.println("更新成功!");
	    }  
	    
	    @Test
	    public void testDeleteToDictionaryType(){
	    	String[] ids = new String[]{"111","3333"};
	    	List<String> list = new ArrayList<String>();
	    	//mapper.deleteDipDictionaryByIds(ids);
//	    	List<DipDictionaryType> list22 = null;
//	    	for(DipDictionaryType dt : list22){
//	    		System.out.println(dt.getType_id());
//	    	}
	    	System.out.println("删除成功!");
	    }
	    
	    @Test
	    public void testSelectById(){
	    	DipDictionaryType dt = mapper.getDipDictionaryTypeById("111");
	    	System.out.println(dt.getParent_id()+dt.getRemark());
	    }
	    
	    
	    
	    
	    @Test
	    public void getAllDipDictionaryType(){
	    	List<DipDictionaryType> all = mapper.getAllDipDictionaryType();
	    	for(DipDictionaryType dt : all)
	    		System.out.println(dt.getType_id());
	    }
	    
	   @Test
	   public void searchDD(){
		   DipDictionaryType dt = new DipDictionaryType();
		   dt.setType_name("2");
		   dt.setType_id("2");
		  // List<DipDictionaryType> all = mapper.searchDipDictionaryType(dt);
//		   for(DipDictionaryType dtd : all)
//		    	System.out.println(dtd.getType_id());
	   }
	    
	    
//////////////////////////////////////////////////////////////////////////////////
	   
	   @Test
		public void testAddDipDictionary(){
			DipDictionary dd = new DipDictionary();
			dd.setDict_id("bb");
			dd.setDict_name("bb");
			dd.setDict_code("qbq");
			dd.setName_jianpin("bbqbq");
			dd.setName_pinyin("qbbbqqq");
			dd.setDicttype_id("3333");
			mapper1.addDipDictionary(dd);
		}
		
	   @Test
		public void testSearchDipDictionary(){
			DipDictionary dic = new DipDictionary();
			dic.setDict_code("1");
			dic.setDict_name("1");
			dic.setDicttype_id("2");
			//List<DipDictionary> list = mapper1.searchDipDictionary(dic);
//			for(DipDictionary dc : list){
//				System.out.println(dc.getDict_name());
//			}
		}
		
	   @Test
		public void testFindDipDicrionaryById(){
			DipDictionary dd = mapper1.findDipDictionaryById("11111");
			System.out.println(dd.getDict_name());
		}
	   
	   	@Test
		public void testDeleteDipDictionaryByIds(){
	   		String[] str = new String[]{"111111","bb"};
			mapper1.deleteDipDictionaryByIds(str);
			System.out.println("删除OK");
		}
	   	
	   	@Test
		public void testUpdateDipDictionary(){
			
	   		DipDictionary dd = new DipDictionary();
			dd.setDict_id("11111");
			dd.setDict_name("bb");
			dd.setDict_code("qbq");
			dd.setName_jianpin("bbqbq");
			dd.setName_pinyin("qbbbqqq");
			dd.setDicttype_id("3333");
			mapper1.updateDipDictionary(dd);
		}
		
	   	@Test
		public void testGetDipDitionaryByParentId(){
			//List<DipDictionary> dd = mapper1.getDipDictionaryByParentId("111");
//			for(DipDictionary dc : dd){
//				System.out.println(dc.getDict_name());
//			}
		}
	    
	    
	    @Test
	    public void testJianpin(){
			String[] jianpin = ChineseToPinYin.chineseToPinyinAndShort("aa").split(",");
			System.out.println(jianpin.length+jianpin[0]);
	    }
	    
	    
	    @Test
	    public void intToStringVersion(){
	    	String version = "33";
			String fist = version.substring(1);
			String second = version.substring(1, 2);
			version = fist+"."+second;
			System.out.print(version);
	    }
	    
}
