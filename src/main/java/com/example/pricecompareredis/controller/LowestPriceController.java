package com.example.pricecompareredis.controller;

import com.example.pricecompareredis.service.LowestPriceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class LowestPriceController {

    private final LowestPriceServiceImpl lowestPriceService;

    @GetMapping("/getZSETValue")
    public Set getZsetValue(String key) {
        return lowestPriceService.getZsetValue(key);
    }
}
