package com.mambu.back.headers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetHeaders {

    @Bean
    public Headers headers(){
        return new Headers();
    }

}
