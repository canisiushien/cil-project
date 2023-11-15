package cil.bf.activiteApp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Configuration of web application with Servlet 3.0 APIs.
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Configuration
public class WebConfigurer {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowCredentials(false);
        config.setMaxAge(1800L);
        config.setExposedHeaders(Arrays.asList("Authorization,Link,X-Total-Count,X-SIGACIL-alert,X-SIGACIL-error,X-SIGACIL-params"));
        if (!CollectionUtils.isEmpty(config.getAllowedOrigins())) {
            source.registerCorsConfiguration("/api/**", config);
            source.registerCorsConfiguration("/v3/api-docs", config);
            source.registerCorsConfiguration("/swagger-resources", config);
            source.registerCorsConfiguration("/swagger-ui/**", config);

        }
        return new CorsFilter(source);
    }

    //swagger doc config
    @Bean
    public OpenAPI openApiInformation() {
        Server localServer = new Server()
                .url("http://localhost:8080")
                .description("Localhost Server URL");

        Contact contact = new Contact()
                .email("infos@cil.bf")
                .name("CIL Dev team");

        Info info = new Info()
                .contact(contact)
                .description("Swagger pour la gestion des activites de la cil")
                .summary("Demo of Spring Boot 3 & Open API 3 Integration")
                .title("ACTIVITE-CIL API REST")
                .version("V1.0.0")
                .license(new License());

        return new OpenAPI().info(info).addServersItem(localServer);
    }
}
