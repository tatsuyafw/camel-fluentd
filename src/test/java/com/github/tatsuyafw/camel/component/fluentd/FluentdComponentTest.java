package com.github.tatsuyafw.camel.component.fluentd;

import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class FluentdComponentTest extends CamelTestSupport {
    @Test
    public void testFluentdComponentWithCamelContext() throws Exception {
        FluentdComponent component = new FluentdComponent(context);
        assertNotNull(component);

        FluentdEndpoint endpoint = (FluentdEndpoint) component.createEndpoint("fluentd://hostname:24224/tag.abc");
        assertNotNull(endpoint);
    }
}
