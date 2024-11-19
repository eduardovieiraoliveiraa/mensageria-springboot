package com.eduardooliveiradev.mscloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class MscloudgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscloudgatewayApplication.class, args);
	}
	
	@Bean
	 RouteLocator myRoutes(RouteLocatorBuilder builder) {
	    return builder.routes()
	    		.route(r ->r.path("/clientes/**").uri("lb://msclientes"))
	    		.route(r ->r.path("/cartoes/**").uri("lb://mscartoes"))
	    		.route(r ->r.path("/clientecartao/**").uri("lb://mscartoes"))
	    		.route(r ->r.path("/avaliacoescredito/**").uri("lb://msavaliadorcredito"))
	    		.build();
	}

}
