package com.zappos.canatalio.web.rest;

import com.zappos.canatalio.domain.ProductCombos;
import com.zappos.canatalio.domain.ProductSearchResult;
import com.zappos.canatalio.domain.SearchResults;
import com.zappos.canatalio.security.AuthoritiesConstants;
import com.zappos.canatalio.service.AuditEventService;
import com.zappos.canatalio.service.ProductSearchService;
import com.zappos.canatalio.web.propertyeditors.LocaleDateTimeEditor;
import org.joda.time.LocalDateTime;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * REST controller for getting the audit events.
 */
@RestController
@RequestMapping("/api")
public class ProductSearchResource {

    @Inject
    private ProductSearchService productSearchService;

    static List<String> searchTerms;

    //Initializer
    static
    {
        String[] searchTermsArray = {"boots","shoes","scarf","jeans","shirt","socks","hoodie","jacket"};
        searchTerms = new ArrayList<>(Arrays.asList(searchTermsArray));
    }

    @RequestMapping(value = "/products",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductCombos findAll(
        @RequestParam(value = "searchTerm", defaultValue = "boots", required = true)String searchTerm,
        @RequestParam(value = "totalPrice", defaultValue = "1000", required = true)Double totalPrice,
        @RequestParam(value = "numOfItems", defaultValue = "3", required = true)Integer numOfItems) {

        System.out.println("totalPrice " +totalPrice );
        System.out.println("numOfItems " + numOfItems);

        ProductCombos productCombos = new ProductCombos();
        List<ProductSearchResult> currentProductCombo = new ArrayList<>();

        List<SearchResults> searchResultsList = new ArrayList<SearchResults>();

        //Cache products array with one item type per rows and one row per numOfItems
        for(int i = 0; i < numOfItems; i++) {
            SearchResults searchResults = productSearchService.getProductsBySearchTerm(searchTerms.get(i));

            //Sort from lowest price to highest price using product search results compareTo Method
            Collections.sort(searchResults.getProductSearchResultList());
            searchResultsList.add(searchResults);
        }


        int productListPosition = 0;
        while(totalCostOfCombo(currentProductCombo) <= totalPrice && productListPosition < searchResultsList.size() ) {

            int category = 0;
            currentProductCombo = new ArrayList<>();
            for(SearchResults sr : searchResultsList) {
                currentProductCombo.add(category, sr.getProductSearchResultList().get(productListPosition));
                category++;
            }

            if(totalCostOfCombo(currentProductCombo) <= totalPrice
                && !isDuplicate(currentProductCombo, productCombos)) {
                System.out.println("added " + currentProductCombo);
                productCombos.addProductCombo(currentProductCombo);
            }

            productListPosition++;
        }

        return productCombos;
    }

    private Double totalCostOfCombo(List<ProductSearchResult> combo) {
        return combo.stream().mapToDouble(ProductSearchResult::getPriceAsDouble).sum();
    }

    private boolean isDuplicate(List<ProductSearchResult> psrList, ProductCombos pc) {

        if(pc.getProductCombosList().isEmpty())
            return false;

        for(List<ProductSearchResult> psrAddedList : pc.getProductCombosList()) {

            int psrAddedListPointer = 0;
            for(ProductSearchResult psr : psrList) {
                System.out.println("id1 " + psr.getProductId() + "id2 " + psrAddedList.get(psrAddedListPointer).getProductId());
                if(!psr.getProductId().equals(psrAddedList.get(psrAddedListPointer).getProductId())) {
                    return false;
                }
                psrAddedListPointer++;
            }
        }

        return true;
    }

}
