package com.zebone.empi.check;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * 校验规则配置对象
 *
 * @author 杨英
 * @version 2014-4-15 下午03:03
 */
@XStreamAlias("checks")
public class CheckConfig {
    @XStreamAsAttribute
    private String path;

    @XStreamImplicit(itemFieldName = "check")
    private List<Check> checkList;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Check> getCheckList() {
        return checkList;
    }

    public void setCheckList(List<Check> checkList) {
        this.checkList = checkList;
    }


    public static class Check {
        @XStreamAsAttribute
        private String field;

        @XStreamAsAttribute
        private String fieldName;

        @XStreamImplicit(itemFieldName = "rule")
        private List<Rule> ruleList;

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public List<Rule> getRuleList() {
            return ruleList;
        }

        public void setRuleList(List<Rule> ruleList) {
            this.ruleList = ruleList;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }
    }


    public static class Rule {

        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
