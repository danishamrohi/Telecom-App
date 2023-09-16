package com.cgi.customer;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


import com.cgi.customer.filter.CustomerFilter;

@EnableEurekaClient
@SpringBootApplication
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
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
     fbean.setFilter(new CustomerFilter());
     fbean.addUrlPatterns("/api/v1/customer/get/*");
     fbean.addUrlPatterns("/api/v1/customer/update/*");
     fbean.addUrlPatterns("/api/v1/customer/delete/*");
		return fbean;
	}
}
