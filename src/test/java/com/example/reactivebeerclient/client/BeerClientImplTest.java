package com.example.reactivebeerclient.client;

import com.example.reactivebeerclient.config.WebClientConfig;
import com.example.reactivebeerclient.model.BeerDto;
import com.example.reactivebeerclient.model.BeerPagedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

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


    @Disabled("API returning inventory when should not be")
    @Test
    void getBeerById() {
        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(null, null, null, null, null);
        BeerPagedList pagedList = beerPagedListMono.block();
        UUID beerId = pagedList.getContent().get(0).getId();
        Mono<BeerDto> beerDtoMono = beerClient.getBeerById(beerId, false);
        BeerDto beerDto = beerDtoMono.block();

        assertThat(beerDto).isNotNull();
        assertThat(beerDto.getId()).isEqualTo(beerId);
        assertThat(beerDto.getQuantityOnHand()).isNotNull();
    }

    @Test
    void getBeerByIdShowInventoryTrue() {
        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(null, null, null, null, null);
        BeerPagedList pagedList = beerPagedListMono.block();
        UUID beerId = pagedList.getContent().get(0).getId();
        Mono<BeerDto> beerDtoMono = beerClient.getBeerById(beerId, true);
        BeerDto beerDto = beerDtoMono.block();

        assertThat(beerDto).isNotNull();
        assertThat(beerDto.getId()).isEqualTo(beerId);
        assertThat(beerDto.getQuantityOnHand()).isNotNull();
    }

    @Test
    void getBeerByUPC() {
        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(null, null, null, null, null);
        BeerPagedList pagedList = beerPagedListMono.block();
        String upc = pagedList.getContent().get(0).getUpc();
        Mono<BeerDto> beerDtoMono = beerClient.getBeerByUPC(upc);
        BeerDto beerDto = beerDtoMono.block();

        assertThat(beerDto.getUpc()).isEqualTo(upc);
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
}