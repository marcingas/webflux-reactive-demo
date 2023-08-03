package pl.marcindev.webfluxreactivedemo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pl.marcindev.webfluxreactivedemo.entity.ProductEntity;
@Repository
public interface ProductRepository extends ReactiveMongoRepository<ProductEntity,String> {

}
