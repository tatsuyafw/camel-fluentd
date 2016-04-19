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

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;
import org.fluentd.logger.FluentLogger;

/**
 *  The fluentd component is used for interacting with Fluentd server.
 */
@UriEndpoint(scheme = "fluentd", title = "Fluentd", syntax = "fluentd:hostname:port/tag", producerOnly = true, label = "monitoring")
public class FluentdEndpoint extends DefaultEndpoint {

    private FluentLogger logger;

    @UriParam
    private FluentdConfiguration configuration;

    public FluentdEndpoint(String endpointUri, FluentdComponent component) {
        super(endpointUri, component);
    }

    @Override
    public Producer createProducer() throws Exception {
        return new FluentdProducer(this, logger);
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        throw new UnsupportedOperationException("Cannot consume from a FluentdEndpoint " + getEndpointUri());
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public FluentdConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(FluentdConfiguration configuration) {
        this.configuration = configuration;
    }

    public String getHost() {
        return getConfiguration().getHost();
    }

    public void setHost(String host) {
        getConfiguration().setHost(host);
    }

    public int getPort() {
        return getConfiguration().getPort();
    }

    public void setPort(int port) {
        getConfiguration().setPort(port);
    }

    public synchronized FluentLogger getLogger() {
        if (logger == null) {
            String host = configuration.getHost();
            int port = configuration.getPort();
            logger = FluentLogger.getLogger(null, host, port);
        }
        return logger;
    }
}
