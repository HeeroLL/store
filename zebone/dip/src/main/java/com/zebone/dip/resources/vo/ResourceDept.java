package com.zebone.dip.resources.vo;

/**
 * 科室资源信息
 * @author LinBin
 */
public class ResourceDept {

	/**
	 * 科室id
	 */
	private String deptId;
	
	/**
	 * 科室名字
	 */
	private String deptName;
	
	/**
	 * 科室22位编码
	 */
	private String deptCode;
	
	/**
	 * 科室说明
	 */
	private String deptDesc;
	
	/**
	 * 所属机构
	 */
	private String orgCode;
	
	/**
	 * 所属科室22位编码
	 */
	private String parentDeptCode;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 上传机构
	 */
	private String inputOrgCode;
	
	/**
	 * 上传用户
	 */
	private String inputUserCode;

    //市县区代码
    private String cityCode;
    //乡镇街道代码
    private String townCode;
    //村、社区代码
    private String communityCode;
    //医疗机构编码（嘉兴就诊卡医院编码）
    private String medicalOrgCode;
    //归类代码
    private String classifiedCode;
    //科室分类代码
    private String deptClassifyCode;
    //院内码
    private String hospInternalCode;
    //门诊住院编码
    private String clinicHospCode;

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptDesc() {
		return deptDesc;
	}

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getParentDeptCode() {
		return parentDeptCode;
	}

	public void setParentDeptCode(String parentDeptCode) {
		this.parentDeptCode = parentDeptCode;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getInputOrgCode() {
		return inputOrgCode;
	}

	public void setInputOrgCode(String inputOrgCode) {
		this.inputOrgCode = inputOrgCode;
	}

	public String getInputUserCode() {
		return inputUserCode;
	}

	public void setInputUserCode(String inputUserCode) {
		this.inputUserCode = inputUserCode;
	}

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public String getCommunityCode() {
        return communityCode;
    }

    public void setCommunityCode(String communityCode) {
        this.communityCode = communityCode;
    }

    public String getMedicalOrgCode() {
        return medicalOrgCode;
    }

    public void setMedicalOrgCode(String medicalOrgCode) {
        this.medicalOrgCode = medicalOrgCode;
    }

    public String getClassifiedCode() {
        return classifiedCode;
    }

    public void setClassifiedCode(String classifiedCode) {
        this.classifiedCode = classifiedCode;
    }

    public String getDeptClassifyCode() {
        return deptClassifyCode;
    }

    public void setDeptClassifyCode(String deptClassifyCode) {
        this.deptClassifyCode = deptClassifyCode;
    }

    public String getHospInternalCode() {
        return hospInternalCode;
    }

    public void setHospInternalCode(String hospInternalCode) {
        this.hospInternalCode = hospInternalCode;
    }

    public String getClinicHospCode() {
        return clinicHospCode;
    }

    public void setClinicHospCode(String clinicHospCode) {
        this.clinicHospCode = clinicHospCode;
    }
}
