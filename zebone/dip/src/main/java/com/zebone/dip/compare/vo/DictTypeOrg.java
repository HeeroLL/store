package com.zebone.dip.compare.vo;
/**
 * 字典类型基本信息（机构）
 */
public class DictTypeOrg {
    /**字典类型标识*/
    private String typeId;
    /**类型名称*/
    private String typeName;
    /**类型编码*/
    private String typeCode;
    /**说明*/
    private String remark;
    /**是否已删除（0未删除，1已删除）*/
    private Integer isDeleted;
    /**父类型标识*/
    private String parentId;
    /**版本*/
    private String version;
    /**机构编码*/
    private String orgCode;

    public String getTypeId() {
        return typeId;
    }
    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }
    public String getTypeCode() {
        return typeCode;
    }
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
    public String getParentId() {
        return parentId;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }
    public String getOrgCode() {
        return orgCode;
    }
    public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }
}