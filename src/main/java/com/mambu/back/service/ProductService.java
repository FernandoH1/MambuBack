package com.mambu.back.service;

import com.mambu.back.model.dto.passive.Product;
import com.mambu.back.model.dto.active.ProductoLoanDTO;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<Product> getProductByID (String id, String offset, String limit, String paginationDetails, String detailsLevel);

    Mono<ProductoLoanDTO> getProductLoanByID (String id, String offset, String limit, String paginationDetails, String detailsLevel);
}
