package com.piseth.bank.gatewayserver.filter;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class ResponseTracingFilter {
	private final FilterUtility filterUtility;
	
	@Bean
	public GlobalFilter postFilter() {
		
		return ( exchange,  chain) ->{
			
			return chain.filter(exchange).then(Mono.fromRunnable(()->{
				HttpHeaders headers = exchange.getRequest().getHeaders();
				String correlationId = filterUtility.getCorrelationId(headers);
				log.debug("Updated the corelation id to the outbound header: {}", 
						correlationId);
				exchange.getResponse().getHeaders().add(FilterUtility.CORRELATION_ID, correlationId);
			}));
		};
		
		
	}

}
