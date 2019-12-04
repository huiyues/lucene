package com.imooc.sell.service;

import com.imooc.sell.pojo.ProductCategory;
import com.imooc.sell.pojo.ProductInfo;
import com.imooc.sell.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName ProductCategoryService.java
 * @createTime 2019年11月02日 16:14:00
 */
@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryPro;

    /**
     * 查询所有
     * @return
     */
    public List<ProductCategory> findAll(){
        return productCategoryPro.findAll();
    }

    /**
     * 根据id进行查询
     * @return
     */
    public ProductCategory findById(int id){
        return productCategoryPro.findById(id).get();
    }

    /**
     * 添加方法
     */
    public ProductCategory save(ProductCategory productCategory){
        ProductCategory save = productCategoryPro.save(productCategory);
        return save;
    }

    /**
     * 更新方法
     * @param productCategory
     */
    public void update(ProductCategory productCategory,int id){
        productCategory.setCategoryId(id);
        productCategoryPro.save(productCategory);
    }

    /**
     * 删除
     */
    public void delete(int id){
        productCategoryPro.deleteById(id);
    }

    /**
     * 根据对应的类型查询商品类目
     * @return
     */
    public List<ProductCategory> findByCategoryType(List<Integer> integerList){
        return productCategoryPro.findByCategoryTypeIn(integerList);
    }
}
