package com.mambu.back.controller;

import com.mambu.back.model.Client;
import com.mambu.back.service.Impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientServiceImpl clientService;


    @GetMapping(value = "/all")
    private Flux<Client> allClients(@RequestParam String offset,
                                    @RequestParam String limit,
                                    @RequestParam String paginationDetails,
                                    @RequestParam String detailsLevel) {
        return this.clientService.getAllClients(offset, limit, paginationDetails, detailsLevel);
    }

    @GetMapping(value = "/search/{id}")
    private Mono<Client> searchRecursoByID(@PathVariable("id") String id, @RequestParam String detailsLevel) {
        return this.clientService.getClientByID(id, detailsLevel);
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<Client> saveClient(@RequestBody Client client) {
        return this.clientService.createClient(client);
    }
}
