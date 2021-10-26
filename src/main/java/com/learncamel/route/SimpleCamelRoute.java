package com.learncamel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by z001qgd on 1/24/18.
 */
@Component
public class SimpleCamelRoute  extends RouteBuilder{

    @Autowired
    Environment environment;

    @Override
    public void configure() throws Exception {

        from("{{startRoute}}").setProperty("i", "10").routeId("timer-route-id").setHeader("username", "user")
        		.setBody("select * from projects where license = :?lic and id > :?min order by id")
                .log("Timer Invoked and the body" + environment.getProperty("message"))
                .pollEnrich("{{fromRoute}}")
                .setBody("<customer><customerId>1123</customerId></customer>")
                .to("{{toRoute1}}").setHeader("username", "user")
                .to("http://localhost:8080")
                .to("ftp://localhost");


    }
}
