package com.isell.service.order.po;

public class SendPresonalItems {
	/**商品序号**/
	private String goodsOrder;
	/**行邮税号**/
	private String codeTs;
	/**商品货号 （货号即指料号，与仓储企业备案的电子账册中料号数据一致）(不超过50字符)**/
	private String goodsItemNo;
	/**物品名称**/
	private String goodsName;
	/**商品规格，型号**/
	private String goodsModel;
	/**成交币制（三字代码）**/
	private String tradeCurr;
	/**成交总价（包裹实际成交的金额）**/
	private String tradeTotal;
	/**申报单价(是物品申报的价值不是账单多的是当前行的金额)**/
	private String declPrice;
	/**申报总价**/
	private String declTotalPrice;
	/**用途**/
	private String useTo;
	/**申报数量**/
	private String declareCount;
	/**申报计量单位(三字代码)**/
	private String goodsUnit;
	/**商品毛重**/
	private String goodsGrossWeight;
	/**第一单位**/
	private String firstUnit;
	/**第一数量**/
	private String firstCount;
	/**第二单位**/
	private String secondUnit;
	/**第二数量**/
	private String secondCount;
	private String originCountry;
	public String getGoodsOrder() {
		return goodsOrder;
	}
	public void setGoodsOrder(String goodsOrder) {
		this.goodsOrder = goodsOrder;
	}
	public String getCodeTs() {
		return codeTs;
	}
	public void setCodeTs(String codeTs) {
		this.codeTs = codeTs;
	}
	public String getGoodsItemNo() {
		return goodsItemNo;
	}
	public void setGoodsItemNo(String goodsItemNo) {
		this.goodsItemNo = goodsItemNo;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsModel() {
		return goodsModel;
	}
	public void setGoodsModel(String goodsModel) {
		this.goodsModel = goodsModel;
	}
	public String getTradeCurr() {
		return tradeCurr;
	}
	public void setTradeCurr(String tradeCurr) {
		this.tradeCurr = tradeCurr;
	}
	public String getTradeTotal() {
		return tradeTotal;
	}
	public void setTradeTotal(String tradeTotal) {
		this.tradeTotal = tradeTotal;
	}
	public String getDeclPrice() {
		return declPrice;
	}
	public void setDeclPrice(String declPrice) {
		this.declPrice = declPrice;
	}
	public String getDeclTotalPrice() {
		return declTotalPrice;
	}
	public void setDeclTotalPrice(String declTotalPrice) {
		this.declTotalPrice = declTotalPrice;
	}
	public String getUseTo() {
		return useTo;
	}
	public void setUseTo(String useTo) {
		this.useTo = useTo;
	}
	public String getDeclareCount() {
		return declareCount;
	}
	public void setDeclareCount(String declareCount) {
		this.declareCount = declareCount;
	}
	public String getGoodsUnit() {
		return goodsUnit;
	}
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	public String getGoodsGrossWeight() {
		return goodsGrossWeight;
	}
	public void setGoodsGrossWeight(String goodsGrossWeight) {
		this.goodsGrossWeight = goodsGrossWeight;
	}
	public String getFirstUnit() {
		return firstUnit;
	}
	public void setFirstUnit(String firstUnit) {
		this.firstUnit = firstUnit;
	}
	public String getFirstCount() {
		return firstCount;
	}
	public void setFirstCount(String firstCount) {
		this.firstCount = firstCount;
	}
	public String getSecondUnit() {
		return secondUnit;
	}
	public void setSecondUnit(String secondUnit) {
		this.secondUnit = secondUnit;
	}
	public String getSecondCount() {
		return secondCount;
	}
	public void setSecondCount(String secondCount) {
		this.secondCount = secondCount;
	}
	public String getOriginCountry() {
		return originCountry;
	}
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	
}
