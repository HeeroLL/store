package com.zebone.btp.mdm;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.zebone.btp.Constant;
import com.zebone.btp.mdm.vo.DBQuery;
import com.zebone.btp.mdm.vo.MDObject;
import com.zebone.btp.mdm.vo.MainDataTypeVO;
import com.zebone.btp.mdm.vo.MdtProperty;
import com.zebone.util.UUIDUtil;

/**
 * MDM 主数据管理工具类
 * 
 * @author ouyangxin
 * 
 * CreateDate: 2012-11-30
 */
public class MDMUtils {

	private static Logger log = Logger.getLogger(MDMUtils.class);

	/**
	 * 根据表名生成类名 规则
	 * 
	 * @param tableName
	 * @return
	 */
	public static String getTableClassName(String tableName) {

		StringBuilder tbn = new StringBuilder(50);
		String[] temp = tableName.split("_");
		for (String ns : temp) {
			ns = ns.trim();
			tbn.append(ns.substring(0, 1).toUpperCase()
					+ ns.substring(1).toLowerCase());
		}
		return tbn.toString();
	}

	/**
	 * 根据表名生成带路径的类名字符串
	 * 
	 * @param className
	 * @return
	 */
	public static String getClassNameWithPath(String tableName) {

		StringBuffer sb = new StringBuffer(100);
		sb.append(Constant.PACKAGE_PATH);
		sb.append(".");
		sb.append(getTableClassName(tableName));

		return sb.toString();
	}

	/**
	 * 
	 * 根据表结构信息找到对应的类，并为对应的字段设值，封装成List<MDObject>返回
	 * 
	 * @param dbQuery
	 * @param tempList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List process(DBQuery dbQuery,
			List<HashMap<String, String>> tempList) {

		List<MDObject> dataList = new ArrayList<MDObject>(tempList.size()); // 返回结果
		// map对应的key是大写形式，需转换
		String fields = dbQuery.getDbFields().toUpperCase(); // 字段名字符串
		MDObject obj = null;

		try {
			for (HashMap<String, String> map : tempList) {
				String[] v = fields.split(",");
				obj = new MDObject();
				for (int k = 0; k < v.length; k++) {
					v[k] = v[k].trim();
					Method method = MDObject.class.getMethod(("setPro" + k),
							String.class);
					method.invoke(obj, getStringValue(map.get(v[k])));
				}
				dataList.add(obj);
			}
		} catch (Exception e) {
			log.error("class error", e);
		}

		return dataList;
	}

	/**
	 * 
	 * 根据字段名形成对应的方法名 如字段fields生成setFields
	 * 
	 * @param fields
	 * @return
	 */
	public static String getMethodName(String fields) {
		String tem = fields.trim();
		StringBuffer sb = new StringBuffer(50);
		sb.append("set");
		sb.append(tem.substring(0, 1).toUpperCase());
		sb.append(tem.substring(1).toLowerCase());
		return sb.toString();
	}

	/**
	 * 页面展示 为空或者为null 则显示 未填写 <功能详细描述>
	 * 
	 * @param obj
	 * @return
	 */
	public static String getStringValue(Object obj) {
		return (null == obj ? "未填写" : String.valueOf(obj));
	}

	/**
	 * 
	 * Java反射机制创建类
	 * 
	 * @param dbQuery
	 * @param tempList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static MDObject processObject(DBQuery dbQuery,
			List<HashMap<String, String>> tempList) {
		// map对应的key是大写新式，需转换
		String fields = dbQuery.getDbFields().toUpperCase(); // 字段名字符串
		MDObject obj = null;
		try {
			for (HashMap<String, String> map : tempList) {
				String[] v = fields.split(",");
				obj = new MDObject();
				for (int k = 0; k < v.length; k++) {
					v[k] = v[k].trim();
					Method method = MDObject.class.getMethod(("setPro" + k),
							String.class);
					method.invoke(obj, getStringValue(map.get(v[k])));
				}

			}
		} catch (Exception e) {
			log.error("class error", e);
		}
		return obj;
	}

	/**
	 * 对MainDataTypeVO的属性处理
	 * 
	 * @param mdt
	 * @return
	 */
	public static boolean processMainDataType(MainDataTypeVO mdt, String code) {

		boolean flag = false;
		if (isArrayEmpty(mdt)) {

			// 处理可见 字段 字段类型 字段释义
			mdt.setMdtVisible(proArray(mdt.getVisible()));
			mdt.setMdtFields(proArray(mdt.getField()));
			mdt.setMdtFieldsType(proArray(mdt.getFieldType()));
			mdt.setMdtComments(proArray(mdt.getComment()));

			// 处理ID
			mdt.setMdtId(UUIDUtil.getUuid());

			// 处理CODE
			mdt.setMdtCode(getNextCode(code));

			// 处理类型名 中文字符转化
			mdt.setMdtName(mdt.getMdtName());

			// 处理表名
			mdt.setMdtTablename(generateTableName(mdt.getMdtTablename()));

			// 类型种类
			mdt.setMdtType(mdt.getMdtType().trim());

			// 处理字段对应属性字段
			processCorres(mdt);

			// 构造建表语句
			if (getMDTObjectSql(mdt)) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 
	 * 转化前的条件判断
	 * 
	 * @param mdt
	 * @return
	 */
	public static boolean isArrayEmpty(MainDataTypeVO mdt) {

		if (null != mdt) {
			// 字段 字段类型 字段释义 字段只要有一个为空 就返回false
			if (null == mdt.getField() || mdt.getField().length == 0) {
				return false;
			}
			if (null == mdt.getFieldType() || mdt.getFieldType().length == 0) {
				return false;
			}
			if (null == mdt.getComment() || mdt.getComment().length == 0) {
				return false;
			}
			if (null == mdt.getVisible() || mdt.getVisible().length == 0) {
				return false;
			}
			// 字段可见=字段类型与字段释义 字段 一一对应 不对应则返回false
			if (mdt.getVisible().length != mdt.getField().length
					|| mdt.getVisible().length != mdt.getComment().length) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * 将String类型的数组转化为逗号","分隔的字符串
	 * 
	 * @param array
	 * @param length
	 * @return
	 */
	public static String proArray(String[] array) {

		StringBuffer sb = new StringBuffer(500);
		for (String f : array) {
			sb.append(f + ", ");
		}
		sb.trimToSize();
		String fields = sb.toString().toLowerCase().trim();
		return (fields.substring(0, fields.length() - 1));
	}

	/**
	 * 构造建表语句
	 * 
	 * @param mdt
	 * @return
	 */
	public static boolean getMDTObjectSql(MainDataTypeVO mdt) {

		boolean flag = false;

		// 构建动态的建表语句 和注释语句
		String table = mdt.getMdtTablename().trim().toUpperCase();
		StringBuffer sb_comm = new StringBuffer(4000);
		StringBuffer sb_table = new StringBuffer(4000);
		StringBuffer sb_field = new StringBuffer(4000);

		sb_table.append(" CREATE TABLE " + table);
		sb_table.append(" (");

		try {
			String[] a_field = mdt.getField();
			String[] a_field_type = mdt.getFieldType();
			String[] a_field_comm = mdt.getComment();

			for (int i = 0; i < a_field.length; i++) {
				// 构造字段
				sb_field.append(a_field[i].trim());
				sb_field.append(" ");
				// TODO 长度的拼装
				sb_field.append(a_field_type[i].trim());
				sb_field.append(", ");

				// 构造注解
				sb_comm.append("comment on column " + table + "."
						+ a_field[i].trim() + " is '" + a_field_comm[i].trim()
						+ "'~");

			}
			sb_field.trimToSize();
			String fileds = sb_field.toString().trim();
			fileds = fileds.substring(0, fileds.length() - 1);
			sb_table.append(fileds);

			sb_table.append(" )");
			sb_table.trimToSize();

			mdt.setSqlCreate(sb_table.toString());

			sb_comm.trimToSize();
			mdt.setSqlComments(sb_comm.toString());
			flag = true;

		} catch (Exception e) {
			log.error("class error", e);
			flag = false;
		}
		return flag;
	}

	/**
	 * 根据field字段形成对应的属性字符串 <功能详细描述>
	 * 
	 * @param mdt
	 */
	public static void processCorres(MainDataTypeVO mdt) {

		String[] field = mdt.getMdtFields().split(",");
		int length = field.length;
		StringBuffer sb = new StringBuffer(300);
		for (int k = 0; k < length; k++) {
			sb.append("pro" + k + ", ");
		}
		sb.trimToSize();
		String corres = sb.toString().trim();
		corres = corres.substring(0, corres.length() - 1);
		mdt.setMdtCorres(corres);
	}

	/**
	 * 组装字段对象集合
	 * 
	 * @param mdt
	 */
	public static void processMainDataTypeVO(MainDataTypeVO mdt) {

		if (null != mdt && StringUtils.isNotEmpty(mdt.getMdtFields())) {
			List<MdtProperty> list = new ArrayList<MdtProperty>();
			String[] field = mdt.getMdtFields().split(",");
			String[] fieldType = mdt.getMdtFieldsType().split(",");
			String[] visible = mdt.getMdtVisible().split(",");
			String[] comment = mdt.getMdtComments().split(",");
			MdtProperty obj = null;
			for (int k = 0; k < field.length; k++) {
				obj = new MdtProperty();
				obj.setField(field[k].trim());
				obj.setFieldType(fieldType[k]);
				obj.setVisible(visible[k].trim());
				obj.setComment(comment[k].trim());
				list.add(obj);
			}
			mdt.setFieldList(list);
		}
	}

	/**
	 * 
	 * 自动生成Code
	 * 
	 * @param maxCode
	 * @return
	 */
	public static String getNextCode(String maxCode) {
		StringBuffer sb = new StringBuffer(10);
		String code = "";
		if (StringUtils.isEmpty(maxCode)) {
			code = "0001";
		} else {
			code = maxCode.substring(3);
			int num = Integer.parseInt(code);
			code = String.valueOf(num + 1);
		}
		sb.append("MDT" + code);
		return sb.toString();
	}

	/**
	 * 
	 * 组装表名
	 * 
	 * @param name
	 * @return
	 */
	public static String generateTableName(String name) {
		// TODO 中文字符转化

		// 表名默认加BTP_MD_开头 大写
		StringBuffer sb = new StringBuffer(50);
		sb.append("BTP_MD_");
		sb.append(name.toUpperCase().trim());
		sb.trimToSize();
		return sb.toString();
	}

	/**
	 * 
	 * 自动生成typeValue
	 * 
	 * @param typeValue
	 * @return
	 */
	public static String getNextType(String typeValue) {
		if (StringUtils.isEmpty(typeValue)) {
			return "";
		}

		String nextTypeValue = typeValue.trim();
		int num = Integer.parseInt(nextTypeValue);
		nextTypeValue = String.valueOf(num + 1);
		return nextTypeValue;
	}

	public static boolean processMainData(MainDataTypeVO mdt) {
		boolean bool = false;
		if (isArrayEmpty(mdt)) {
			try {
				// 处理属性字段
				List<String> list = Arrays.asList(mdt.getField());
				int index = list.indexOf(Constant.DEFAULT_ID);
				if (index > 0) {
					
					//如果没有增加字段只是更新 则只更新注释和是否可见
					bool = (index == mdt.getField().length-1);
					
					mdt.setMdtComments(processMDTString(mdt.getComment(), index, bool));
					mdt.setMdtVisible(processMDTString(mdt.getVisible(), index, bool));
					mdt.setMdtFields(processMDTString(mdt.getField(), index, bool));
					mdt.setMdtFieldsType(processMDTString(mdt.getFieldType(), index, bool));
					
					//新增字段
					if(!bool)
					{
						mdt.setField(processMDTArray(mdt.getField(), index));
						mdt.setFieldType(processMDTArray(mdt.getFieldType(), index));
						mdt.setComment(processMDTArray(mdt.getComment(), index));
						mdt.setVisible(processMDTArray(mdt.getVisible(), index));

						// 处理字段对应属性字段
						processCorres(mdt);

						// 组装SQL语句
						bool = processUpdateSql(mdt);
					}
				}
			} catch (ArrayIndexOutOfBoundsException ae) {
				bool = false;
				log.error("class error", ae);
			} catch (Exception e) {
				bool = false;
				log.error("class error", e);
			}
		}
		return bool;
	}

	/**
	 * 
	 * 重新组装字符串数组
	 * 
	 * @param mdt
	 * @param array
	 * @param index
	 */
	public static String[] processMDTArray(String[] array, int index) {

		String[] addField = Arrays.copyOfRange(array, index + 1, array.length);

		return addField;

	}

	/**
	 * 
	 * 返回数组字符串形式
	 * 
	 * @param array
	 * @param index
	 * @return
	 */
	public static String processMDTString(String[] array, int index, boolean justUpdate) {

		String[] newField = new String[array.length];
		newField[0] = array[index];
		System.arraycopy(array, 0, newField, 1, index);
		
		if(!justUpdate)
		{
			String[] addField = Arrays.copyOfRange(array, index + 1, array.length);
			System.arraycopy(addField, 0, newField, index + 1, addField.length);
		}
		
		// 去掉[]括号
		String temp = Arrays.toString(newField).trim();
		temp = temp.substring(1, temp.length() - 1);
		
		return temp;
	}

	/**
	 * 形成更新sql <功能详细描述>
	 * 
	 * @param array
	 * @param index
	 */
	public static boolean processUpdateSql(MainDataTypeVO mdt) {

		boolean bool = false;

		// 构建动态的建表语句 和注释语句
		String table = mdt.getMdtTablename().trim().toUpperCase();
		StringBuffer sql = new StringBuffer(4000);
		StringBuffer sqlComments = new StringBuffer(4000);

		try {
			String[] a_field = mdt.getField();
			String[] a_field_type = mdt.getFieldType();
			String[] a_field_comm = mdt.getComment();

			for (int i = 0; i < a_field.length; i++) {

				// 构造 增加表字段 sql语句
				sql.append(" ALTER TABLE " + table);
				sql.append(" ADD " + a_field[i]);
				sql.append(" " + a_field_type[i]);
				sql.append("~");

				// 构造注解 sql语句
				sqlComments.append("comment on column " + table + "."
						+ a_field[i].trim() + " is '" + a_field_comm[i].trim()
						+ "'~");
			}
			sql.trimToSize();
			mdt.setSqlCreate(sql.toString().trim());

			sqlComments.trimToSize();
			mdt.setSqlComments(sqlComments.toString().trim());

			bool = true;

		} catch (Exception e) {
			log.error("class error", e);
			bool = false;
		}
		return bool;
	}

	public static void main(String[] args) {
		//System.out.println(MDMUtils.getNextType(""));

	}
}
