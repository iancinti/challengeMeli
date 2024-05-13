package com.iancinti.challengeMeli.coupon.adapter.out.webflux;

import com.iancinti.challengeMeli.coupon.adapter.out.webflux.model.ItemRestResponse;
import com.iancinti.challengeMeli.coupon.application.port.out.GetItemByIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Component
public class GetItemsByIdsRestAdapter implements GetItemByIdRepository {

    private WebClient webClient;

    @Autowired
    public GetItemsByIdsRestAdapter(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<ItemRestResponse> execute(String itemId) {
        return webClient.get()
                .uri("/items/" + itemId)
                .retrieve().bodyToMono(ItemRestResponse.class);
    }
}



