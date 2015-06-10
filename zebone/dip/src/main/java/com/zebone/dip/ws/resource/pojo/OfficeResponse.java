package com.zebone.dip.ws.resource.pojo;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("response")
public class OfficeResponse {
	 
	@XStreamAlias("head")
	private ResponseHead responseHead = new ResponseHead() ;
	
	@XStreamAlias("body")
	private OfficeResponseBody officeResponseBody = new OfficeResponseBody();

	public ResponseHead getResponseHead() {
		return responseHead;
	}

	public void setResponseHead(ResponseHead responseHead) {
		this.responseHead = responseHead;
	}
		
    public OfficeResponseBody getOfficeResponseBody() {
		return officeResponseBody;
	}

	public void setOfficeResponseBody(OfficeResponseBody officeResponseBody) {
		this.officeResponseBody = officeResponseBody;
	}


	public static class OfficeResponseBody{
		
		@XStreamImplicit(itemFieldName="item")
		private List<ResponseOfficeItem> responseOfficeItemList = new ArrayList<ResponseOfficeItem>();

		public List<ResponseOfficeItem> getResponseOfficeItemList() {
			return responseOfficeItemList;
		}

		public void setResponseOfficeItemList(
				List<ResponseOfficeItem> responseOfficeItemList) {
			this.responseOfficeItemList = responseOfficeItemList;
		}
		
			
    }
	
	
	public static class ResponseOfficeItem{
	
		private String deptCode;
		
		private String deptName;
		
		private String deptDesc;
		
		private String orgCode;
		
		private String parentDeptCode;

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
        //门诊住院编码
        private String clinicHospCode;
        //科室分类代码
        private String deptClassifyCode;
        //院内码
        private String hospInternalCode;

		public String getDeptCode() {
			return deptCode;
		}

		public void setDeptCode(String deptCode) {
			this.deptCode = deptCode;
		}

		public String getDeptName() {
			return deptName;
		}

		public void setDeptName(String deptName) {
			this.deptName = deptName;
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

        @Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Office [deptCode=");
			builder.append(deptCode);
			builder.append(", deptName=");
			builder.append(deptName);
			builder.append(", deptDesc=");
			builder.append(deptDesc);
			builder.append(", orgCode=");
			builder.append(orgCode);
			builder.append(", parentDeptCode=");
			builder.append(parentDeptCode);
            builder.append(", cityCode=");
            builder.append(cityCode);
            builder.append(", townCode=");
            builder.append(townCode);
            builder.append(", communityCode=");
            builder.append(communityCode);
            builder.append(", medicalOrgCode=");
            builder.append(medicalOrgCode);
            builder.append(", classifiedCode=");
            builder.append(classifiedCode);
            builder.append(", clinicHospCode=");
            builder.append(clinicHospCode);
            builder.append(", deptClassifyCode=");
            builder.append(deptClassifyCode);
            builder.append(", hospInternalCode=");
            builder.append(hospInternalCode);
			builder.append("]");
			return builder.toString();
		}
			
	}
	
}
