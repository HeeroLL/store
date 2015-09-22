package com.zebone.pubsub.server.data;



public class DcDataAcquisition implements DataAcquisition {

	@Override
	public void getData() {
		// TODO Auto-generated method stub
		
	}
	
	/**
    
	private static final Log log = LogFactory.getLog(DcDataAcquisition.class);
	
	private QueryRunner queryRunnerDC;
	
	private SelectSql selectSql = new  DcDataSelectSql();
	
	private boolean page = true;
	
	private DbType dbType = DbType.ORACLE;
	
	private Object[] paraObject;
	
	private OraclePageParameterCal oraclePageParameterCal = new OraclePageParameterCal();
	
	private PageData<String> pageData = new PageData<String>();
	


	public DcDataAcquisition(Object[] paraObject,QueryRunner queryRunnerDC) {
		super();
		this.paraObject = paraObject;
	}


	public DcDataAcquisition(boolean page, Object[] paraObject,QueryRunner queryRunnerDC) {
		super();
		this.page = page;
		this.paraObject = paraObject;
	}


	public DcDataAcquisition(DbType dbType, Object[] paraObject, QueryRunner queryRunnerDC) {
		super();
		this.dbType = dbType;
		this.paraObject = paraObject;
	}


	public DcDataAcquisition(boolean page, DbType dbType, Object[] paraObject, QueryRunner queryRunnerDC) {
		super();
		this.page = page;
		this.dbType = dbType;
		this.paraObject = paraObject;
	}


	public PageData<String> getPageData() {
		return pageData;
	}


	@Override
	public void getData() {
		// TODO Auto-generated method stub
		PageData<String> pageData = new PageData<String>();
		try {
			if(page){
			   String ecSql = selectSql.getCountSelectSql();
			   Map<String,Object> countMap = queryRunnerDC.query(ecSql, new MapHandler(),paraObject);
			   int count = (Integer)countMap.get("count");
			   int curPage = pageData.getCurPage();
			   int totalPage = oraclePageParameterCal.calPage(count);
			   int[] calPageParameter =  oraclePageParameterCal.calPageParameter(curPage);
			   PageParameter pageParameter = new PageParameter(dbType,calPageParameter[0], calPageParameter[1]);
			   SqlPlugin pagePlugin = new PagePlugin(ecSql,pageParameter);
			   String eSql = pagePlugin.genSql();
			   List<Object[]> docXmlList = queryRunnerDC.query(eSql, new ArrayListHandler(),paraObject);
			   if(docXmlList != null && docXmlList.size() > 0){
					pageData.setDataCount(docXmlList.size());
					List<String> dataList  = new ArrayList<String>();
					for(Object strData : docXmlList){
						dataList.add(String.valueOf(strData));
					}
					pageData.setDataList(dataList);		
				}
			   if(++curPage >= totalPage){
				   pageData.setHasNext(false); 
			   }else{
				   pageData.setHasNext(true);
				   pageData.setCurPage(curPage);
			   }
			 
			}else{
				String eSql = selectSql.getSelectSql();
				List<Object[]> docXmlList = queryRunnerDC.query(eSql,new ArrayListHandler(), paraObject);
				if(docXmlList != null && docXmlList.size() > 0){
					pageData.setDataCount(docXmlList.size());
					List<String> dataList  = new ArrayList<String>();
					for(Object strData : docXmlList){
						dataList.add(String.valueOf(strData));
					}
					pageData.setDataList(dataList);		
				}
				pageData.setHasNext(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(),e);
		}
		this.pageData = pageData;
	}

   **/

}
