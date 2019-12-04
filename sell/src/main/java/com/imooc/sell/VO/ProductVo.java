package com.imooc.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imooc.sell.pojo.ProductInfo;
import lombok.Data;

import java.util.List;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName ProductVo.java
 * @createTime 2019年11月02日 17:43:00
 */
@Data
public class ProductVo {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVoList;
}
