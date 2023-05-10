package com.mambu.back.headers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

@Data
public class Headers {

    @Value("${user}")
    private String user;

    @Value("${password}")
    private String password;

    @Value("${baseUrl}")
    private String basicUrl;

    @Value("${Accept}")
    private String accept;

    @Value("${Content-Type}")
    private String contentType;

    private String idempotencyKey = UUID.randomUUID().toString().substring(0, 36);
}
