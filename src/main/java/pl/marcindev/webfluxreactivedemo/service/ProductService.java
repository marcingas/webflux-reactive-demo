package pl.marcindev.webfluxreactivedemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import pl.marcindev.webfluxreactivedemo.dto.ProductDto;
import pl.marcindev.webfluxreactivedemo.entity.ProductEntity;
import pl.marcindev.webfluxreactivedemo.repository.ProductRepository;
import pl.marcindev.webfluxreactivedemo.utils.ApplicationUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.font.NumericShaper;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Flux<ProductDto> getProducts() {
        return productRepository.findAll().map(ApplicationUtils::entityToDto);
    }

    public Mono<ProductDto> getProduct(String id) {
        return productRepository.findById(id).map(ApplicationUtils::entityToDto);
    }

    public Flux<ProductDto> getProductInPriceRange(double min, double max) {
        return productRepository.findByPriceBetween(Range.closed(min, max));
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono) {
        return productDtoMono.map(ApplicationUtils::dtoTOEntity)
                .flatMap(productRepository::insert)
                .map(ApplicationUtils::entityToDto);
    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id) {
        return productRepository.findById(id)
                .flatMap(p -> productDtoMono.map(ApplicationUtils::dtoTOEntity)
                        .doOnNext(entity -> entity.setId(id))
                        .flatMap(productRepository::save))
                .map(ApplicationUtils::entityToDto);
    }
    public Mono<Void>deleteProduct(String id){
        return productRepository.deleteById(id);
    }

}
