package com.iancinti.challengeMeli.adapter.out;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import com.iancinti.challengeMeli.coupon.adapter.out.webflux.GetItemsByIdsRestAdapter;
import com.iancinti.challengeMeli.coupon.adapter.out.webflux.model.ItemRestResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class GetItemsByIdsRestAdapterTest {

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec uriSpecMock;

    @Mock
    private WebClient.RequestHeadersSpec headersSpecMock;

    @Mock
    private WebClient.ResponseSpec responseSpecMock;

    @InjectMocks
    private GetItemsByIdsRestAdapter adapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecute() {
        String itemId = "ABC123";
        ItemRestResponse expectedResponse = new ItemRestResponse(
                String.valueOf(Collections.singletonList("item1")), 50.0);
        Mono<ItemRestResponse> responseMono = Mono.just(expectedResponse);

        when(webClient.get()).thenReturn(uriSpecMock);
        when(uriSpecMock.uri("/items/" + itemId)).thenReturn(headersSpecMock);
        when(headersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(ItemRestResponse.class)).thenReturn(responseMono);

        Mono<ItemRestResponse> actualResponseMono = adapter.execute(itemId);

        assertEquals(expectedResponse, actualResponseMono.block());
        verify(webClient, times(1)).get();
        verify(uriSpecMock, times(1)).uri("/items/" + itemId);
        verify(headersSpecMock, times(1)).retrieve();
        verify(responseSpecMock, times(1)).bodyToMono(ItemRestResponse.class);
    }

}
