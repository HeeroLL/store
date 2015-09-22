package com.zebone.dnode.etl.adapter.vo;

public class FieldConf {

	private String id;

    private String fieldName;

    private String fieldDesc;

    private String fieldCode;

    private String fieldType;

    private String fieldValue;

    private String fieldFormat;

    private String onlyCode;

    private String fieldRule;
    
    private String isDeleted;
    
    private String fieldId;
    
    private String fieldRuleFormat;
    
    private String onlySort;

    private String fieldValname;
    
    private String createTime;

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getFieldValname() {
		return fieldValname;
	}

	public void setFieldValname(String fieldValname) {
		this.fieldValname = fieldValname;
	}

	public String getOnlyCode() {
		return onlyCode;
	}

	public void setOnlyCode(String onlyCode) {
		this.onlyCode = onlyCode;
	}

	public String getFieldRuleFormat() {
		return fieldRuleFormat;
	}

	public void setFieldRuleFormat(String fieldRuleFormat) {
		this.fieldRuleFormat = fieldRuleFormat;
	}

	public String getOnlySort() {
		return onlySort;
	}

	public void setOnlySort(String onlySort) {
		this.onlySort = onlySort;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public String getFieldDesc() {
        return fieldDesc;
    }

    public void setFieldDesc(String fieldDesc) {
        this.fieldDesc = fieldDesc == null ? null : fieldDesc.trim();
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode == null ? null : fieldCode.trim();
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType == null ? null : fieldType.trim();
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue == null ? null : fieldValue.trim();
    }

    public String getFieldFormat() {
        return fieldFormat;
    }

    public void setFieldFormat(String fieldFormat) {
        this.fieldFormat = fieldFormat == null ? null : fieldFormat.trim();
    }

    public String getFieldRule() {
        return fieldRule;
    }

    public void setFieldRule(String fieldRule) {
        this.fieldRule = fieldRule == null ? null : fieldRule.trim();
    }
}