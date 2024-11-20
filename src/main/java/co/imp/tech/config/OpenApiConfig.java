package co.imp.tech.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class OpenApiConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                // Disable CSRF for development if needed
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        // Allow public access to Swagger UI and OpenAPI documentation
//                        .requestMatchers(
//                                "/swagger-ui/**",
//                                "/v3/api-docs/**",
//                                "/swagger-ui.html",
//                                "/swagger-resources/**",
//                                "/webjars/**",
//                                "/api-docs/**"
//                        ).permitAll()
//                        // Require authentication for all other requests
//                        .anyRequest().authenticated()
//                )
//                .formLogin(login -> login.permitAll());  // Enable form login (if needed)

        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection (if needed)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permit all requests without authentication
                );


        return http.build();
    }

    @Bean
    public OpenAPI customOpenAPI (
            @Value("${openapi.service.title}") String openApiTitle,
            @Value("${openapi.service.version}") String openApiVersion
    ) throws Exception {


        return new OpenAPI()
                .info(new Info().title(openApiTitle).version(openApiVersion).license(new License().name("Apache 2.0")
                        .url("http://springdoc.org"))).externalDocs(new ExternalDocumentation().description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"))
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("bearer-key"));
    }
}
