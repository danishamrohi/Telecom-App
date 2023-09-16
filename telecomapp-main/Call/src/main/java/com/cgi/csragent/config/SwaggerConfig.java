package com.cgi.csragent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket getDocket()
	{
		return new Docket(DocumentationType.SWAGGER_2).select()
								.apis(RequestHandlerSelectors.basePackage("com.cgi.Call"))
								.build()
								.apiInfo(setDetails())
								.useDefaultResponseMessages(false);
	}
	
	public ApiInfo setDetails()
	{
		ApiInfoBuilder apibuild=new ApiInfoBuilder();
		apibuild.title("Telecom App").version("Ver 1.1").description("This app is capture call details from a customer by a CSR Agent")
									.license("TelecomApp@cgi.com");
		return apibuild.build();
	}

}