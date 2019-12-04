package com.imooc.sell.mapper;

import com.imooc.sell.pojo.ProductCategory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

public interface CategoryMapper {

    /**
     * 添加类目
     */
    @Insert("insert into product_category(category_name,category_type,create_time,update_time) " +
            "values(#{categoryName},#{categoryType},#{createTime},#{updateTime})")
    public void  insertCategory(ProductCategory productCategory);
}
