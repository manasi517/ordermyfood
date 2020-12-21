# Ordermyfood

Ordermyfood is an application to view list of all restaurants and their menu with various features such as search and filter. 
Select the food item to order and decide the payment method. 
The application fetch restaurants details and their menu from an external API and saves them into database.

## Prerequisite
* Jdk 1.8
* Maven 3.3 +
* Docker


## Technologies Used
* Java 1.8
* Maven to build the project
* Microservices architecture with Spring boot framework
* Netflix OSS to configure eureka server and client 
* Spring Cloud for maintaing property files
* Spring security for Admin and User roles
* MySql database to maintain restaurants and users
* Hibernate and JPA for connectivity between application and database
* Kafka to maintain the queue of users while ordering the food
* Docker to build and image and deploy the code


## Installation

* Unzip the folder
* Build every project using comman 'mvn clean install'
* First run ConfigServer to load all the properties files
* Second run the Ordermyfood application to start eureka server
* Run all the other applications 

## Usage
* First use the search application to search all the restaurants and their menu
* Second use order management application to select the food item and add them to cart
* Third use user management application to access your cart and select payment method
