package com.mambu.back.service.Impl;

import com.mambu.back.config.WebClientConfig;
import com.mambu.back.headers.GetHeaders;
import com.mambu.back.model.dto.passive.Product;
import com.mambu.back.model.dto.active.ProductoLoanDTO;
import com.mambu.back.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private WebClientConfig webClientConfig;

    @Autowired
    private GetHeaders headers;


    @Override
    public Mono<Product> getProductByID(String id, String offset, String limit, String paginationDetails, String detailsLevel) {
        return webClientConfig.webClient().get()
                .uri(uriBuilder -> uriBuilder
                        .path("/depositproducts/" + id)
                        .queryParam("offset", offset)
                        .queryParam("limit", limit)
                        .queryParam("paginationDetails", paginationDetails)
                        .queryParam("detailsLevel", detailsLevel)
                        .build())
                .header("Accept", headers.headers().getAccept())
                .retrieve().bodyToMono(Product.class).onErrorResume(e -> {
                    var responseException = (WebClientResponseException) e;
                    if (e instanceof WebClientResponseException) {
                        if (responseException.getStatusCode().is4xxClientError()) {
                            return Mono.error(new Throwable(responseException.getResponseBodyAsString()));
                        }
                    }

                    return Mono.error(new Throwable(responseException.getResponseBodyAsString()));
                });
    }

    @Override
    public Mono<ProductoLoanDTO> getProductLoanByID(String id, String offset, String limit, String paginationDetails, String detailsLevel) {
        return webClientConfig.webClient().get()
                .uri(uriBuilder -> uriBuilder
                        .path("/loanproducts/"+id)
                        .queryParam("offset", offset)
                        .queryParam("limit", limit)
                        .queryParam("paginationDetails", paginationDetails)
                        .queryParam("detailsLevel", detailsLevel)
                        .build())
                .header("Accept", headers.headers().getAccept())
                .retrieve().bodyToMono(ProductoLoanDTO.class).onErrorResume(e -> {
                    var responseException = (WebClientResponseException) e;
                    if (e instanceof WebClientResponseException) {
                        if (responseException.getStatusCode().is4xxClientError()) {
                            return Mono.error(new Throwable(responseException.getResponseBodyAsString()));
                        }
                    }

                    return Mono.error(new Throwable(responseException.getResponseBodyAsString()));
                });
    }
}
