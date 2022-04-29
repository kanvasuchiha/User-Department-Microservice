package com.kanvas.cloud.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class GatewayConfig {

//    Alternate way of configuring the api gateway routes
//    First one is via application.yml
//
//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
//        return routeLocatorBuilder.routes()
//        .route(p -> p
//                .path("/users/**")
//                .uri("lb://USER-SERVICE"))
//        .route(p -> p
//                .path("/departments/**")
//                .uri("lb://DEPARTMENT-SERVICE"))
//        .build();
//    }

}
