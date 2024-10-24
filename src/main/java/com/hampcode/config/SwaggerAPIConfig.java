package com.hampcode.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

@Configuration
public class SwaggerAPIConfig {

    @Value("${nutri-api.openapi.dev-url}")
    private String devUrl;

    @Value("${nutri-api.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI(){
        //Definir el servidor de desarrollo
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Development Server");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Production Server");

        //Informacion de contacto
        Contact contact = new Contact();
        contact.setEmail("nutrimed@gmail.com");
        contact.setName("Nutrimed");
        contact.setUrl("https://t.ly/gwCPV");

        License mitLicense = new License().name("MIT License").url("https://opensource.org/licenses/MIT");

        //Informacion general de la API
        Info info = new Info()
                .title("API Nutrimed planes nutricionales")
                .version("1.0")
                .contact(contact)
                .description("API Restful de nutrimed")
                .termsOfService("https://t.ly/gwCPV")
                .license(mitLicense);

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer, prodServer));
    }

}