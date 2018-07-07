package com.kinley.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	
	private static final String DEFAULT_REGEX_PATTERNS = "/rest.*";
	@Bean
	public Docket productApi(){
		return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kinley.rest"))
                .paths(regex(DEFAULT_REGEX_PATTERNS))
                .build()
                .apiInfo(metaInfo());
	}
	
	 private ApiInfo metaInfo() {

	        ApiInfo apiInfo = new ApiInfo(
	                "Spring Boot Sample Project",
	                "The APIs here demonstrate creating a 'production-ready' service built with Spring Boot and other helpful libraries",
	                "1.0",
	                "Terms of Service",
	                new Contact("Site Name", "https://www.google.com/",
	                        "abc@gmail.com"),
	                "Apache License Version 2.0",
	                "https://www.apache.org/licesen.html"
	        );
	        return apiInfo;
	    }
}
