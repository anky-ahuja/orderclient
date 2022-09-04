package com.anky.xoriant.orderclient.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@SpringBootConfiguration
public class OrderClientConfig {

    @Value("${baseurl.order}")
    private String orderServerUrl;

    @Bean
    public WebClient orderServerWebClient() {
        return WebClient.builder()
                .baseUrl(orderServerUrl)
                .clientConnector(new ReactorClientHttpConnector())
                .build();
    }
}
