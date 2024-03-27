package com.example.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyCamelRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:foo?period=5000")
                .setBody().constant("Hello, Camel!")
                .to("log:output");

    }
}
