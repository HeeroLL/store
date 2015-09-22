package com.zebone.empi.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.zebone.empi.dao.DictionaryMapper;
import com.zebone.empi.vo.Dictionary;

public class ApplicationListenerBean implements ApplicationListener {
	@Resource
	private DictionaryMapper dictionaryMapper;
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            ApplicationContext applicationContext = ((ContextRefreshedEvent) event).getApplicationContext();
            //获取国家
            List<Dictionary> list1 = dictionaryMapper.getDictionaryByTypeId("2539698A5A6B4D4FA48DD9CBEAF4A0EA");
            Map<String, String> nationalityMap = new HashMap<String, String>();
            for(Dictionary dt : list1){
            	nationalityMap.put(dt.getDict_code(), dt.getDict_name());
            	System.out.println(dt.getDict_code()+":::"+dt.getDict_name());
            }
            DictionaryConvert.getDictionary().put("nationality", nationalityMap);
            System.out.println("---------------------------------------");
            //性别
            List<Dictionary> list2 = dictionaryMapper.getDictionaryByTypeId("FA5853DD4FE34449A06B90AD406C68AC");
            Map<String, String> sexMap = new HashMap<String, String>();
            for(Dictionary dt : list2){
            	sexMap.put(dt.getDict_code(), dt.getDict_name());
            	System.out.println(dt.getDict_code()+":::"+dt.getDict_name());
            }
            DictionaryConvert.getDictionary().put("sex", sexMap);
            //职业
            List<Dictionary> list3 = dictionaryMapper.getDictionaryByTypeId("B184243FFB4A4F0E877F7C163FF98168");
            Map<String, String> professionMap = new HashMap<String, String>();
            for(Dictionary dt : list3){
            	professionMap.put(dt.getDict_code(), dt.getDict_name());
            	System.out.println(dt.getDict_code()+":::"+dt.getDict_name());
            }
            DictionaryConvert.getDictionary().put("profession", professionMap);
            //民族
            List<Dictionary> list4 = dictionaryMapper.getDictionaryByTypeId("3DB9B62FBE124FD3A21083EBFBF9AA9F");
            Map<String, String> nationMap = new HashMap<String, String>();
            for(Dictionary dt : list4){
            	nationMap.put(dt.getDict_code(), dt.getDict_name());
            	System.out.println(dt.getDict_code()+":::"+dt.getDict_name());
            }
            DictionaryConvert.getDictionary().put("nation", nationMap);
            //婚姻
            List<Dictionary> list5 = dictionaryMapper.getDictionaryByTypeId("09DCE2D0E5E7479683C913193C2BC12F");
            Map<String, String> matrimonyMap = new HashMap<String, String>();
            for(Dictionary dt : list5){
            	matrimonyMap.put(dt.getDict_code(), dt.getDict_name());
            	System.out.println(dt.getDict_code()+":::"+dt.getDict_name());
            }
            DictionaryConvert.getDictionary().put("matrimony", matrimonyMap);
            //卡类别代码
            List<Dictionary> list6 = dictionaryMapper.getDictionaryByTypeId("81F111E5693443B192D0962C5CBDDE63");
            Map<String, String> cardTypeMap = new HashMap<String, String>();
            for(Dictionary dt : list6){
            	cardTypeMap.put(dt.getDict_code(), dt.getDict_name());
            	System.out.println(dt.getDict_code()+":::"+dt.getDict_name());
            }
            DictionaryConvert.getDictionary().put("cardType", cardTypeMap);
        }
    }
	public DictionaryMapper getDictionaryMapper() {
		return dictionaryMapper;
	}
	public void setDictionaryMapper(DictionaryMapper dictionaryMapper) {
		this.dictionaryMapper = dictionaryMapper;
	}
	 
    
}