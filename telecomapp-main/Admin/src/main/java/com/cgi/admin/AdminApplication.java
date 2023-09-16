package com.cgi.admin;

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

import com.cgi.admin.filter.AdminFilter;


@EnableEurekaClient
@SpringBootApplication
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
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
     fbean.setFilter(new AdminFilter());
     fbean.addUrlPatterns("/api/v1/csr/admin/*");
     
     return fbean;
	}

}
