package com.deyvidsalvatore.praticaljava.server;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.Random;

@RestController
@RequestMapping(value = "/api")
@Tag(name = "Default REST API", description = "Documentation for Default REST API")
public class DefaultRestApi {

    private final Logger LOG = LoggerFactory.getLogger(DefaultRestApi.class);

    @GetMapping("/welcome")
    @Operation(summary = "welcome", description = "Description for welcome API")
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

    @GetMapping("/random-error")
    public ResponseEntity<String> randomError() {
        int remainder = new Random().nextInt() % 5;
        var body = "Kibana";

        return switch (remainder) {
            case 0 -> ResponseEntity.ok().body(body);
            case 1, 2 -> ResponseEntity.badRequest().body(body);
            default -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        };
    }
}
