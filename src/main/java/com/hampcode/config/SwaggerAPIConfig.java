package com.hampcode.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerAPIConfig {

    @Value("${nutri.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        // Definir el servidor de desarrollo
        Server devServer = new Server();
        devServer.setUrl(this.devUrl);
        devServer.setDescription("Development Server");

        // Informacion de contacto
        Contact contact = new Contact();
        contact.setEmail("correofeo2718@gmail.com");
        contact.setName("NutriMedTeam");
        contact.setUrl("https://elysian-c.github.io/NutriMed/landing");

        License mitLicense = new License().name("MIT License").url("https://opensource.org/licenses/MIT");

        // Informacion general de la API
        Info info = new Info()
                .title("API NutriMed Vida Saludable")
                .version("1.0")
                .contact(contact)
                .description("API Nutrimed, mejorar calidad de vida")
                .termsOfService("https://elysian-c.github.io/NutriMed/landing")
                .license(mitLicense);

        return new OpenAPI()
                .info(info)
                .addServersItem(devServer);
    }
}
