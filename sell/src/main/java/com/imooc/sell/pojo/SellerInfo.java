package com.imooc.sell.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName SellerInfo.java
 * @createTime 2019年11月19日 20:51:00
 */
@Entity
@Table(name = "seller_info")
@Data
@DynamicUpdate
public class SellerInfo implements Serializable {

    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openId;

    private String icon;

    private Date createTime;

    private Date updateTime;
}
