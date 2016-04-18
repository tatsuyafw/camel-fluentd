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

import org.apache.camel.RuntimeCamelException;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriPath;

import java.net.URI;
import java.util.Optional;

public class FluentdConfiguration implements Cloneable {

    public static final int DEFAULT_PORT = 24224;

    @UriPath @Metadata(required = "true")
    private String hostname;
    @UriPath @Metadata(defaultValue = "" + DEFAULT_PORT)
    private int port;

    /**
     * Returns a copy of this configuration
     */
    public FluentdConfiguration copy() {
        try {
            return (FluentdConfiguration) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeCamelException(e);
        }
    }

    public void configure(URI uri) {
        Optional<String> hostnameOpt = Optional.ofNullable(uri.getHost());
        hostnameOpt.ifPresent(hostname -> setHost(hostname));

        Optional<Integer> portOpt = Optional.ofNullable(uri.getPort());
        portOpt.ifPresent(port -> setPort(port));
    }

    public String getHost() {
        return this.hostname;
    }

    public void setHost(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "FluentdConfiguration[hostname=" + hostname + ":" + port + "]";
    }
}
