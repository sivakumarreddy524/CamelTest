package com.example.demo;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;
import java.util.Random;

@Component
class RestApi extends RouteBuilder {


    RouteProcessor routeProcessor;
    DataSaveProcessor dataSaveProcessor;

    public RestApi(RouteProcessor routeProcessor, DataSaveProcessor dataSaveProcessor) {
        this.routeProcessor = routeProcessor;
        this.dataSaveProcessor = dataSaveProcessor;
    }


    Random r = new Random();

    @Value("${server.port}")
    String serverPort;

    @Autowired
    ApplicationProperties applicationProperties;


    @Override
    public void configure() {

        CamelContext context = new DefaultCamelContext();

        restConfiguration().contextPath(applicationProperties.getApiPath()) //
            .port(serverPort)
            .enableCORS(true)
            .apiContextPath("/api-doc")
            .apiProperty("api.title", "Test REST API")
            .apiProperty("api.version", "v1")
            .apiProperty("cors", "true")
            .apiContextRouteId("doc-api")
            .component("servlet")
            .bindingMode(RestBindingMode.json)
            .dataFormatProperty("prettyPrint", "true");


        rest("/api/").description("Teste REST Service")
            .id("api-route")
            .post("/bean")
            .produces(MediaType.APPLICATION_JSON)
            .consumes(MediaType.APPLICATION_JSON)
            .bindingMode(RestBindingMode.auto)
            .type(MyBean.class)
            .enableCORS(true)
            .to("direct:remoteService2");


        from("direct:remoteService2")
            .routeId("direct-route2")
            .process(routeProcessor)
            .setHeader(Exchange.HTTP_METHOD, simple("GET"))
            .marshal().json(JsonLibrary.Jackson)
            .toD("${property.path}")
            .convertBodyTo(String.class)
            .process(dataSaveProcessor)
            .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));


    }
}
