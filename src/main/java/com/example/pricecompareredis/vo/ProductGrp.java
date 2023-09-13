package com.example.pricecompareredis.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ProductGrp {

    private String productGrpId;

    private List<Product> productList;

}

