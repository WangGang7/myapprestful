package cn.nawa.restful;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.nawa.restful.web"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("RESTful API for Jeremy's Love App")
                .description("This document is the API specification for Jeremy's Love App. btw, Jeremy is a GuaPi~,  'GuaPi' means highly intelligent!")
                .termsOfServiceUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .version("1.0")
                .contact(new Contact("Nawa", null, "isven0_0@sina.com"))
                .build();
    }
}

