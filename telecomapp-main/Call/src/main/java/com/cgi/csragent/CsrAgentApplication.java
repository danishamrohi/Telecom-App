package com.cgi.csragent;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.cgi.csragent.filter.CsrFilter;

@EnableEurekaClient
@SpringBootApplication
public class CsrAgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsrAgentApplication.class, args);
    }

   @Bean
	public FilterRegistrationBean getfilter()
	{
		UrlBasedCorsConfigurationSource urlconfig=new UrlBasedCorsConfigurationSource();
     CorsConfiguration config=new CorsConfiguration();

     config.setAllowCredentials(true);
     List<String> all=Arrays.asList("*");

     config.setAllowedOrigins(all);
     config.setAllowedMethods(all);
     config.setAllowedHeaders(all);

     urlconfig.registerCorsConfiguration("/**", config);

     FilterRegistrationBean fbean=new FilterRegistrationBean(new CorsFilter(urlconfig));
     fbean.setFilter(new CsrFilter());
     fbean.addUrlPatterns("/call/add");
     //fbean.addUrlPatterns("/call/add/");
     fbean.addUrlPatterns("/call/all-with-ph/*");
		return fbean;
	}

}
