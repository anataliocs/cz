package com.zappos.canatalio.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by canatalio on 11/23/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResults {

    String originalTerm;
    String currentResultCount;
    String term;
    String statusCode;
    @JsonProperty("results")
    List<ProductSearchResult> productSearchResultList;

    public String getOriginalTerm() {
        return originalTerm;
    }

    public void setOriginalTerm(String originalTerm) {
        this.originalTerm = originalTerm;
    }

    public String getCurrentResultCount() {
        return currentResultCount;
    }

    public void setCurrentResultCount(String currentResultCount) {
        this.currentResultCount = currentResultCount;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public List<ProductSearchResult> getProductSearchResultList() {
        return productSearchResultList;
    }

    public void setProductSearchResultList(List<ProductSearchResult> productSearchResultList) {
        this.productSearchResultList = productSearchResultList;
    }
}
