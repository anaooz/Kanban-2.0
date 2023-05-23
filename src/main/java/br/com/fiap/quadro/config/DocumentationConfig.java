package br.com.fiap.quadro.config;

import org.springframework.context.annotation.*;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class DocumentationConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                    .info(new Info()
                            .title("Quadro")
                            .version("V1")
                            .description("Uma API para o app de gerenciamento de tarefas")
                            .contact(new Contact().name("Mateus").email("rm94075@fiap.com.br")))
                    .components(new Components().addSecuritySchemes("bearer-key", 
                    new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
                    
                    .bearerFormat("JWT")));
    }
}
