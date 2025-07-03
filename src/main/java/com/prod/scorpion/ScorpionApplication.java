package com.prod.scorpion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class ScorpionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScorpionApplication.class, args);
    }

    @Bean
    public CommandLineRunner testConnection(DataSource dataSource) {
        return args -> {
            try (var connection = dataSource.getConnection()) {
                if (connection != null) {
                    System.out.println("Connection to the database established successfully!");
                } else {
                    System.out.println("Failed to make connection!");
                }
            } catch (Exception e) {
                System.err.println("Error while connecting to the database: " + e.getMessage());
            }
        };
    }

}
