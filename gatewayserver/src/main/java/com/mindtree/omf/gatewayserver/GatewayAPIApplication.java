package com.mindtree.omf.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableZuulProxy
@SpringBootApplication
@EnableDiscoveryClient
@EnableWebSecurity
public class GatewayAPIApplication extends WebSecurityConfigurerAdapter{

	public static void main( String[] args )
	{

		SpringApplication.run(GatewayAPIApplication.class, args);

	}

	
	  @Override protected void configure(HttpSecurity http) throws Exception {
	  
	  http 
	  .csrf().disable() 
	  .authorizeRequests(a -> a
	  .antMatchers("/user-management-service/api/v1/customer/**",
	  "/order-management-service/**","/search-service/**","/user-management-service/swagger-ui.html") .permitAll() .anyRequest().authenticated() )
	  .oauth2Login();
	  
	  }
	 


}
