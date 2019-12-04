package com.imooc.sell.controller;

import com.imooc.sell.VO.ProductInfoVo;
import com.imooc.sell.VO.ProductVo;
import com.imooc.sell.VO.ResultVo;
import com.imooc.sell.pojo.ProductCategory;
import com.imooc.sell.pojo.ProductInfo;
import com.imooc.sell.repository.ProductCategoryRepository;
import com.imooc.sell.repository.ProductInfoRepository;
import com.imooc.sell.service.ProductCategoryService;
import com.imooc.sell.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName BuyerProductController.java
 * @createTime 2019年11月02日 17:49:00
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVo findTest() {
        //查询所有上架的商品
        List<ProductInfo> productInfoList = productInfoService.findByStatus();

        //让商品与自己的类目对应
        List<Integer> createGoryTypeList =
                productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());

        //查询对应的商品类目
        List<ProductCategory> productCategories = productCategoryService.findByCategoryType(createGoryTypeList);

        //封装list类目数据
        List<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory productCategory : productCategories) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());

            //封装商品数据
            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productCategory.getCategoryType().equals(productInfo.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    //使用工具类进行数据copy
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }

        //返回数据
        return new ResultVo(0, "查询成功", productVoList);
    }
}
