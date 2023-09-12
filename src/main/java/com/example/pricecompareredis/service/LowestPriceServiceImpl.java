package com.example.pricecompareredis.service;

import com.example.pricecompareredis.vo.Product;
import com.example.pricecompareredis.vo.ProductGrp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LowestPriceServiceImpl implements LowestPriceService {

    private final RedisTemplate<String, Object> redisTemplate;


    public Set getZsetValue(String key) {
        Set myTempSet = new HashSet();
        myTempSet = redisTemplate.opsForZSet().rangeWithScores(key, 0, 9);
        return myTempSet;
    }

    public int setNewProduct(Product newProduct) {
        redisTemplate.opsForZSet().add(newProduct.getProductGrpId(), newProduct.getProductId(), newProduct.getPrice());
        return redisTemplate.opsForZSet().rank(newProduct.getProductGrpId(), newProduct.getProductId()).intValue();
    }

    public int setNewProductGroup(ProductGrp newProductGrp) {
        List<Product> productList = newProductGrp.getProductList();
        Product product = productList.get(0);

        redisTemplate.opsForZSet().add(newProductGrp.getProductGrpId(), product.getProductId(), product.getPrice());
        return redisTemplate.opsForZSet().zCard(newProductGrp.getProductGrpId()).intValue();
    }

    public int setNewProductGrpToKeyword(String keyword, String prodGrpId, double score) {
        redisTemplate.opsForZSet().add(keyword, prodGrpId, score);
        int rank = redisTemplate.opsForZSet().rank(keyword, prodGrpId).intValue();
        return rank;
    }
}
