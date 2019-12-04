package com.imooc.sell.controller;

import com.imooc.sell.VO.ResultVo;
import com.imooc.sell.enumCode.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.mapper.CategoryMapper;
import com.imooc.sell.pojo.ProductCategory;
import com.imooc.sell.service.ProductCategoryService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName SellerCategoryController.java
 * @createTime 2019年11月18日 20:37:00
 */
@Controller
@RequestMapping("/seller/category")
@MapperScan("com.imooc.sell.mapper")
public class SellerCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 查询所有类目
     *
     * @return
     */
    @GetMapping("/list")
    public ModelAndView findAll(Map map) {
        List<ProductCategory> categoryList = productCategoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("category/list", map);
    }

    /**
     * 查询类目详情
     *
     * @return
     */
    @GetMapping("/detail")
    public ModelAndView findById(@RequestParam(value = "categoryId",required = false) Integer categoryId, Map map) {
        ProductCategory category = null;
        try {
            if (categoryId == null || "".equals(categoryId)){
                return new ModelAndView("category/update", map);
            }
            category = productCategoryService.findById(categoryId);
            if (category == null){
                throw new SellException(ResultEnum.PRODUCT_NOEXIT);
            }
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/category/list");
            return new ModelAndView("common/error", map);
        }
        map.put("category", category);
        return new ModelAndView("category/update", map);
    }

    /**
     * 类目修改
     *
     * @return
     */
    @PostMapping("/update")
    public ModelAndView update(ProductCategory productCategory) {
        Map map = new HashMap();
        ProductCategory category = null;
        try {
            if (productCategory.getCategoryId() == null || "".equals(productCategory.getCategoryId())){
                category = productCategoryService.save(productCategory);
                map.put("msg", "新增成功");
                map.put("url", "/sell/seller/category/list");
                return new ModelAndView("common/success", map);
            }else {
                category = productCategoryService.save(productCategory);
                if (category == null){
                    throw new SellException(ResultEnum.CATEGORY_UPDATE_ERROR);
                }
            }
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/category/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", "修改成功");
        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success", map);
    }
}
