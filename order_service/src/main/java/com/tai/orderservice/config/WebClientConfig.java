package com.tai.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    //Tao ra 1 web client goi den controller cua service khac
    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }
}
