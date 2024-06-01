    package com.app.CRM.SecurityConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
   public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/api/**").allowedOrigins(new String[]{"https://www.dashvendormaster.com/"}).allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE", "OPTIONS"}).allowedHeaders(new String[]{"*"}).allowCredentials(true);
   }
}
    