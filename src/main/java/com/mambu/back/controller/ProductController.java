package com.mambu.back.controller;

import com.mambu.back.model.dto.passive.Product;
import com.mambu.back.model.dto.active.ProductoLoanDTO;
import com.mambu.back.service.Impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    @GetMapping(value = "/search/{id}")
    private Mono<Product> searchProductByID(@PathVariable("id") String id,
                                            @RequestParam String offset,
                                            @RequestParam String limit,
                                            @RequestParam String paginationDetails,
                                            @RequestParam String detailsLevel) {
        return this.productService.getProductByID(id, offset, limit, paginationDetails, detailsLevel);
    }

    @GetMapping(value = "/searchLoan/{id}")
    private Mono<ProductoLoanDTO> searchLoanProductByID(@PathVariable("id") String id,
                                                        @RequestParam String offset,
                                                        @RequestParam String limit,
                                                        @RequestParam String paginationDetails,
                                                        @RequestParam String detailsLevel) {
        return this.productService.getProductLoanByID(id, offset, limit, paginationDetails, detailsLevel);
    }
}
