package com.example.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class CamelApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelApplication.class, args);
	}
}

@Configuration
class CamelConfiguration {

	@Bean
	CamelContextConfiguration contextConfiguration() {
		return new CamelContextConfiguration() {
			@Override
			public void beforeApplicationStart(CamelContext context) {
				// Add your routes to the Camel context here
				try {
					context.addRoutes(new RouteBuilder() {
						@Override
						public void configure() throws Exception {
							from("timer:foo?period=5000")
									.setBody().constant("Hello, Camel!")
									.to("log:output");
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void afterApplicationStart(CamelContext context) {
				// Additional configuration after Camel context starts
			}
		};
	}
}
