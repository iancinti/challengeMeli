package com.iancinti.challengeMeli.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

@Configuration
public class WebConfig {

    @Bean
    public WebClient webClient() {

        ConnectionProvider connectionProvider = ConnectionProvider.builder("custom")
                .maxConnections(100_000)
                .pendingAcquireMaxCount(100_000)
                .pendingAcquireTimeout(Duration.ofMillis(5000))
                .build();

        HttpClient httpClient = HttpClient.create(connectionProvider);

        ReactorClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        return WebClient.builder()
                .clientConnector(connector)
                .baseUrl("")
                .build();
    }
}
