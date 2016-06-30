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
import org.fluentd.logger.FluentLogger;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

public class FluentdEndpointTest extends CamelTestSupport {

    @Test
    public void testDefaults() {
        FluentdEndpoint endpoint = context.getEndpoint("fluentd:hostname:port/tag", FluentdEndpoint.class);

        assertThat(endpoint, is(notNullValue()));
    }

    @Test
    public void testEndpointHasConfiguration() {
        FluentdEndpoint endpoint = context.getEndpoint("fluentd:hostname:12345", FluentdEndpoint.class);

        assertThat(endpoint.getConfiguration(), is(notNullValue()));
    }

    @Test
    public void testGetLoggerReturnsSameLogger() {
        FluentdEndpoint endpoint = context.getEndpoint("fluentd:hostname:12345", FluentdEndpoint.class);

        FluentLogger expected = endpoint.getLogger();
        assertThat(endpoint.getLogger(), is(sameInstance(expected)));
    }

}
