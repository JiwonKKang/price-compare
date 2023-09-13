package com.example.pricecompareredis.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Keyword {

    private String keyword;

    private List<ProductGrp> productGrpList;

}
