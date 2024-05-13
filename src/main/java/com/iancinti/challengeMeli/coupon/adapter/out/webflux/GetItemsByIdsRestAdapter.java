package com.iancinti.challengeMeli.coupon.adapter.out.webflux;

import com.iancinti.challengeMeli.coupon.adapter.out.webflux.model.ItemRestResponse;
import com.iancinti.challengeMeli.coupon.application.port.out.GetItemByIdRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GetItemsByIdsRestAdapter implements GetItemByIdRepository {

    private final WebClient webClient;

    private static final Logger logger = LoggerFactory.getLogger(GetItemsByIdsRestAdapter.class);

    @Autowired
    public GetItemsByIdsRestAdapter(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<ItemRestResponse> execute(String itemId) {
        logger.info("Ejecutando GetItemsByIdsRestAdapter");

        return webClient.get()
                .uri("/items/" + itemId)
                .retrieve()
                .bodyToMono(ItemRestResponse.class)
                .doOnSuccess(item -> logger.info("Obtenido el ítem desde GetItemsByIdsRestAdapter"));
    }
}



