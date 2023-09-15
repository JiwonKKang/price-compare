package com.example.pricecompareredis.service;

import com.example.pricecompareredis.vo.Keyword;
import com.example.pricecompareredis.vo.Product;
import com.example.pricecompareredis.vo.ProductGrp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class LowestPriceServiceImpl implements LowestPriceService {

    private final RedisTemplate<String, Object> redisTemplate;


    public Set getZsetValue(String key) {
        Set<ZSetOperations.TypedTuple<Object>> myTempSet = new HashSet<>();
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

    public Keyword getLowestPriceProductByKeyword(String keyword) {
        List<String> productGrpIdList = Objects.requireNonNull(redisTemplate.opsForZSet().reverseRange(keyword, 0, 9)).stream()
                .map(Object::toString).toList();

        List<ProductGrp> productGrpList = productGrpIdList.stream()
                .map(productGrpId -> {
                            List<Product> productList = Objects.requireNonNull(redisTemplate.opsForZSet().rangeWithScores(productGrpId, 0, 9)).stream()
                                    .map(
                                    tuple -> Product.of(
                                            productGrpId,
                                            Objects.requireNonNull(tuple.getValue()).toString(),
                                            Objects.requireNonNull(tuple.getScore()).intValue()
                                    )
                            ).toList();
                            return ProductGrp.of(productGrpId, productList);
                        }
                ).toList();

        return Keyword.of(keyword, productGrpList);
    }
}
