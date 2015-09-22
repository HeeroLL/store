package com.zebone.btp;

/**
 * 系统常量
 * @author 宋俊杰
 *
 */
public interface Constant {
	
	static final String TOP_MODULE_ID = "1000";
	/** 机构层级码增量 */
	static final String ORGAN_INCREMENT_VALUE = "101";
	
	static final String MHO_CODE="100";

	static final String DICTTYPE = "1";
	
//	public static final String DICT_HOSPXZ="yiliaojigousuoyouzhixingzhi";
	
//	public static final String DICT_HOSPDJ="yiliaojigouidengji";
	
//	public static final String DICT_HOSPTYPE="jigouleixing";
	
	/**
	 * 超级管理员的账户名
	 */
	static final String SUPERADMIN_LOGINNAME = "admin";
	
	/**
	 * 系统默认皮肤
	 */
	static final String DEFAULT_SKIN = "default";
	
	
	/** ********** MDM常量 start add by ouyangixn ********** */
	
	/** 自动JavaBean对象存放路径*/
	static final String PACKAGE_PATH = "com.zebone.btp.mdm.tb";
	/** 工程目录*/
	static final String PROJECT_PATH = System.getProperty("user.dir");
	/** 自动JavaBean对象目录 相对路径*/
	static final String BEAN_PAHT = "\\src\\com\\zebone\\btp\\mdm\\tb\\";
	/** 自动JavaBean对象目录 绝对路径*/
	static final String TB_PATH = PROJECT_PATH + BEAN_PAHT;
	/** 类型正常标识*/
	static final String MDT_FLAG_NORNAL = "0";
	/** 根类型TYPE值*/
	static final String MDT_TYPE = "0";
	/** 根名称*/
	static final String MDT_NAME = "主数据类型";
	/** 顶层根类型值*/
	static final String MDT_ROOT = "root";
	/** 默认主键ID键*/
	static final String DEFAULT_ID = "mdtid";
	/** ********** MDM常量 end add by ouyangixn ********** */
	
	/** ********** Empi管理系统常量 start add by ouyangixn ********** */
	/** 状态 1正常 */
	public static final String STATE_NORMAL = "1"; 
	
	/** 状态 0注销 */
	public static final String STATE_CANCEL = "0"; 
	
	/** 标识 1正常 */
	public static final String DEL_FLAG_NORMAL = "1"; 
	
	/** 标识 1删除 */
	public static final String DEL_FLAG_DEL = "0"; 
	
	/** 性别 男 */
	public static final String SEX_MAN = "男"; 
	
	/** 性别 女 */
	public static final String SEX_FEMALE = "女"; 
	
	/** 导入EMPI存在时是否更新基本信息 0 否（default）1 是 */
	public static final String UPDATE_EMPI_FLAG = "1"; 
	
	/** 更新EMPI时是否更新对应的卡card信息0 否 1 是（default） */
	public static final String UPDATE_CARD_FLAG = "1"; 
	
	/** EMPI类型 身份证*/
	public static final String IDENTIFY_CARD_NO = "1"; 
	
	/** EMPI类型 军官证*/
	public static final String OFFICER_CARD_NO = "2"; 
	
	/** EMPI类型 护照*/
	public static final String PASSPORT_CARD_NO = "3"; 
	
	/** 导入数据类型 人口信息*/
	public static final String IMPORT_DATA_TYPE_PERSON = "0"; 
	
	/** 导入数据类型 社保信息*/
	public static final String IMPORT_DATA_TYPE_SOCIAL = "1"; 

	/** 导入数据类型 农保信息*/
	public static final String IMPORT_DATA_TYPE_FARM = "2"; 
	
	/** 导入数据类型 一卡通信息*/
	public static final String IMPORT_DATA_TYPE_CARD = "3"; 

	/** 错误信息代码 E01证件格式不正确*/
	public static final String ERR_CODE_ICN_INVALIDATE = "E01"; 
	
	/** 错误信息代码 E02证件已经注册*/
	public static final String ERR_CODE_ICN_EXISTS = "E02"; 

	/** 错误信息代码 E04卡已经注册*/
	public static final String ERR_CODE_CARD_EXISTS = "E04"; 
	
	/** 错误信息代码 E07其他错误*/
	public static final String ERR_CODE_OTHER = "E07";
	
	/** ********** Empi管理系统常量 end add by ouyangixn ********** */
	
	/** 定义json的文本类型 */
	public static final String TEXT_JSON = "text/json; charset=utf-8";
}
