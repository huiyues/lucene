package com.imooc.sell.service;

import com.imooc.sell.dto.CartDTO;
import com.imooc.sell.enumCode.ProductInfoEnum;
import com.imooc.sell.enumCode.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.pojo.ProductInfo;
import com.imooc.sell.repository.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName ProductInfoService.java
 * @createTime 2019年11月02日 16:40:00
 */
@Service
public class ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepo;

    /**
     * 查询所有加分页
     * @param pageable
     * @return
     */
    public Page<ProductInfo> findAll(Integer page,Integer size){
        Pageable pageable = PageRequest.of(page -1 ,size );
        return productInfoRepo.findAll(pageable);
    }

    /**
     * 根据id进行查询
     * @param id
     * @return
     */
    public ProductInfo findById(String id){
        return productInfoRepo.findById(id).get();
    }

    /**
     * 查询上架商品
     * @param status
     * @return
     */
    public List<ProductInfo> findByStatus(){
        return productInfoRepo.findByProductStatus(ProductInfoEnum.UP.getCode());
    }

    /**
     * 新增商品
     * @param productInfo
     */
    public void add(ProductInfo productInfo){
        productInfoRepo.save(productInfo);
    }

    /**
     * 修改
     * @param productInfo
     * @param id
     */
    public ProductInfo update(ProductInfo productInfo){
       return productInfoRepo.save(productInfo);
    }

    /**
     * 减库存
     * @param cartDTOList
     */
    @Transactional(rollbackFor = Exception.class)
    public void decreaseStack(List<CartDTO> cartDTOList){
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = this.findById(cartDTO.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOEXIT);
            }
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0){
                throw new SellException(ResultEnum.PRODUCT_NOT_STACK);
            }
            productInfo.setProductStock(result);
            productInfoRepo.save(productInfo);
        }
    }

    /**
     * 回滚库存
     * @param cartDTOList
     */
    @Transactional(rollbackFor = Exception.class)
    public void increaseStack(List<CartDTO> cartDTOList){
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = this.findById(cartDTO.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOEXIT);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoRepo.save(productInfo);
        }
    }
}
