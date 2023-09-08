package com.example.pricecompareredis.service;

import com.example.pricecompareredis.vo.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
}
