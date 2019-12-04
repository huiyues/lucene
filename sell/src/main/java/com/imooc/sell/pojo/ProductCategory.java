package com.imooc.sell.pojo;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName ProductCategoryRepository.java
 * @createTime 2019年11月02日 10:55:00
 */
@Entity
@Table(name = "product_category")
@Data
@ToString
@Proxy(lazy = false)
@DynamicUpdate
public class ProductCategory implements Serializable {

    /**类目id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    /**类目名称*/
    private String categoryName;

    /**类目类型*/
    private Integer categoryType;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;
}
