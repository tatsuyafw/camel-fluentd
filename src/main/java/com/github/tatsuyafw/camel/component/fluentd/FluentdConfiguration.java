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
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;

import java.net.URI;
import java.util.Optional;

public class FluentdConfiguration implements Cloneable {

    public static final int DEFAULT_PORT = 24224;

    @UriPath @Metadata(required = "true")
    private String host;
    @UriPath @Metadata(defaultValue = "" + DEFAULT_PORT)
    private int port;
    @UriPath @Metadata
    private String tagPrefix;
    @UriParam
    private String tag;

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
        Optional<String> hostOpt = Optional.ofNullable(uri.getHost());
        hostOpt.ifPresent(hostname -> setHost(host));

        Optional<Integer> portOpt = Optional.ofNullable(uri.getPort());
        portOpt.ifPresent(port -> setPort(port));

        Optional<String> tagPrefixOpt = Optional.ofNullable(uri.getPath());
        tagPrefixOpt.ifPresent(tagPrefix -> setTagPrefix(tagPrefix));
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getTagPrefix() {
        return this.tagPrefix;
    }

    public void setTagPrefix(String tagPrefix) {
        this.tagPrefix = tagPrefix;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "FluentdConfiguration[hostname=" + host + ":" + port + "]";
    }
}
