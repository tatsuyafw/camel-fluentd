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

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.fluentd.logger.FluentLogger;

import java.util.Map;

// [WIP]
public class FluentdProducer extends DefaultProducer {

    private final FluentLogger logger;
    private final FluentdConfiguration configuration;

    public FluentdProducer(FluentdEndpoint endpoint, FluentLogger logger) {
        super(endpoint);
        this.configuration = endpoint.getConfiguration();
        this.logger = logger;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        @SuppressWarnings("unchecked")
        Map<String, Object> body = exchange.getIn().getBody(Map.class);

        // TODO: check body

        String tag = configuration.getTag();
        logger.log(tag, body);
    }

//    private String extractConcatTag() {
//        String tagPrefix = configuration.getTagPrefix();
//        String tag = configuration.getTag();
//
//        // TODO: deal with tagPrefix and tag
//        return "";
//    }
}
