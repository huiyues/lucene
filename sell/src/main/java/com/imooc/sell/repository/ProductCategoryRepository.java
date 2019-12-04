package com.imooc.sell.repository;

import com.imooc.sell.pojo.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName ProductCategoryRepository.java
 * @createTime 2019年11月02日 11:55:00
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer>, JpaSpecificationExecutor<ProductCategory> {

    /**
     * 根据商品类型查询对应的分类
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
