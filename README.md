# camel-fluentd

[![Build Status][travis-image]][travis-url]
[![Maven Central][maven-central-image]][maven-central-url]

## Usage

### Dependency

Gradle:

```
compile 'com.tatsuyafw:camel-fluentd:x.y.z'
```

Maven:

```
<dependency>
    <groupId>com.tatsuyafw</groupId>
    <artifactId>camel-fluentd</artifactId>
    <version>x.y.z</version>
</dependency>
```

### URI format

```
fluentd://hostname[:port]/tag[?options]
```

The default port number is `24224`

### Endpoint Options
- T.B.D.

### Message Operations
- T.B.D.

### Message Body
The Component will use the camel message body as a fluentd message body.
The body type must be convertible to `Map<String, String>`.

## Example

```java
public static void main(String[] args) throws Exception {
    CamelContext context = new DefaultCamelContext();
    context.addRoutes(new RouteBuilder() {
        @Override
        public void configure() throws Exception {
            from("direct:fluentd").to("fluentd://localhost/sample.tag.foo.bar");
        }
    });
    context.start();

    ProducerTemplate producer = context.createProducerTemplate();
    Map<String, String> map = new HashMap<String, String>() {
        {
            put("key", "value");
            put("date", "20161025");
        }
    };
    producer.sendBody("direct:fluentd", map);
}
```

## License

Apache License, Version 2.0


[travis-image]: https://img.shields.io/travis/tatsuyafw/camel-fluentd.svg
[travis-url]: https://travis-ci.org/tatsuyafw/camel-fluentd
[maven-central-image]: https://maven-badges.herokuapp.com/maven-central/com.tatsuyafw/camel-fluentd/badge.svg
[maven-central-url]: https://maven-badges.herokuapp.com/maven-central/com.tatsuyafw/camel-fluentd
