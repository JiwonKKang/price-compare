package com.example.pricecompareredis.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class LowestPriceServiceImpl implements LowestPriceService {

    public Set getZsetValue(String key) {
        Set myTempSet = new HashSet();
        return myTempSet;
    }

}
