package pl.marcindev.webfluxreactivedemo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import pl.marcindev.webfluxreactivedemo.controller.ProductController;
import pl.marcindev.webfluxreactivedemo.dto.ProductDto;
import pl.marcindev.webfluxreactivedemo.service.ProductService;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@SpringBootTest

class WebfluxReactiveDemoApplicationTests {
   @Test
    public void contextLoads(){

   }

}
