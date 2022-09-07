package com.goodfood.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * <p>
 *  Class qui permet la configuration du swagger 2
 * </p>
 *t
 * @see <a href="http://localhost:8080/swagger-ui/">Api graphique accessible ici</a>
 * @see <a href="http://localhost:8080/v2/api-docs">Api basique accessible ici</a>
 * @author Gaëtan T.
 */


public class SwaggerConfig
{
    /**
     * <p>
     *  Une méthode qui retourne le swagger en api graphique
     * </p>
     * @return l'api graphique en swagger 2
     */
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
