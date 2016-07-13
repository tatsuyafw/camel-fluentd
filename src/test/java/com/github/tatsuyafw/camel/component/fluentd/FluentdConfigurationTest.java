/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.tatsuyafw.camel.component.fluentd;

import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;

public class FluentdConfigurationTest extends CamelTestSupport {

    @Test
    public void createEndpointWithMinimalConfiguration() {
        FluentdEndpoint endpoint = context.getEndpoint("fluentd://hostname:12345", FluentdEndpoint.class);

        assertThat(endpoint.getHost(), is("hostname"));
        assertThat(endpoint.getPort(), is(12345));
    }

    @Test
    public void defaultPortUsedWhenNotSpecified() {
        FluentdEndpoint endpoint = context.getEndpoint("fluentd://hostname", FluentdEndpoint.class);

        assertThat(endpoint.getPort(), is(FluentdConfiguration.DEFAULT_PORT));
    }

    @Test
    public void testTag1() {
        FluentdEndpoint endpoint = context.getEndpoint("fluentd://hostname/tag.abc", FluentdEndpoint.class);

        assertThat(endpoint.getTag(), is("tag.abc"));
    }

    @Test
    public void testTag2() {
        FluentdEndpoint endpoint = context.getEndpoint("fluentd://hostname/", FluentdEndpoint.class);

        assertNotNull(endpoint);
        assertThat(endpoint.getTag(), is(""));
    }

    @Test
    public void testTag3() {
        FluentdEndpoint endpoint = context.getEndpoint("fluentd://hostname?tag=abc", FluentdEndpoint.class);

        assertNotNull(endpoint);
        assertThat(endpoint.getTag(), is("abc"));
    }

    @Test
    public void testConfigurationCopy() {
        FluentdEndpoint endpoint = context.getEndpoint("fluentd://hostname", FluentdEndpoint.class);
        FluentdConfiguration config1 = endpoint.getConfiguration();
        FluentdConfiguration config2 = config1.copy();

        assertThat(config1, is(not(sameInstance(config2))));
    }

}
