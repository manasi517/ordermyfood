package com.mindtree.ordermyfood.searchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@EnableCaching
@EnableDiscoveryClient
@SpringBootApplication
public class SearchServiceApplication {
    public static void main( String[] args )
    {
       SpringApplication.run(SearchServiceApplication.class, args);
    }
}
