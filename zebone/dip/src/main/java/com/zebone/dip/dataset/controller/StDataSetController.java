/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Mar 26, 2013		标准数据管理集控制层
 */
package com.zebone.dip.dataset.controller;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.core.web.BaseController;
import com.zebone.dip.dataset.service.StDataSetService;
import com.zebone.dip.dataset.vo.StDataSet;
import com.zebone.util.UUIDUtil;
@Controller
@Scope(value="prototype")
public class StDataSetController extends BaseController {
	@Resource
	private StDataSetService stDataSetService;
	
	@RequestMapping("dataset/datasetIndex")
	public String datasetIndex(){
		return "dip/dataset/dataset_index";
	}
	
	@RequestMapping("dataset/datasetInfoList")
	@ResponseBody
	public JsonGrid datasetInfoList(StDataSet dataset,Pagination<StDataSet> page){
		page = stDataSetService.datasetInfoPage(dataset,page);
		JsonGrid jGrid = new JsonGrid("success",page.getTotalCount(),page.getData());
		return jGrid;
	}
	
	@RequestMapping("data/datasetEdit")
	public String datasetEdit(HttpServletRequest request){
		String id = request.getParameter("id");
		StDataSet sds = stDataSetService.getDatasetById(id);
		request.setAttribute("dataSet", sds);
		return "dip/dataset/dataset_edit";
	}
	
	@RequestMapping("dataset/datasetInfoSave")
	@ResponseBody
	public Map<String, Object> datasetInfoSave(StDataSet dataset){
		int result = 0;
		boolean bool = false;
		String msg = "";
		if(StringUtils.isEmpty(dataset.getId())){
			dataset.setId(UUIDUtil.getUuid());
			dataset.setDelFlag("1");
			result = stDataSetService.saveDatasetInfo(dataset);
			if(result>0){
				bool = true;
				msg = "保存成功";
			}else{
				msg = "保存失败";
			}
		}else{
			result = stDataSetService.updateDatasetInfo(dataset);
			if(result>0){
				bool = true;
				msg = "更新成功";
			}else{
				msg = "更新失败";
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", bool);
		map.put("msg", msg);
		map.put("id", dataset.getId());
		return map;
	}
	
	@RequestMapping("dataset/datasetInfoRemove")
	@ResponseBody
	public Map<String, Object> datasetInfoRemove(HttpServletRequest request){
		int result = 0;
		String id = request.getParameter("id");
		result = stDataSetService.removeDatasetByIds(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", result >0 ?true:false);
		return map;
	}
	
	/**********************************excel文件数据导入库中start*************************************/
	@Test
	public void exportDictInfo(){
		Workbook wwb = null;
	    try {
	    	wwb = Workbook.getWorkbook(new File("e:\\导入数据\\数据元.xls"));
	    	//Sheet sheet = wwb.getSheet(0);
	    	//Sheet sheet = wwb.getSheet(1);
	    	Sheet sheet = wwb.getSheet(2);
	    	//Sheet sheet = wwb.getSheet(3);
	    	int n = sheet.getRows();
	    	List<StDataSet> sdsList = new ArrayList<StDataSet>(n);
	    	int a = 0;System.out.println("========================");
	    	for(int i=0; i<n; i++){
	    		Cell[] cell = sheet.getRow(i);
	    		//System.out.println(cell[0].getContents()+"\t"+cell[1].getContents());
	    		//System.out.println(cell[4].getContents());
	    		StDataSet sds = new StDataSet();
	    		sds.setDelFlag("1");
	    		sds.setId(UUIDUtil.getUuid());
	    		sds.setpCode(cell[0].getContents());
	    		sds.setpName(cell[1].getContents());
	    		sds.setpDesc(cell[2].getContents());
	    		sds.setpType(cell[3].getContents());
	    		sds.setpFormat(cell[4].getContents());
	    		sds.setpValue(cell[0].getContents());
	    		sds.setpValueType("D1");
	    		//允许值处理
	    		//String str = cell[5].getContents();
	    		//System.out.println(str.substring(0,str.indexOf(" ")));
	    		//sds.setpValue(str.substring(0,str.indexOf(" ")));
	    		//sds.setpValueType("D3");
	    		//sds.setpValue(str);
//	    		if(str.indexOf("GB/T")>-1){
//	    			sds.setpValueType("D2");
//	    		}else{
//	    			sds.setpValueType("QT");
//	    		}
	    		System.out.println(sds.getpValue()+"\t"+sds.getpValueType());
	    		sdsList.add(sds);
	    		a++;
	    	}
	    	wwb.close();
	    	//importDictInfo(sdsList);
	    	System.out.println(a);
	    } catch (Exception e) {
	    	System.out.println("导入失败......");
	    	e.printStackTrace();
	    }
	}
	
	/**
	 * @author caixl
	 * @date Mar 27, 2013
	 * @description TODO 导入的数据插入库中
	 * @param sdsList void
	 */
	private void importDictInfo(List<StDataSet> sdsList) {
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.6:1521:ZB", "dsp", "dsp");
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("INSERT INTO P_ST_DATASET VALUES (?,?,?,?,?,?,?,?,?)");
			int s=0;
			for(StDataSet sds : sdsList){
				ps.setString(1, sds.getId());
				ps.setString(2, sds.getpCode());
				ps.setString(3, sds.getpName());
				ps.setString(4, sds.getpDesc());
				ps.setString(5, sds.getpType());
				ps.setString(6, sds.getpFormat());
				ps.setString(7, sds.getpValue());
				ps.setString(8, sds.getpValueType());
				ps.setString(9, sds.getDelFlag());
				ps.addBatch();
				s++;
				if(s==500){
					s=0;
					ps.executeBatch();
				}
			}
			ps.executeBatch();
			ps.close();
			conn.commit();
			conn.close();
		}catch(Exception e){
			System.out.println("插入数据失败......");
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch(Exception e){}
		}
	}
	/**********************************excel文件数据导入库中end*************************************/

	
}
