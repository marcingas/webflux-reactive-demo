package pl.marcindev.webfluxreactivedemo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import pl.marcindev.webfluxreactivedemo.dto.ProductDto;
import pl.marcindev.webfluxreactivedemo.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@WebFluxTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private ProductService service;

    @Test
    public void addProductTest() {
        Mono<ProductDto> productDtoMono = Mono.just(new ProductDto("102", "mobile", 1, 1000));
        when(service.saveProduct(productDtoMono)).thenReturn(productDtoMono);

        webTestClient.post().uri("/products")
                .body(Mono.just(productDtoMono), ProductDto.class)
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    public void getProductsTest(){
        Flux<ProductDto> productDtoFlux = Flux.just(new ProductDto("102","mobile",1,1000),
                new ProductDto("103","TV",1,500));
        when(service.getProducts()).thenReturn(productDtoFlux);

        Flux<ProductDto> responseBody = webTestClient.get().uri("/products")
                .exchange()
                .expectStatus().isOk()
                .returnResult(ProductDto.class)
                .getResponseBody();
        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(new ProductDto("102","mobile",1,1000))
                .expectNext(new ProductDto("103","TV",1,500))
                .verifyComplete();
    }


}