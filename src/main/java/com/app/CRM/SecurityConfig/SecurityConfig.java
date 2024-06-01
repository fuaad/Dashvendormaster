    package com.app.CRM.SecurityConfig;

import com.app.CRM.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.AuthorizedUrl;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
   @Autowired
   private UserService userService;

   @Bean
   public WebSecurityCustomizer webSecurityCustomizer() {
      return (web) -> {
         web.ignoring();
      };
   }

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      ((HttpSecurity)((FormLoginConfigurer)((HttpSecurity)((AuthorizedUrl)((AuthorizedUrl)((AuthorizedUrl)((AuthorizedUrl)((HttpSecurity)http.csrf().disable()).authorizeRequests().requestMatchers(HttpMethod.POST, new String[]{"/api/products/createps"})).permitAll().requestMatchers(new String[]{"/redirect"})).permitAll().requestMatchers(new String[]{"/dashboard"})).hasRole("admin").anyRequest()).authenticated().and()).formLogin().defaultSuccessUrl("/redirect", true)).and()).logout().permitAll();
      return (SecurityFilterChain)http.build();
   }
}