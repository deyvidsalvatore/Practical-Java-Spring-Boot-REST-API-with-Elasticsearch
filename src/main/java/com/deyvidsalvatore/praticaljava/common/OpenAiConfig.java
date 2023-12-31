package com.deyvidsalvatore.praticaljava.common;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiConfig {

    @Bean
    public OpenAPI practicalJavaOpenApi() {
        var info = new Info()
                .title("Practical Java API")
                .description("Open API (Swagger) documentation auto generated by code")
                .version("1.0");
        return new OpenAPI().info(info);
    }
}
