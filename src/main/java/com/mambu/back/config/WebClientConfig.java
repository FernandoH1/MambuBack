package com.mambu.back.config;

import com.mambu.back.headers.GetHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Autowired
    GetHeaders getHeader;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(getHeader.headers().getBasicUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeaders(header -> header.setBasicAuth(getHeader.headers().getUser(), getHeader.headers().getPassword()))
                .build();
    }

}
