package com.example.pricecompareredis.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Product {

    private String productGrpId;
    private String productId;
    private int price;


}
