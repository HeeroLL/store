package com.zebone.core.util;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.springframework.ui.ModelMap;

import java.util.List;

/**
 * xml解析工具类
 * @author 杨英
 * @version 2013-8-16 上午10:55
 */
public class ParseUtil {

    /**
     * 获取节点具体信息
     * @param element 节点元素
     * @param oMap
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static ModelMap parseElement(Element element, ModelMap oMap) {
        List els = element.elements();
        // 此节点还有子节点
        if (els != null && els.size() > 0) {
            for (int i = 0; i < els.size(); i++) {
                Element subElement = (Element) els.get(i);
                parseElement(subElement, oMap);
            }
        } else {
            //此节点没有子节点
            String elName = element.getName();
            String textValue = element.getText();
            //<item>节点标签
            if ("item".equals(elName)) {
                Attribute keyAttr = element.attribute("key");
                String key = keyAttr.getValue();
                oMap.put(key, textValue);
            }
        }
        return oMap;
    }
}
