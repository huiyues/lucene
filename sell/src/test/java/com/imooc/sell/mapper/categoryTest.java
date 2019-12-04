
package com.imooc.sell.mapper;

import com.imooc.sell.pojo.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import sun.awt.windows.WEmbeddedFrame;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
public class categoryTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void test(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(5);
        productCategory.setCategoryName("优惠榜");
        productCategory.setCreateTime(new Date());
        productCategory.setUpdateTime(new Date());
        categoryMapper.insertCategory(productCategory);
    }
}
