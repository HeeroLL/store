package com.zebone.dip.dictionary.util;

import com.zebone.dip.dictionary.dao.DipDictionaryMapper;
import com.zebone.dip.dictionary.vo.DipDictionary;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 杨英
 * @version 2014-7-24 上午11:10
 */
public class ApplicationListenerBean implements ApplicationListener {

    @Resource
    private DipDictionaryMapper dipDictionaryMapper;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            ApplicationContext applicationContext = ((ContextRefreshedEvent) event).getApplicationContext();
            //获取国籍
            List<DipDictionary> list1 = dipDictionaryMapper.getDipDictionaryByParentId("2539698A5A6B4D4FA48DD9CBEAF4A0EA");
            Map<String, String> nationalityMap = new HashMap<String, String>();
            for(DipDictionary dt : list1){
                nationalityMap.put(dt.getDict_code(), dt.getDict_name());
            }
            DictionaryConvert.getDictionary().put("nationality", nationalityMap);
            //性别
            List<DipDictionary> list2 = dipDictionaryMapper.getDipDictionaryByParentId("FA5853DD4FE34449A06B90AD406C68AC");
            Map<String, String> sexMap = new HashMap<String, String>();
            for(DipDictionary dt : list2){
                sexMap.put(dt.getDict_code(), dt.getDict_name());
            }
            DictionaryConvert.getDictionary().put("sex", sexMap);
            //职业
            List<DipDictionary> list3 = dipDictionaryMapper.getDipDictionaryByParentId("B184243FFB4A4F0E877F7C163FF98168");
            Map<String, String> professionMap = new HashMap<String, String>();
            for(DipDictionary dt : list3){
                professionMap.put(dt.getDict_code(), dt.getDict_name());
            }
            DictionaryConvert.getDictionary().put("profession", professionMap);
            //民族
            List<DipDictionary> list4 = dipDictionaryMapper.getDipDictionaryByParentId("3DB9B62FBE124FD3A21083EBFBF9AA9F");
            Map<String, String> nationMap = new HashMap<String, String>();
            for(DipDictionary dt : list4){
                nationMap.put(dt.getDict_code(), dt.getDict_name());
            }
            DictionaryConvert.getDictionary().put("nation", nationMap);
            //婚姻状况
            List<DipDictionary> list5 = dipDictionaryMapper.getDipDictionaryByParentId("09DCE2D0E5E7479683C913193C2BC12F");
            Map<String, String> matrimonyMap = new HashMap<String, String>();
            for(DipDictionary dt : list5){
                matrimonyMap.put(dt.getDict_code(), dt.getDict_name());
            }
            DictionaryConvert.getDictionary().put("matrimony", matrimonyMap);
            //标识类别代码
            List<DipDictionary> list6 = dipDictionaryMapper.getDipDictionaryByParentId("81F111E5693443B192D0962C5CBDDE63");
            Map<String, String> cardTypeMap = new HashMap<String, String>();
            for(DipDictionary dt : list6){
                cardTypeMap.put(dt.getDict_code(), dt.getDict_name());
            }
            DictionaryConvert.getDictionary().put("cardType", cardTypeMap);
            //人员等级
            List<DipDictionary> list7 = dipDictionaryMapper.getDipDictionaryByParentId("5401c9b3567f4e2a82b8e4ffddb4f5cd");
            Map<String, String> levelMap = new HashMap<String, String>();
            for(DipDictionary dt : list7){
                levelMap.put(dt.getDict_code(), dt.getDict_name());
            }
            DictionaryConvert.getDictionary().put("starLevel", levelMap);
        }
    }
}
