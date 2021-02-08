package com.ooredoo.franchise.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ooredoo Franchise Application")
                        .version("V1.0")
                        .description("Ooredoo Franchise API")
                        .termsOfService("https://www.ooredoo.qa/")
                        .license(new License().name("Apache 2.0").url("https://www.ooredoo.qa/")));
    }
}
