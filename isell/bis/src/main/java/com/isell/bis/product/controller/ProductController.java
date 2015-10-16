package com.isell.bis.product.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isell.service.product.service.ProductService;

/**
 * 商品controller
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-09]
 */
@Controller
@RequestMapping("product")
public class ProductController {
    
    /**
     * 商品service
     */
    @Resource
    private ProductService productService;
    
}
