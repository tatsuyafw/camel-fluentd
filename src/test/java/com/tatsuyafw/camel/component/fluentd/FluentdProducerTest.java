package com.tatsuyafw.camel.component.fluentd;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class FluentdProducerTest extends CamelTestSupport {

    @Test
    public void testFluentdProducer() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMessageCount(1);

        Map<String, String> body = new HashMap<>();
        body.put("test", "value");
        template.sendBody("direct:start", body);

        assertMockEndpointsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start").to("fluentd:127.0.0.1/tag.test").to("mock:result");
            }
        };
    }

}
