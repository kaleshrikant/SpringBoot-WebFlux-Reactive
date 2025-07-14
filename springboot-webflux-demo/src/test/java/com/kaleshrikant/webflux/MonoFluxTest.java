package com.kaleshrikant.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono() {
        Mono<?> stringMono = Mono.just("JavaTechie")
                .then(Mono.error(new RuntimeException("Error Occurred in Mono")))
                .log();
        stringMono.subscribe(System.out::println, e -> System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux() {
        Flux<String> stringFlux = Flux.just("Java", "Spring", "SpringBoot");
        stringFlux
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("Error Occurred in Flux")))
                .log()
                .subscribe(System.out::println);
    }
}
