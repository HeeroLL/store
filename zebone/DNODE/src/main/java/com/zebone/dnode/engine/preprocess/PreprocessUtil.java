package com.zebone.dnode.engine.preprocess;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.zebone.dnode.engine.preprocess.vo.ParseLogInfo;


/**
 * 预处理任务工具类
 *
 * @author 杨英
 * @version 2013-12-12 上午10:12
 */
public class PreprocessUtil {

    /**
     * 获取未加工数据的map信息,key为empiNo和docNo,值为id
     *
     * @param parseInfoLic
     */
    public static Map<String, String> getUndressedIdMapInfo(List<ParseLogInfo> parseInfoLic) {
        Map<String, String> oMap = new LinkedHashMap<String, String>();
        if (parseInfoLic != null && parseInfoLic.size() > 0) {
            for (ParseLogInfo parseLogInfo : parseInfoLic) {
                String id = parseLogInfo.getId();
                String key = parseLogInfo.getEmpiNo() + "&" + parseLogInfo.getDocNo();
                String idInfo = oMap.get(key);
                if (idInfo != null && !"".equals(idInfo)) {
                    oMap.put(key, idInfo + "," + id );
                } else {
                    oMap.put(key, id );
                }
            }
        }
        return oMap;
    }
}
