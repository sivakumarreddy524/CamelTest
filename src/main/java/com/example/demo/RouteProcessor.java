package com.example.demo;


import com.example.demo.domain.RouteConfig;
import com.example.demo.service.RouteConfigService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class RouteProcessor implements Processor {



    RouteConfigService routeConfigService;

    public RouteProcessor(RouteConfigService routeConfigService){
        this.routeConfigService=routeConfigService;
    }


    @Override
    public void process(Exchange exchange) throws Exception {

        MyBean b = (MyBean) exchange.getIn().getBody();

        RouteConfig r = routeConfigService.findByName(b.getName());

        if (r != null) {
            b.setPath(r.getRoutePath());
        }

        exchange.setProperty("path",b.getPath());

        exchange.getOut().setBody(b);


    }
}
