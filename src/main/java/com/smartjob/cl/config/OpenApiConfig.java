package com.smartjob.cl.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class configures the information API Docs. <br/>
 * <b>Class</b>: {@link OpenApiConfig}<br/>
 * <b>Copyright</b>: &Copy; 2024 SmartJob. <br/>
 * <b>Company</b>: SmartJob. <br/>
 *
 * @author SmartJob <br/>
 *     <u>Service Provider</u>: Consultor TI <br/>
 *     <u>Developed by</u>: <br/>
 *     <ul>
 *     <li>Carlos Augusto Nole Machaca</li>
 *     </ul>
 *     <u>Changes</u>:<br/>
 *     <ul>
 *     <li>Nov 17, 2024 (JAR) Creation class.</li>
 *     </ul>
 * @version 1.0
 */
@Configuration
public class OpenApiConfig {

    /**
     * Define the basic information that will appear in your API documentation, including title, description, version,
     * terms of service, contact, and license.
     *
     * @return OpenAPI bean custom.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("RESTful API for creating users.")
                        .version("1.0.0")
                        .description("Application that exposes a RESTful API for user creation.")
                        .termsOfService("https://smartjob.cl/")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("Ing. Inf. Carlos Augusto Nole Machaca")
                                .url("https://www.linkedin.com/in/carlos-augusto-nole-machaca-1ba16a49/")
                                .email("ing.inf.cnolem@outlook.com"))
                        .license(new License()
                                .name("GNU")
                                .url("https://www.gob.pe/indecopi"))
                );
    }

    /**
     * Configure the default API group. You can adjust this if you need to create additional groups of APIs.
     *
     * @return GroupedOpenApi groups of APIs.
     */
    @Bean
    public GroupedOpenApi defaultApi() {
        return GroupedOpenApi.builder()
                .group("default")
                .pathsToMatch("/**")
                .build();
    }

}
