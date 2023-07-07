package com.deyvidsalvatore.praticaljava.server;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping(value = "/api")
public class DefaultRestApi {

    private final Logger LOG = LoggerFactory.getLogger(DefaultRestApi.class);

    @GetMapping("/welcome")
    public String welcome() {
        LOG.info(StringUtils.join("Hello", " this is", " Spring Boot", " REST API"));
        return "Welcome to Spring Boot Deyvid";
    }

    @GetMapping("/time")
    public String getTime() {
        return LocalTime.now().toString();
    }

    @GetMapping("/headerOne")
    public String headerByAnnotation(
            @RequestHeader(name = "User-agent", required = false) String headerUserAgent,
            @RequestHeader(name = "Practical-java", required = false) String headerPracticalJava) {
        return "User-agent: " + headerUserAgent + ", Practical-java : " + headerPracticalJava;
    }

    @GetMapping("/headerTwo")
    public String headerByRequest(
        ServerHttpRequest request
    ) {
        return "User-agent: " + request.getHeaders().getValuesAsList("User-agent") +
                ", Pratical-java : " +
                request.getHeaders().getValuesAsList("Practical-java");
    }
}
