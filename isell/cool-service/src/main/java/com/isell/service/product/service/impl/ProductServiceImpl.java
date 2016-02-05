package com.isell.service.product.service.impl;


import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.isell.core.config.BisConfig;
import com.isell.core.mybatis.page.PageInfo;
import com.isell.core.util.CommonUtils;
import com.isell.core.util.GenerateQRCode;
import com.isell.core.util.Record;
import com.isell.service.code.dao.CoolConfigMapper;
import com.isell.service.product.dao.CoolProductCategoryMapper;
import com.isell.service.product.dao.CoolProductGgMapper;
import com.isell.service.product.dao.CoolProductImgMapper;
import com.isell.service.product.dao.CoolProductMapper;
import com.isell.service.product.dao.CoolProductReviewMapper;
import com.isell.service.product.po.CoolProductExternal;
import com.isell.service.product.po.CoolProductExternalStock;
import com.isell.service.product.po.CoolProductExternalStockSelect;
import com.isell.service.product.po.CoolProductReviewInfo;
import com.isell.service.product.po.CoolProductSelect;
import com.isell.service.product.service.ProductService;
import com.isell.service.product.vo.CoolProduct;
import com.isell.service.product.vo.CoolProductCategory;
import com.isell.service.product.vo.CoolProductGg;
import com.isell.service.product.vo.CoolProductImg;
import com.isell.service.product.vo.CoolProductReview;
import com.isell.service.shop.dao.CoonShopLevelMapper;
import com.isell.service.shop.dao.CoonShopMapper;
import com.isell.service.shop.dao.CoonShopProductMapper;
import com.isell.service.shop.po.CoonShopProductInfo;
import com.isell.service.shop.po.CoonShopProductParam;
import com.isell.service.shop.vo.CoonShop;
import com.isell.service.shop.vo.CoonShopProduct;

/**
 * 商品服务接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年9月24日]
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	private static final Logger log = Logger.getLogger(ProductServiceImpl.class);
	
    /**
     * 商品mapper
     */
    @Resource
    private CoolProductMapper coolProductMapper;
    
    /**
     * 商品分类表mapper
     */
    @Resource
    private  CoolProductCategoryMapper coolProductCategoryMapper;
    
    /**
     * 商品规格mapper
     */
    @Resource
    private CoolProductGgMapper coolProductGgMapper;
    
    /**
     * 酷店商品表mapper
     */
    @Resource
    private CoonShopProductMapper coonShopProductMapper;
    
    /**
     * 商品图片表mapper
     */
    @Resource
    private CoolProductImgMapper coolProductImgMapper;
    
    /**
     * 商品评价表mapper
     */
    @Resource
    private CoolProductReviewMapper coolProductReviewMapper;
    
    /**
     * 酷店表mapper
     */
    @Resource
    private CoonShopMapper coonShopMapper;
    
    /**
     * 配置表mapper
     */
    @Resource
    private CoolConfigMapper coolConfigMapper;
    
    /**
     * 酷店登记表mapper
     */
    @Resource
    private CoonShopLevelMapper coonShopLevelMapper;
    
    /**
     * coolJdbcTemplate
     */
    @Resource
    private JdbcTemplate jdbcTemplate;
    
    /**
     * 配置信息
     */
    @Resource
    private BisConfig config;
    
    /**
     * 所有商品
     */
    @Override
    public PageInfo<CoolProduct> getCoolProductPageList(CoolProductSelect coolProductSelect) {
        PageInfo<CoolProduct> pageInfo = new PageInfo<CoolProduct>();
        coolProductSelect.setShelves(false); // 未下架的产品
        pageInfo.setRows(coolProductMapper.getCoolProductPageList(coolProductSelect.getRowBounds(), coolProductSelect));
        pageInfo.setTotal(coolProductMapper.getCoolProductPageListCount(coolProductSelect));
        if (coolProductSelect.isSearchDetail() && pageInfo.getRows() != null) {
            for (CoolProduct product : pageInfo.getRows()) {
                product.setGgList(coolProductGgMapper.findCoolProductGgList(product.getId()));
            }
        }
        return pageInfo;
    }

    @Override
    public CoolProduct getCoolProductById(CoolProductSelect param) {
        CoolProduct product = coolProductMapper.getCoolProductById(param.getId());
        if (param.isSearchDetail() && product != null) {
            product.setGgList(coolProductGgMapper.findCoolProductGgList(product.getId()));
        }
        return product;
    }

    /**
     * 获取商品分类列表
     * 
     * @return  商品分类列表
     */
	@Override
	public Record getProductTypeList() {
		Record record = new Record(); 
		boolean success = false;
		List<CoolProductCategory> typeList = coolProductCategoryMapper.findAllCoolProductCategory();
		if(CollectionUtils.isNotEmpty(typeList)){
			record.set("typeList", typeList);			
		}
		success = true;
		record.set("success", success);
		return record;
	}

	 /**
     * 分页获取商品列表
     * 
     * @param  coonShopProductPo 商品查询条件
     * @return  商品列表
     */
	@Override
	public Record getProductList(CoonShopProductParam coonShopProductParam) {
		Record record = new Record(); 
		boolean success = false;
		if(StringUtils.isEmpty(coonShopProductParam.getOrderBy())){
			coonShopProductParam.setOrderBy("12");
		}
		/**
		List<CoonShopProductInfo> list = coonShopProductMapper.getCoonShopProductInfoListPage(coonShopProductParam.getRowBounds(), coonShopProductParam);
		if(CollectionUtils.isNotEmpty(list)){
			List<CoonShopProductInfo> productList = new ArrayList<CoonShopProductInfo>();
			for(CoonShopProductInfo info : list){
				//处理价格、上架酷店数、是否在该店铺上架、评价数
				if(new BigDecimal(0).compareTo(info.getCxjg()) == 0){
					info.setPrice(info.getJg());
				}else{
					info.setPrice(info.getCxjg());
				}
				int count = coonShopProductMapper.getCoonShopProductAddedCountByGoodsId(info.getGoodsId().toString());
				info.setPutSalesNum(count);				
				CoolProductReview coolProductReview = new CoolProductReview();
				coolProductReview.setgId(info.getGoodsId());
				coolProductReview.setGgId(info.getId());
				int countReview = coolProductReviewMapper.getCountReview(coolProductReview);
				info.setReviews(countReview);
				productList.add(info);
			}
			record.set("productList", productList);
			success = true;
		}else{
			record.set("msg", "无数据");
		}
		*/
		if(StringUtils.isNotEmpty(coonShopProductParam.getName())){
			String name = coonShopProductParam.getName();
			coonShopProductParam.setName("%" + name + "%");
		}
		String stock = coonShopProductParam.getStock();
		if(StringUtils.isEmpty(stock)){
			stock = "0";
			coonShopProductParam.setSort(stock);
		}
		
		List<CoolProduct> list = coolProductMapper.getCoolProductPageListNoGg(coonShopProductParam.getRowBounds(), coonShopProductParam);
		if(CollectionUtils.isNotEmpty(list)){
			String sId = coonShopProductParam.getsId();
			BigDecimal cRate = new BigDecimal(1);
			if(StringUtils.isNotEmpty(sId)){
				cRate = coonShopLevelMapper.getLevelCrate(sId);				
			}
			
			List<CoonShopProductInfo> productList = new ArrayList<CoonShopProductInfo>();
			for(CoolProduct p : list){
				CoonShopProductInfo info = new CoonShopProductInfo();
				info.setGoodsId(p.getId());
				info.setName(p.getName());
				info.setNameEn(p.getNameEn());
				info.setType(p.getType());
				info.setLogo(p.getLogo());
				//info.setPrice(p.getFavPrice().compareTo(new BigDecimal(0))>0 ? p.getFavPrice() : p.getPrice());
				info.setDivide(p.getDivide());
				info.setcRate(cRate);
				String dist = coonShopProductParam.getDist();
				CoolProductGg gg= coolProductGgMapper.getCoolProductGgMinDrpPrice(info.getGoodsId(),stock);
				if(gg != null){
					//库存小于0 的取0
                    Float stockgg = gg.getStock();
                    if(stockgg != null && stockgg > 0){      
                    	info.setStock(gg.getStock().intValue());
                    }else{
                    	info.setStock(0);
                    }
					info.setId(gg.getId());
					info.setGg(gg.getGg());
					info.setPrice(gg.getCxjg().compareTo(new BigDecimal(0))>0 ? gg.getCxjg() : gg.getJg());
					if(StringUtils.isNotEmpty(dist) && "1".equals(dist)){ //一件代发需要供货价
						info.setDrpPrice(new BigDecimal(0).compareTo(gg.getDrpPrice()) == 0 ? gg.getCxjg() : gg.getDrpPrice());
					}
				}else{
					continue;
				}
				
				/**
				if(StringUtils.isNotEmpty(dist) && "1".equals(dist)){ //一件代发需要供货价
					//获取最低供货价格的规格信息					
					if(gg == null){
						info.setDrpPrice(info.getJg());
						//info.setGg("");
						//info.setId(0);
					}else{
						info.setDrpPrice(new BigDecimal(0).compareTo(gg.getDrpPrice()) == 0 ? gg.getDrpPrice() : gg.getCxjg());
						//info.setGg(gg.getGg());
						//info.setId(gg.getId());
					}
				}else{
					if(gg == null){
						//info.setGg("");
						//info.setId(0);
					}else{
						//info.setGg(gg.getGg());
						//info.setId(gg.getId());
					}
				}			
				*/	
				
				//处理价格、上架酷店数、是否在该店铺上架、评价数
				/**
				if(new BigDecimal(0).compareTo(info.getCxjg()) == 0){
					info.setPrice(info.getJg());
				}else{
					info.setPrice(info.getCxjg());
				}
				*/
				int count = coonShopProductMapper.getCoonShopProductAddedCountByGoodsId(info.getGoodsId().toString());
				info.setPutSalesNum(count);				
				CoolProductReview coolProductReview = new CoolProductReview();
				coolProductReview.setgId(p.getId());
				int countReview = coolProductReviewMapper.getCountReview(coolProductReview);
				info.setReviews(countReview);
				
				int salesMonth = coolProductGgMapper.getProductSalesMonth(info.getGoodsId());
				info.setSalesMonth(salesMonth);
				
				productList.add(info);
			}
			record.set("productList", productList);
			success = true;
		}else{
			record.set("msg", "无数据");
		}
		
		record.set("success", success);
		return record;
	}

	/**
     * 获取商品详情
     * 
     * @param  coonShopProductPo 商品查询条件
     * @return  商品列表
     */
	@Override
	public Record getProductDetail(CoonShopProductParam coonShopProductParam) {
		Record record = new Record(); 
		boolean success = false;
		//String sId = coonShopProductParam.getsId();
		//if(StringUtils.isNotEmpty(sId)){
		List<CoonShopProductInfo> list = coonShopProductMapper.getCoonShopProductInfoListPage(coonShopProductParam.getRowBounds(), coonShopProductParam);
		List<CoonShopProductInfo> productList = new ArrayList<CoonShopProductInfo>();
		if(CollectionUtils.isNotEmpty(list)){
			String sId = coonShopProductParam.getsId();
			BigDecimal cRate = new BigDecimal(1);
			if(StringUtils.isNotEmpty(sId)){
				cRate = coonShopLevelMapper.getLevelCrate(sId);				
			}
			
			for(CoonShopProductInfo info : list){		
				info.setcRate(cRate );
				//处理价格、上架酷店数、是否在该店铺上架、商品图片
				if(new BigDecimal(0).compareTo(info.getCxjg()) == 0){
					info.setPrice(info.getJg());
				}else{
					info.setPrice(info.getCxjg());
				}
				//库存小于0 的取0
                Integer stock = info.getStock();
                if(stock != null && stock > 0){      
                	info.setStock(stock);
                }else{
                	info.setStock(0);
                }
				int count = coonShopProductMapper.getCoonShopProductAddedCountByGoodsId(info.getGoodsId().toString());
				info.setPutSalesNum(count);		
				CoolProductReview coolProductReview = new CoolProductReview();
				coolProductReview.setgId(info.getGoodsId());
				int countReview = coolProductReviewMapper.getCountReview(coolProductReview);
				info.setReviews(countReview);
				/*
				int added = coonShopProductMapper.getCoonShopProductAddedCountByGoodsIdAndShopId(product.getGoodsId().toString(), sId);
				if(added > 0){
					product.setAdded(true);
				}else{
					product.setAdded(false);
				}
				*/
				List<CoolProductImg> imgList = coolProductImgMapper.findCoolProductImgListByGoodsId(info.getGoodsId());
				if(CollectionUtils.isNotEmpty(imgList)){
					info.setImgList(imgList);
				}				
				productList.add(info);
			}										
			record.set("productList", productList);
			success = true;
		}else{
			record.set("msg", "无数据");
		}
		//}else{
		//	record.set("msg", "参数错误，酷店主键不能为空");
		//}
		record.set("success", success);
		return record;
	}

	 /**
     * 分页查询商品评价列表
     * 
     * @param  coolProductReview 商品评价查询条件
     * @return  商品评价列表
     */
	@Override
	public Record getProductReviewListPage(CoolProductReview coolProductReview) {
		Record record = new Record(); 
		boolean success = false;
		Integer gId = coolProductReview.getgId();
		if(gId != null){
			List<CoolProductReviewInfo> reviewList = coolProductReviewMapper.getCoolProductReviewInfoListPage(coolProductReview.getRowBounds(), coolProductReview);
			if(CollectionUtils.isNotEmpty(reviewList)){
				record.set("reviewList", reviewList);
				success = true;
			}else{
				record.set("msg", "无数据");
			}
		}else{
			record.set("msg", "参数错误，商品主键不能为空");
		}
		record.set("success", success);
		return record;
	}

	 /**
     * 保存商品上架（含批量）<br>
     * 该方法配合APP，忽略仓库，直接上架
     * 
     * @param  coonShopProductList 商品上架信息
     * @return  是否保存成功
     */
	@Override
	public Record saveShopProduct(List<CoonShopProduct> coonShopProductList) {
		Record record = new Record(); 
		boolean success = false;
		String msg = "";
		if(CollectionUtils.isNotEmpty(coonShopProductList)){
			int result = 0;
			int order = 0;
			Integer maxOrder = 0;			
			int size = coonShopProductList.size();
			for(CoonShopProduct sProduct : coonShopProductList){
				boolean added = sProduct.getAdded();				
				success = false;
				result = 0;
				String sId = sProduct.getsId();
				if(StringUtils.isNotEmpty(sId)){
					if(order == 0){						
						maxOrder = coonShopProductMapper.getMaxOrderByShopId(sId);
						if(maxOrder == null){
							maxOrder = 1;
						}else{
							maxOrder ++;
						}
					}
					String pId = sProduct.getpId();
					if(StringUtils.isNotEmpty(pId)){
						//判断是否已添加过上架信息
						CoonShopProduct product = coonShopProductMapper.getCoonShopProduct(sProduct);
						if(added){ //上架
							if(product == null){
								String uuid = CommonUtils.uuid();
								sProduct.setId(uuid);
								sProduct.setOrder(maxOrder);
								sProduct.setIsNew(CoonShopProduct.IS_NEW_0);
								//处理二维码
								CoonShop shop = coonShopMapper.getCoonShopById(sId);
								if(shop != null){
									String s_code = shop.getCode();
									String webSite = "http://" + s_code + ".m." + config.getBaseDomain() + "/product/" + pId;
						            String dirPath = "/shop/" + sId + "/";
						            String dir = config.getImgLocal();
						            String qrCode = dirPath + "qr_" + pId + ".png";
						            new File(dir + dirPath).mkdirs();
						            File file = new File(dir + qrCode);
						            if (file.exists()) {
						                FileUtils.deleteQuietly(file);
						            }
						            //CommonUtils.createQRCode(webSite, dir + qrCode, "/wap/images/xiaocoon1.png",10);
						            try {
										GenerateQRCode.generate("qr_" + pId + ".png", webSite, dir+ dirPath, 200, 200);
									} catch (Exception e) {
										log.info("酷店编码为" + s_code + "的商品 " + pId + "生成二维码失败");  
									}
						            sProduct.setQrCode(qrCode);
						            sProduct.setAdded(true);
									result = coonShopProductMapper.saveCoonShopProduct(sProduct);
									if(result > 0){
										order ++;
										maxOrder ++;
										success = true;
									}else{
										//record.set("msg", "保存商品上架信息失败");
										msg += " 保存商品上架信息失败";
										break;
									}
								}else{
									//record.set("msg", "参数错误，无法根据酷店主键获取酷店信息");
									msg += " 参数错误，无法根据酷店主键获取酷店信息";
									break;
								}							
							}else{
								if(size == 1){
									msg = "商品编号为" + pId + "的商品已经上过架"; 
								}								
								continue;							
								/**
								product.setAdded(sProduct.getAdded());
								result = coonShopProductMapper.updateCoonShopProduct(product);
								if(result > 0){
									order ++;
									success = true;
								}else{
									record.set("msg", "更新商品上架信息失败");
									break;
								}
								*/
							}
						}else{//下架
							if(product == null){
								msg += " 参数错误，不存在该上架商品";
							}else{
								result = coonShopProductMapper.deleteCoonShopProduct(product.getId());
								if(result > 0){
									success = true;
								}else{
									msg += " 商品下架失败";
								}
							}							
						}
						
					}else{
						//record.set("msg", "参数错误，商品主键不能为空");
						msg += " 参数错误，商品主键不能为空";
						break;
					}
				}else{
					//record.set("msg", "");
					msg += " 参数错误，店铺主键不能为空";
					break;
				}
			}
		}else{
			//record.set("msg", "参数错误");
			msg += " 参数错误";
		}
		record.set("msg", msg);
		record.set("success", success);
		return record;
	}

	/**
     * 更新商品排序
     * 
     * @param  coonShopProductList 商品上架信息
     * @return  是否更新成功
     */
	@Override
	public Record updateShopProductOrder(List<CoonShopProduct> coonShopProductList) {		
		Record record = new Record(); 
		boolean success = false;
		if(CollectionUtils.isNotEmpty(coonShopProductList)){
			int result = 0;
			for(CoonShopProduct sProduct : coonShopProductList){
				result = 0;
				success = false;
				String sId = sProduct.getsId();
				String pId = sProduct.getpId();
				Integer order = sProduct.getOrder();
				if(StringUtils.isEmpty(sId) || StringUtils.isEmpty(pId) || order==null){
					record.set("msg", "参数错误,酷店id、商品id、排序号不能为空");
				}else{
					CoonShopProduct product = coonShopProductMapper.getCoonShopProduct(sProduct);
					if(product != null){
						//TODO 暂时只支持向上排序
						//获取排序号比order大的或者相同的商品列表
						List<CoonShopProduct> pList = coonShopProductMapper.getCoonShopProductList(sId, order,pId);
						product.setOrder(order);						
						result = coonShopProductMapper.updateCoonShopProduct(product);
						if(result > 0){							
							//重新排序
							Integer pxh = order;
							for(CoonShopProduct p : pList){
								pxh++;
								p.setOrder(pxh);
								coonShopProductMapper.updateCoonShopProduct(p);
							}							
							success = true;
							
						}else{
							record.set("msg", "更新排序错误");
							break;
						}
					}else{
						record.set("msg", "参数错误，无法获取上架商品信息");
						break;
					}
				}
			}			
		}else{
			record.set("msg", "参数格式错误");
		}
		record.set("success", success);
		return record;
	}

	/**
     * 保存新品上线
     * 
     * @param  coonShopProductList 商品上架信息
     * @return  是否保存成功
     */
	@Override
	public Record saveShopProductNew(List<CoonShopProduct> coonShopProductList) {
		Record record = new Record(); 
		boolean success = false;
		if(CollectionUtils.isNotEmpty(coonShopProductList)){
			int result = 0;
			int order = 0;
			Integer maxOrder = 0;
			for(CoonShopProduct sProduct : coonShopProductList){							
				success = false;
				result = 0;
				String sId = sProduct.getsId();
				if(StringUtils.isNotEmpty(sId)){
					if(order == 0){						
						maxOrder = coonShopProductMapper.getMaxOrderByShopId(sId);
						if(maxOrder == null){
							maxOrder = 1;
						}
					}
					String pId = sProduct.getpId();
					if(StringUtils.isNotEmpty(pId)){
						//判断是否已添加过上架信息
						CoonShopProduct product = coonShopProductMapper.getCoonShopProduct(sProduct);
						if(product == null){
							String uuid = CommonUtils.uuid();
							sProduct.setId(uuid);
							sProduct.setOrder(maxOrder);
							sProduct.setAdded(true);
							sProduct.setIsNew(CoonShopProduct.IS_NEW_1);
							//处理二维码
							CoonShop shop = coonShopMapper.getCoonShopById(sId);
							if(shop != null){
								String s_code = shop.getCode();
								String webSite = "http://" + s_code + ".m." + config.getBaseDomain() + "/product/" + pId;
					            String dirPath = "/shop/" + sId + "/";
					            String dir = config.getImgLocal();
					            String qrCode = dirPath + "qr_" + pId + ".png";
					            new File(dir + dirPath).mkdirs();
					            File file = new File(dir + qrCode);
					            if (file.exists()) {
					                FileUtils.deleteQuietly(file);
					            }
					            //CommonUtils.createQRCode(webSite, dir + qrCode, "/wap/images/xiaocoon1.png",10);
					            try {
									GenerateQRCode.generate("qr_" + pId + ".png", webSite, dir+ dirPath, 200, 200);
								} catch (Exception e) {
									log.info("酷店编码为" + s_code + "的商品 " + pId + "生成二维码失败");  
								}
					            sProduct.setQrCode(qrCode);
								result = coonShopProductMapper.saveCoonShopProduct(sProduct);
								if(result > 0){
									order ++;
									maxOrder ++;
									success = true;
								}else{
									record.set("msg", "保存新品上线信息失败");
									break;
								}
							}else{
								record.set("msg", "参数错误，无法根据酷店主键获取酷店信息");
								break;
							}
							
						}else{
							product.setAdded(sProduct.getAdded());
							product.setIsNew(CoonShopProduct.IS_NEW_1);
							result = coonShopProductMapper.updateCoonShopProduct(product);
							if(result > 0){
								order ++;
								success = true;
							}else{
								record.set("msg", "更新新品上线信息失败");
								break;
							}
						}
					}else{
						record.set("msg", "参数错误，商品主键不能为空");
						break;
					}
				}else{
					record.set("msg", "参数错误，店铺主键不能为空");
					break;
				}
			}
		}else{
			record.set("msg", "参数格式错误");
		}
		record.set("success", success);
		return record;
	}

	/**
     * 分页	获取已上架商品列表
     * 
     * @param  coonShopProductParam 商品查询条件
     * @return  已上架商品列表
     */
	@Override
	public Record getShopProductListPage(CoonShopProductParam coonShopProductParam) {
		Record record = new Record(); 
		boolean success = false;
		String sId = coonShopProductParam.getsId();
		if(StringUtils.isNotEmpty(sId)){
			String orderBy = coonShopProductParam.getOrderBy();
			if(StringUtils.isEmpty(orderBy)){
				coonShopProductParam.setOrderBy("99");
			}
			String stockP = coonShopProductParam.getStock();
			if(StringUtils.isEmpty(stockP)){
				stockP = "0";
				coonShopProductParam.setSort(stockP);
			}
			//List<CoonShopProductInfo> list = coonShopProductMapper.getCoonShopProductAddedListPage(coonShopProductParam.getRowBounds(), coonShopProductParam);
			List<CoonShopProductInfo> list = coonShopProductMapper.getCoonShopProductAddedPage(coonShopProductParam.getRowBounds(), coonShopProductParam);
			
			if(CollectionUtils.isNotEmpty(list)){
				List<CoonShopProductInfo> productList = new ArrayList<CoonShopProductInfo>();
				for(CoonShopProductInfo info : list){
					//获取最低供货价格的规格信息
					CoolProductGg gg= coolProductGgMapper.getCoolProductGgMinDrpPrice(info.getGoodsId(),stockP);
					if(gg != null){
						//已上架不需要供货价
						//info.setDrpPrice(new BigDecimal(0).compareTo(gg.getDrpPrice()) == 0 ? gg.getDrpPrice() : gg.getCxjg());
						//库存小于0 的取0
		                Float stock = gg.getStock();
		                if(stock != null && stock > 0){      
		                	info.setStock(stock.intValue());
		                }else{
		                	info.setStock(0);
		                }
						info.setGg(gg.getGg());
						info.setId(gg.getId());
						info.setPrice(gg.getCxjg().compareTo(new BigDecimal(0))>0 ? gg.getCxjg() : gg.getJg());
						//info.setStock(gg.getStock().intValue());	
					}else{
						continue;
					}
					/**
					if(gg == null){
						info.setDrpPrice(info.getJg());
						info.setGg("");
					}else{
						info.setDrpPrice(new BigDecimal(0).compareTo(gg.getDrpPrice()) == 0 ? gg.getDrpPrice() : gg.getCxjg());
						info.setGg(gg.getGg());
					}				
					*/	
					
					//处理价格、上架酷店数、是否在该店铺上架
					int count = coonShopProductMapper.getCoonShopProductAddedCountByGoodsId(info.getGoodsId().toString());
					info.setPutSalesNum(count);
					int added = coonShopProductMapper.getCoonShopProductAddedCountByGoodsIdAndShopId(info.getGoodsId().toString(), sId);
					if(added > 0){
						info.setAdded(true);
					}else{
						info.setAdded(false);
					}
					int salesMonth = coolProductGgMapper.getProductSalesMonth(info.getGoodsId());
					info.setSalesMonth(salesMonth);
					CoolProductReview coolProductReview = new CoolProductReview();
					coolProductReview.setgId(info.getGoodsId());
					int countReview = coolProductReviewMapper.getCountReview(coolProductReview);
					info.setReviews(countReview);
					productList.add(info);
				}
				record.set("productList", productList);		
				record.set("total", coonShopProductMapper.getCoonShopProductAddedPageCount(coonShopProductParam));
				success = true;
			}else{
				record.set("msg", "无数据");
			}
		}else{
			record.set("msg", "参数错误，酷店主键不能为空");
		}
		record.set("success", success);		
		return record;
	}

	/**
     * 分页	获取未上架商品列表
     * 
     * @param  coonShopProductParam 商品查询条件
     * @return  未上架商品列表
     */
	@Override
	public Record getShopProductNoList(CoonShopProductParam coonShopProductParam) {
		Record record = new Record(); 
		boolean success = false;
		String sId = coonShopProductParam.getsId();
		if(StringUtils.isNotEmpty(sId)){
			//List<CoonShopProductInfo> list = coonShopProductMapper.getCoonShopProductNoAddedListPage(coonShopProductParam.getRowBounds(), coonShopProductParam);
			List<CoonShopProductInfo> list = coonShopProductMapper.getCoonShopProductNoAddedPage(coonShopProductParam.getRowBounds(), coonShopProductParam);
			if(CollectionUtils.isNotEmpty(list)){
				List<CoonShopProductInfo> productList = new ArrayList<CoonShopProductInfo>();
				for(CoonShopProductInfo info : list){
					//获取最低供货价格的规格信息
					CoolProductGg gg= coolProductGgMapper.getCoolProductGgMinDrpPrice(info.getGoodsId(),"0");
					if(gg == null){
						info.setDrpPrice(info.getJg());
					}else{
						//已上架不需要供货价
						//info.setDrpPrice(new BigDecimal(0).compareTo(gg.getDrpPrice()) == 0 ? gg.getDrpPrice() : gg.getCxjg());
						info.setGg(gg.getGg());
						info.setId(gg.getId());
						info.setPrice(gg.getCxjg().compareTo(new BigDecimal(0))>0 ? gg.getCxjg() : gg.getJg());
						info.setStock(gg.getStock().intValue());	
						info.setDrpPrice(new BigDecimal(0).compareTo(gg.getDrpPrice()) == 0 ? gg.getCxjg() : gg.getDrpPrice());
					}
					//处理价格、上架酷店数、是否在该店铺上架
					info.setPrice(info.getJg());
					/**
					if(new BigDecimal(0).compareTo(info.getCxjg()) == 0){
						info.setPrice(info.getJg());
					}else{
						info.setPrice(info.getCxjg());
					}
					*/
					int count = coonShopProductMapper.getCoonShopProductAddedCountByGoodsId(info.getGoodsId().toString());
					info.setPutSalesNum(count);
					int added = coonShopProductMapper.getCoonShopProductAddedCountByGoodsIdAndShopId(info.getGoodsId().toString(), sId);
					if(added > 0){
						info.setAdded(true);
					}else{
						info.setAdded(false);
					}
					int salesMonth = coolProductGgMapper.getProductSalesMonth(info.getGoodsId());
					info.setSalesMonth(salesMonth);
					productList.add(info);
				}
				record.set("productList", productList);
				success = true;
			}else{
				record.set("msg", "无数据");
			}
		}else{
			record.set("msg", "参数错误，酷店主键不能为空");
		}
		record.set("success", success);
		return record;
	}

	/**
     * 获取热门商品列表
     * 
     * @return  商品列表
     */
	@Override
	public Record getHotTrendProductList() {
		Record record = new Record(); 
		boolean success = false;
		List<CoonShopProductInfo> productList  = coolProductMapper.getHotTrendProductList();
		if(CollectionUtils.isNotEmpty(productList)){
			record.set("productList", productList);
			success = true;
		}else{
			record.set("msg", "无数据");
		}
		record.set("success", success);
		return record;
	}
	
	
	/**
	 * 获取商品信息
	 */
	@Override
	public List<CoolProductExternal> getProductInfo(CoolProductExternal product) {
		List<CoolProductExternal>list=this.coolProductMapper.getProductInfo(product);
		for(CoolProductExternal info:list)
		{
			info.setUnits(this.coolProductMapper.queryGoodsAliUnit(info));
		}
		return list;
	}

	@Override
	public List<CoolProductExternalStockSelect> getProductStock(CoolProductExternalStock product) {
		String[] gid = null;
		String[] code = null;
		if(StringUtils.isNotBlank(product.getGid())){
			 gid = product.getGid().split(",");
		}
		if(StringUtils.isNotBlank(product.getCode())){
			 code = product.getCode().split(",");
		}
		return coolProductGgMapper.getProductStock(gid,code);
	}


}
