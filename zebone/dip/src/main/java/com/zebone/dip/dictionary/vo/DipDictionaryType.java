package com.zebone.dip.dictionary.vo;
/**
 * 字典类型VO
 * @author YinCm
 * @version 2013-7-18 上午10:10:20
 */
public class DipDictionaryType {
	private String type_id;
	private String type_name;
	private String type_code;
	private String remark;
	private String parent_id;
	private String version;
    private String standard_code;
	private int is_deleted;
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getType_code() {
		return type_code;
	}
	public void setType_code(String type_code) {
		this.type_code = type_code;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(int is_deleted) {
		this.is_deleted = is_deleted;
	}
    public String getStandard_code() {
        return standard_code;
    }
    public void setStandard_code(String standard_code) {
        this.standard_code = standard_code;
    }
}
