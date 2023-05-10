package com.mambu.back.service;

import com.mambu.back.model.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ClientService {
    Flux<Client> getAllClients (String offset, String limit, String paginationDetails, String detailsLevel);
    Mono<Client> getClientByID (String id, String detailsLevel);
    Mono<Client> createClient(Client client);
}
