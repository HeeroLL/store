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
import com.isell.service.product.po.CoolProductReviewInfo;
import com.isell.service.product.po.CoolProductSelect;
import com.isell.service.product.service.ProductService;
import com.isell.service.product.vo.CoolProduct;
import com.isell.service.product.vo.CoolProductCategory;
import com.isell.service.product.vo.CoolProductImg;
import com.isell.service.product.vo.CoolProductReview;
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
			coonShopProductParam.setOrderBy("1");
		}
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
		if(CollectionUtils.isNotEmpty(list)){
			CoonShopProductInfo product = list.get(0);					
			//处理价格、上架酷店数、是否在该店铺上架、商品图片
			if(new BigDecimal(0).compareTo(product.getCxjg()) == 0){
				product.setPrice(product.getJg());
			}else{
				product.setPrice(product.getCxjg());
			}
			int count = coonShopProductMapper.getCoonShopProductAddedCountByGoodsId(product.getGoodsId().toString());
			product.setPutSalesNum(count);				
			/*
			int added = coonShopProductMapper.getCoonShopProductAddedCountByGoodsIdAndShopId(product.getGoodsId().toString(), sId);
			if(added > 0){
				product.setAdded(true);
			}else{
				product.setAdded(false);
			}
			*/
			List<CoolProductImg> imgList = coolProductImgMapper.findCoolProductImgListByGoodsId(product.getGoodsId());
			if(CollectionUtils.isNotEmpty(imgList)){
				product.setImgList(imgList);
			}										
			record.set("product", product);
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
     * 保存商品上架（含批量）
     * 
     * @param  coonShopProductList 商品上架信息
     * @return  是否保存成功
     */
	@Override
	public Record saveShopProduct(List<CoonShopProduct> coonShopProductList) {
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
								result = coonShopProductMapper.saveCoonShopProduct(sProduct);
								if(result > 0){
									order ++;
									maxOrder ++;
									success = true;
								}else{
									record.set("msg", "保存商品上架信息失败");
									break;
								}
							}else{
								record.set("msg", "参数错误，无法根据酷店主键获取酷店信息");
								break;
							}
							
						}else{
							product.setAdded(sProduct.getAdded());
							result = coonShopProductMapper.updateCoonShopProduct(product);
							if(result > 0){
								order ++;
								success = true;
							}else{
								record.set("msg", "更新商品上架信息失败");
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
			record.set("msg", "参数错误");
		}
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
					CoonShopProduct p = coonShopProductMapper.getCoonShopProduct(sProduct);
					if(p != null){
						p.setOrder(order);						
						result = coonShopProductMapper.updateCoonShopProduct(p);
						if(result > 0){							
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
			List<CoonShopProductInfo> list = coonShopProductMapper.getCoonShopProductAddedListPage(coonShopProductParam.getRowBounds(), coonShopProductParam);
			if(CollectionUtils.isNotEmpty(list)){
				List<CoonShopProductInfo> productList = new ArrayList<CoonShopProductInfo>();
				for(CoonShopProductInfo info : list){
					//处理价格、上架酷店数、是否在该店铺上架
					if(new BigDecimal(0).compareTo(info.getCxjg()) == 0){
						info.setPrice(info.getJg());
					}else{
						info.setPrice(info.getCxjg());
					}
					int count = coonShopProductMapper.getCoonShopProductAddedCountByGoodsId(info.getGoodsId().toString());
					info.setPutSalesNum(count);
					int added = coonShopProductMapper.getCoonShopProductAddedCountByGoodsIdAndShopId(info.getGoodsId().toString(), sId);
					if(added > 0){
						info.setAdded(true);
					}else{
						info.setAdded(false);
					}
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
     * 分页	获取未上架商品列表
     * 
     * @param  coonShopProductParam 商品查询条件
     * @return  已上架商品列表
     */
	@Override
	public Record getShopProductNoList(CoonShopProductParam coonShopProductParam) {
		Record record = new Record(); 
		boolean success = false;
		String sId = coonShopProductParam.getsId();
		if(StringUtils.isNotEmpty(sId)){
			List<CoonShopProductInfo> list = coonShopProductMapper.getCoonShopProductNoAddedListPage(coonShopProductParam.getRowBounds(), coonShopProductParam);
			if(CollectionUtils.isNotEmpty(list)){
				List<CoonShopProductInfo> productList = new ArrayList<CoonShopProductInfo>();
				for(CoonShopProductInfo info : list){
					//处理价格、上架酷店数、是否在该店铺上架
					if(new BigDecimal(0).compareTo(info.getCxjg()) == 0){
						info.setPrice(info.getJg());
					}else{
						info.setPrice(info.getCxjg());
					}
					int count = coonShopProductMapper.getCoonShopProductAddedCountByGoodsId(info.getGoodsId().toString());
					info.setPutSalesNum(count);
					int added = coonShopProductMapper.getCoonShopProductAddedCountByGoodsIdAndShopId(info.getGoodsId().toString(), sId);
					if(added > 0){
						info.setAdded(true);
					}else{
						info.setAdded(false);
					}
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


}
