package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.demo.service.UserService;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		System.out.println("Running the application...");

        // Database connection parameters

		String url = "jdbc:h2:mem:testdb"; // Example for H2 in-memory database
        String user = "sa";
        String password = "password";

        // SQL statement
        String createTableSQL = "CREATE TABLE IF NOT EXISTS userdetail (" +
                                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                                "username VARCHAR(255) UNIQUE NOT NULL, " +
                                "usdt_quantity DOUBLE DEFAULT 0.0," +
                                "ethusdt_quantity DOUBLE DEFAULT 0.0," +
                                "btcusdt_quantity DOUBLE DEFAULT 0.0" +
                                ")";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            // Execute the create table SQL statement
            stmt.execute(createTableSQL);
            System.out.println("Create Table successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        UserService userService = context.getBean(UserService.class);
        // call the service to add new user if not exists in DB
        userService.addUserIfNotExists("user1", 50000.0);
	}


}
