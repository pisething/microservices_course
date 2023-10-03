package com.piseth.bank.gatewayserver;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}
	
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
	    return builder.routes()
	        .route(p -> p
	            .path("/pisethbank/account/**")
	            .filters(f -> f.rewritePath("/pisethbank/account/(?<segment>.*)","/${segment}")
	            		
	            		.addResponseHeader("X-RESPONSE-TIME", LocalDateTime.now().toString()))
	            				
	            .uri("lb://ACCOUNT")).
	        route(p -> p
		            .path("/pisethbank/loan/**")
		            .filters(f -> f.rewritePath("/pisethbank/loan/(?<segment>.*)","/${segment}"))
		            		
		            .uri("lb://LOAN")).
	        route(p -> p
		            .path("/pisethbank/card/**")
		            .filters(f -> f.rewritePath("/pisethbank/card/(?<segment>.*)","/${segment}"))
		            		
		            .uri("lb://CARD")).build();
	}


}
