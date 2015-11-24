package com.zappos.canatalio.service.impl;

import com.zappos.canatalio.service.ProductSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class ProductSearchServiceImpl implements ProductSearchService {

    private final Logger log = LoggerFactory.getLogger(ProductSearchServiceImpl.class);

    @Override
    public String getProductsBySearchTerm(String searchTerm) {
        RestTemplate restTemplate = new RestTemplate();
        String quote = restTemplate.getForObject("http://api.zappos.com/Search?term=boots&key=b05dcd698e5ca2eab4a0cd1eee4117e7db2a10c4", String.class);
        log.info(quote.toString());

        return "test123";
    }
}