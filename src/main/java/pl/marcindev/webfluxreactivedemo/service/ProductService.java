package pl.marcindev.webfluxreactivedemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.marcindev.webfluxreactivedemo.dto.ProductDto;
import pl.marcindev.webfluxreactivedemo.entity.ProductEntity;
import pl.marcindev.webfluxreactivedemo.repository.ProductRepository;
import pl.marcindev.webfluxreactivedemo.utils.ApplicationUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Flux<ProductDto> getProducts() {
        return productRepository.findAll().map(ApplicationUtils::entityToDto);
    }

    public Mono<ProductDto>getProduct(String id){
        return productRepository.findById(id).map(ApplicationUtils::entityToDto);
    }

}
