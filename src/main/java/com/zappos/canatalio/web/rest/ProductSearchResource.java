package com.zappos.canatalio.web.rest;

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
import java.util.List;

/**
 * REST controller for getting the audit events.
 */
@RestController
@RequestMapping("/api")
public class ProductSearchResource {

    @Inject
    private ProductSearchService productSearchService;


    @RequestMapping(value = "/products",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String findAll() {
        return productSearchService.getProductsBySearchTerm("");
    }

}
