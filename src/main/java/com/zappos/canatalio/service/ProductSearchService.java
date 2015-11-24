package com.zappos.canatalio.service;

import com.zappos.canatalio.domain.SearchResults;

public interface ProductSearchService {

    public SearchResults getProductsBySearchTerm(String searchTerm);

}
