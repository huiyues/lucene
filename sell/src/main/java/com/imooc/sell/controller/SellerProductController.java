package com.imooc.sell.controller;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enumCode.ProductInfoEnum;
import com.imooc.sell.enumCode.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.pojo.ProductCategory;
import com.imooc.sell.pojo.ProductInfo;
import com.imooc.sell.service.ProductCategoryService;
import com.imooc.sell.service.ProductInfoService;
import com.imooc.sell.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName SellerProductController.java
 * @createTime 2019年11月17日 16:05:00
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有商品
     *
     * @return
     */
    @GetMapping("/list")
    public ModelAndView findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "size", defaultValue = "10") Integer size,
                                Map<String, Object> map) {
        Page<ProductInfo> productInfoPage = productInfoService.findAll(page, size);
        List<ProductCategory> categoryList = productCategoryService.findAll();
        map.put("productPageList", productInfoPage);
        map.put("currentPage", page);
        map.put("categoryList", categoryList);
        return new ModelAndView("product/list", map);
    }

    /**
     * 修改上架
     *
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/racking")
    public ModelAndView racking(@RequestParam("productId") String productId, Map<String, Object> map) {
        try {
            ProductInfo productInfo = productInfoService.findById(productId);
            productInfo.setProductStatus(0);
            productInfoService.update(productInfo);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", "商品已上架");
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * 修改下架
     *
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/outStack")
    public ModelAndView outStack(@RequestParam("productId") String productId, Map<String, Object> map) {
        try {
            ProductInfo productInfo = productInfoService.findById(productId);
            productInfo.setProductStatus(1);
            productInfoService.update(productInfo);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", "商品已下架");
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * 查询商品详情
     *
     * @return
     */
    @GetMapping("/detail")
    public ModelAndView findAll(@RequestParam("productId") String productId, Map<String, Object> map) {
        ProductInfo productInfo = null;
        List<ProductCategory> categoryList = null;
        try {
            productInfo = productInfoService.findById(productId);
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOEXIT);
            }
            categoryList = productCategoryService.findAll();
        } catch (Exception e) {

            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("product/update", map);
        }
        map.put("productInfo", productInfo);
        map.put("categoryList", categoryList);
        return new ModelAndView("product/update", map);
    }

    /**
     * 商品修改
     *
     * @return
     */
    @PostMapping("/update")
    public ModelAndView update(ProductInfo productInfo) {
        Map map = new HashMap();
        try {
            ProductInfo productInfo1 = productInfoService.update(productInfo);
            if (productInfo1 == null) {
                throw new SellException(ResultEnum.PRODUCT_UPDATE_ERROR);
            }
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/detail?productId=" + productInfo.getProductId());
            return new ModelAndView("common/error", map);
        }
        map.put("msg", "修改成功");
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * 商品新增
     *
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(ProductInfo productInfo) {
        Map map = new HashMap();
        try {
            productInfo.setProductId(idWorker.nextId()+"");
            productInfo.setProductStatus(0);
            ProductInfo productInfo1 = productInfoService.update(productInfo);
            if (productInfo1 == null) {
                throw new SellException(ResultEnum.PRODUCT_UPDATE_ERROR);
            }
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/detail?productId=" + productInfo.getProductId());
            return new ModelAndView("common/error", map);
        }
        map.put("msg", "新增成功");
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * 新增跳转页
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(Map map){
       List<ProductCategory> categoryList = productCategoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("product/save", map);
    }
}
