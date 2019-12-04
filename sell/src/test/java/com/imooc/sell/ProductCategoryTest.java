package com.imooc.sell;

import com.imooc.sell.pojo.ProductCategory;
import com.imooc.sell.repository.ProductCategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName ProductCategoryTest.java
 * @createTime 2019年11月02日 11:57:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void test(){
        ProductCategory repositoryOne = repository.getOne(1);
        System.out.println(repositoryOne);
    }

    @Test
    public void test2(){
        System.out.println(repository.findById(1).get());
    }

    @Test
    public void add(){
        ProductCategory productCategory = repository.findById(2).get();
        productCategory.setCategoryType(1);
        repository.save(productCategory);
    }

    @Test
    public void findByCategoryType(){
        List<Integer> list = Arrays.asList(2,3,4);

        List<ProductCategory> byCategoryTypeIn = repository.findByCategoryTypeIn(list);
        System.out.println(byCategoryTypeIn);
    }
}
