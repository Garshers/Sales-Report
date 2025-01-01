package com.salesreport.salesreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// The @SpringBootApplication annotation marks this class as the main entry point for a Spring Boot application.
// It enables component scanning, auto-configuration, and property support.
@SpringBootApplication  
public class SalesreportApplication {

    // The main method is the entry point of the application.
    // It starts the Spring Boot application by calling SpringApplication.run().
    public static void main(String[] args) {
        // SpringApplication.run() bootstraps the Spring application, starting the Spring context and the embedded web server.
        // This method launches the Spring Boot application with the provided arguments.
        SpringApplication.run(SalesreportApplication.class, args);
        
		System.out.println("-----Success-----");
    }
}