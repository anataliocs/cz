package com.zappos.canatalio.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by canatalio on 11/23/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductCombos {

    List<List<ProductSearchResult>> productCombosList;
    List<Double> totalPriceList;

    public ProductCombos() {
        productCombosList = new ArrayList<>();
        totalPriceList = new ArrayList<>();
    }

    public List<List<ProductSearchResult>> getProductCombosList() {
        return productCombosList;
    }

    public void setProductCombosList(List<List<ProductSearchResult>> productCombosList) {
        this.productCombosList = productCombosList;
    }

    public List<Double> getTotalPriceList() {
        return totalPriceList;
    }

    public void setTotalPriceList(List<Double> totalPriceList) {
        this.totalPriceList = totalPriceList;
    }

    public void addProductCombo(List<ProductSearchResult> combo) {
        productCombosList.add(combo);
        totalPriceList.add(combo.stream().mapToDouble(ProductSearchResult::getPriceAsDouble).sum());
    }

}
