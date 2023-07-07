package com.deyvidsalvatore.praticaljava.server;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping(value = "/api")
public class DefaultRestApi {

    @GetMapping("/welcome")
    public String welcome() {
        System.out.println(StringUtils.join("Hello", " this is", " Spring Boot", " REST API"));
        return "Welcome to Spring Boot Deyvid";
    }

    @GetMapping("/time")
    public String getTime() {
        return LocalTime.now().toString();
    }

}
