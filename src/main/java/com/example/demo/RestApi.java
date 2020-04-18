package com.example.demo;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
class RestApi extends RouteBuilder {

    private static final Logger LOG = LogManager.getLogger(RouteBuilder.class.getName());


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

//
//        rest("/api/").description("Teste REST Service")
//                .id("api-route")
//                .post("/bean")
//                .produces(MediaType.APPLICATION_JSON)
//                .consumes(MediaType.APPLICATION_JSON)
//                .bindingMode(RestBindingMode.auto)
//                .type(MyBean.class)
//                .enableCORS(true)
//                //.to("direct:remoteService2");
//                //.to("direct:kafkaProducer");
//                .to("direct:mailProducer");
//
//
//        from("direct:mailProducer")
//                .routeId("mailProducer")
//                .setHeader("subject", simple("Testing from camel rest"))
//                //.setHeader("body", simple("${body.name}"))
//                .setBody(simple("${body.name}"))
//                .setHeader("Content-Type", constant("text/html"))
//                .setHeader("to", simple("sivakumarreddy524@gmail.com,srini.guthula@gmail.com"))
//                .to("smtps://smtp.gmail.com:465?username=epos.erpdirect@gmail.com&password=erpdirect@!@#")
//                .setBody(simple("OK"))
//                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));
//
//        from("direct:kafkaProducer")
//                .routeId("kafkaProducer")
//                //.setBody(constant("Message from Camel"))
//                .setHeader(KafkaConstants.KEY, constant("Camel"))
//                .to("kafka:test?brokers=localhost:9092");
//
//        from("kafka:test?brokers=localhost:9092")
//                .log("Message received from Kafka : ${body}")
//                .log("    on the topic ${headers[kafka.TOPIC]}")
//                .log("    on the partition ${headers[kafka.PARTITION]}")
//                .log("    with the offset ${headers[kafka.OFFSET]}")
//                .log("    with the key ${headers[kafka.KEY]}")
//                .process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//
//                    }
//                })
//                .to("");
//        // .to("direct:mailProducerFromKafka");
//
//
//        from("direct:mailProducerFromKafka")
//                .routeId("mailProducerFromKafka")
//                .setHeader("subject", simple("Testing from camel kafka consumer"))
//                //.setHeader("body", simple("${body.name}"))
//                .setBody(simple("${body}"))
//                .setHeader("Content-Type", constant("text/html"))
//                .setHeader("to", simple("sivakumarreddy524@gmail.com,srini.guthula@gmail.com"))
//                .to("smtps://smtp.gmail.com:465?username=epos.erpdirect@gmail.com&password=erpdirect@!@#")
//                .setBody(simple("OK"))
//                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));
//
//
//        from("direct:remoteService2")
//                .routeId("direct-route2")
//                .process(routeProcessor)
//                .setHeader(Exchange.HTTP_METHOD, simple("GET"))
//                .marshal().json(JsonLibrary.Jackson)
//                .toD("${property.path}")
//                .convertBodyTo(String.class)
//                .process(dataSaveProcessor)
//                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));


//        from("ftp://eposfx.com/logs?username=uploads@eposfx.com&password=erp@uploads&passiveMode=true&scheduler=quartz2&scheduler.cron=0 */1 * ? * *")
//                .process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        System.out.println("=========>>>" + exchange.getIn().getBody());
//                    }
//                })
//                .to("file:output/siva?autoCreate=true&keepLastModified=true");
//                //.to("file:output/siva?autoCreate=true&fileName=${file:name.noext}_cp.${file:name.ext}&keepLastModified=true");


//        from("aws-s3://aptdc-image-lib?region=US_EAST_1&accessKey=AKIARVEZI66PEIFJJ626" +
//                "&secretKey=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&deleteAfterRead=false&scheduler=quartz2&scheduler.cron=0 */1 * ? * *")
//                .process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        System.out.println("from s3 ==========>>>>" + exchange.getIn().getBody());
//                        System.out.println("from s3 ==========>>>>" + exchange.getIn().getHeaders().get("CamelAwsS3Key"));
//
//
//
//                    }
//                })
//                .to("file:output/siva?autoCreate=true&keepLastModified=true&fileName=${header.CamelAwsS3Key}");

        from("sftp://test.rebex.net/pub/example?username=demo&password=password&passiveMode=true&scheduler=quartz2&scheduler.cron=0 */1 * ? * *")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("=========>>>" + exchange.getIn().getBody());
                        LOG.log(Level.INFO, "=========>>>" + exchange.getIn().getBody());

                    }
                })
                .to("file:output/siva?autoCreate=true&keepLastModified=true");
        //.to("file:output/siva?autoCreate=true&fileName=${file:name.noext}_cp.${file:name.ext}&keepLastModified=true");


    }
}

