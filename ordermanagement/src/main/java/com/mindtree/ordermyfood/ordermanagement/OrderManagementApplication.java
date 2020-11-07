package com.mindtree.ordermyfood.ordermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OrderManagementApplication 
{
    public static void main( String[] args )
    {
       SpringApplication.run(OrderManagementApplication.class, args);
    }
}
