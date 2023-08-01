package pl.marcindev.webfluxreactivedemo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MOnoFLuxTest {
    @Test
public void testMono(){
    Mono<?> monoString = Mono.just("marcindev")
            .then(Mono.error(new RuntimeException("some message ")))
            .log();
    monoString.subscribe(System.out::print,e-> System.out.println(e.getMessage()));

}
@Test
public void testFlux(){
    Flux<?> fluxString = Flux.just("Spring", "Summer", "Autumn", "Winter")
            .concatWithValues(" and ")
            .concatWith(Flux.error(new RuntimeException("exception in flux")))
            .concatWithValues(" cloud")
            .log();
    fluxString.subscribe(x-> System.out.println(x),e-> System.out.println(e.getMessage() ));


}
}
