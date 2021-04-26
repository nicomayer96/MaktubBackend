package com.maktub.gestion;

import java.util.TimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = "com.maktub.controller")
@Controller
public class GestionMaktubApplication {
            
	public static void main(String[] args){
            System.setProperty("server.servlet.context-path", "/maktub");
		SpringApplication.run(GestionMaktubApplication.class, args);
                TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

        
@Configuration        
public class CORSConfig{
          
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurerAdapter(){
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/**")
                        .allowedMethods("GET","POST","PUT","DELETE")
                        .allowedOrigins("*")
                        .allowedHeaders("*");
                
            }
        };
    }
    }

}

