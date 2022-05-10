package com.kanvas.cloud.gateway.config;

//import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
//import io.github.resilience4j.timelimiter.TimeLimiterConfig;
//import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
//import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;


import java.time.Duration;

@Configuration
public class GatewayConfig {

//    Alternate way of configuring the api gateway routes
//    First one is via application.yml

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
        .route(p -> p
                .path("/users/**")
                    .filters(f -> f.hystrix(h -> h.setName("Hystrix")
                        .setFallbackUri("forward:/userServiceFallBack")))
                .uri("lb://USER-SERVICE")
                .id("userModule"))
        .route(p -> p
                .path("/departments/**")
                    .filters(f -> f.hystrix(h -> h.setName("Hystrix")
                        .setFallbackUri("forward:/departmentServiceFallBack")))
                .uri("lb://DEPARTMENT-SERVICE")
                .id("departmentModule"))
        .build();
    }

//    @Bean
//    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer()
//    {
//        return factory->factory.configureDefault(id ->new Resilience4JConfigBuilder(id)
//                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
//                .timeLimiterConfig(TimeLimiterConfig.custom()
//                        .timeoutDuration(Duration.ofSeconds(2)).build()).build());
//    }

//    @Bean
//    public RedisRateLimiter redisRateLimiter()
//    {
//        return new RedisRateLimiter(1,2);
//    }
//
//    @Bean
//    KeyResolver userKeyResolver()
//    {
//        return exchange -> Mono.just("1");
//    }


}
