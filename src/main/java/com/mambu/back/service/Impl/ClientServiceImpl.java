package com.mambu.back.service.Impl;

import com.mambu.back.config.WebClientConfig;
import com.mambu.back.headers.GetHeaders;
import com.mambu.back.model.Client;
import com.mambu.back.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private WebClientConfig webClientConfig;

    @Autowired
    private GetHeaders headers;

    @Override
    public Flux<Client> getAllClients(String offset, String limit, String paginationDetails, String detailsLevel) {
        return webClientConfig.webClient().get()
                .uri(uriBuilder -> uriBuilder
                        .path("/clients")
                        .build())
                .header("Content-Type", headers.headers().getContentType())
                .header("IdempotencyKey", headers.headers().getIdempotencyKey())
                .retrieve().bodyToFlux(Client.class).onErrorResume(e -> {
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
    public Mono<Client> getClientByID(String id, String detailsLevel) {
        return webClientConfig.webClient().get()
                .uri(uriBuilder -> uriBuilder
                        .path("/clients/" + id)
                        .queryParam("detailsLevel", detailsLevel)
                        .build())
                .header("Accept", headers.headers().getAccept())
                .header("Content-Type", headers.headers().getContentType())
                .header("IdempotencyKey", headers.headers().getIdempotencyKey())
                .retrieve().bodyToMono(Client.class).onErrorResume(e -> {
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
    public Mono<Client> createClient(Client client) {
        return webClientConfig.webClient().post()
                .uri(uriBuilder -> uriBuilder
                        .path("/clients")
                        .build())
                .body(Mono.just(client), Client.class)
                .header("Accept", headers.headers().getAccept())
                .header("Content-Type", headers.headers().getContentType())
                .header("IdempotencyKey", headers.headers().getIdempotencyKey())
                .retrieve().bodyToMono(Client.class).onErrorResume(e -> {
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
