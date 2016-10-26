# camel-fluentd [![Build Status][travis-image]][travis-url]

## Usage

### Dependency
- T.B.D.

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

[travis-image]: https://img.shields.io/travis/tatsuyafw/camel-fluentd.svg
[travis-url]: https://travis-ci.org/tatsuyafw/camel-fluentd
