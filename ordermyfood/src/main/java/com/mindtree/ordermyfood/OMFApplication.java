package com.mindtree.ordermyfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class OMFApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(OMFApplication.class, args);
    }
}
