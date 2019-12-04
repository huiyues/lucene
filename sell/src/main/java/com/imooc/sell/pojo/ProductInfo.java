package com.imooc.sell.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imooc.sell.enumCode.ProductInfoEnum;
import com.imooc.sell.util.EnumUtils;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName ProductInfo.java
 * @createTime 2019年11月02日 16:23:00
 */
@Entity
@Table(name = "product_info")
@Data
@DynamicUpdate
public class ProductInfo {

    @Id
    private String productId;

    /**名字 */
    private String productName;

    /**单价*/
    private BigDecimal productPrice;

    /**库存*/
    private Integer productStock;

    /**描述*/
    private String productDescription;

    /**小图*/
    private String productIcon;

    /**状态,0 正常 1 下架*/
    private Integer productStatus;

    /**类目编号*/
    private Integer categoryType;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

    @JsonIgnore
    public ProductInfoEnum getProductInfoEnum(){
        return EnumUtils.getCode(productStatus, ProductInfoEnum.class);
    }
}
