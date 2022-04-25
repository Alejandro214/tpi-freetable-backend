package com.restaurant.restaurantApi.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@Import({BeanValidatorPluginsConfiguration.class})
public class SwaggerConfig {

    @Bean
    public Docket apiDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

   /* private ApiInfo getApiInfo(){
        return new ApiInfo("Restaurant service API", "This API provides all methods required for Account management",
                "1.0",
                "TERMS OF SERVICE URL",null,null,null);
                //new Contact("ELDAR SRL","http://https://www.eldars.com.ar/","humancapital@eldars.com.ar"),
                //"LICENSE","LICENSE URL", Collections.emptyList());
    }*/
}
