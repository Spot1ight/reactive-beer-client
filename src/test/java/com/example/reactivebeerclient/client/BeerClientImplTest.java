package com.example.reactivebeerclient.client;

import com.example.reactivebeerclient.config.WebClientConfig;
import com.example.reactivebeerclient.model.BeerDto;
import com.example.reactivebeerclient.model.BeerPagedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BeerClientImplTest {

    BeerClientImpl beerClient;

    @BeforeEach
    void setUp() {
        beerClient = new BeerClientImpl(new WebClientConfig().webClient());
    }

    @Test
    void listBeers() {
        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(null, null, null, null, null);
        BeerPagedList pagedList = beerPagedListMono.block();
        assertThat(pagedList).isNotNull();
        assertThat(pagedList.getContent().size()).isGreaterThan(0);
        System.out.println(pagedList.toList());
    }

    @Test
    void listBeersPageSized10() {
        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(1, 10, null, null, null);
        BeerPagedList pagedList = beerPagedListMono.block();
        assertThat(pagedList).isNotNull();
        assertThat(pagedList.getContent().size()).isEqualTo(10);
        System.out.println(pagedList.toList());
    }

    @Test
    void listBeersNoRecords() {
        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(10, 20, null, null, null);
        BeerPagedList pagedList = beerPagedListMono.block();
        assertThat(pagedList).isNotNull();
        assertThat(pagedList.getContent().size()).isEqualTo(0);
        System.out.println(pagedList.toList());
    }


    @Test
    void getBeerById() {
        String id = "c7e91579-1c17-4a3e-96b3-f2a88e5f36c3";
        Mono<BeerDto> beerDtoMono = beerClient.getBeerById(UUID.fromString(id), true);
        BeerDto beerDto = beerDtoMono.block();
        assertThat(beerDto).isNotNull();
        assertThat(beerDto.getBeerName()).isEqualTo("Blessed");
    }

    @Test
    void getBeerByIdNotFound() {
        Mono<BeerDto> beerDtoMono = beerClient.getBeerById(UUID.randomUUID(), true);
        BeerDto beerDto = beerDtoMono.onErrorReturn(new BeerDto()).block();
    }

    @Test
    void createBeer() {
    }

    @Test
    void updateBeer() {
    }

    @Test
    void deleteBeer() {
    }

    @Test
    void getBeerByUPC() {
    }
}