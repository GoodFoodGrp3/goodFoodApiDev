package com.goodfood.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * <p>
 *  Class principal qui permet de lancer l'application.
 * </p>
 * @author Gaëtan T.
 */
@SpringBootApplication(exclude = { RedisRepositoriesAutoConfiguration.class })
public class GoodFoodApiApplication extends SpringBootServletInitializer
{
    @Override
    protected SpringApplicationBuilder configure (SpringApplicationBuilder builder)
    {
        return builder.sources(GoodFoodApiApplication.class);
    }

    /**
     * <p><b>Méthode</b> qui permet de lancer l'application.
     *</p>
     * @param args c'est un tableau de strings.
     */
    public static void main(String[] args)
    {
        SpringApplication.run(GoodFoodApiApplication.class, args);
    }
}
