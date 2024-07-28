package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Testing only!!!!!!!!!!!!!!!!");

		String url = "jdbc:h2:mem:testdb"; // Example for H2 in-memory database
        String user = "sa";
        String password = "password";

        // SQL statement
        String createTableSQL = "CREATE TABLE IF NOT EXISTS userdetail (" +
                                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                                "username VARCHAR(255) UNIQUE NOT NULL, " +
                                "wallet_balance DOUBLE NOT NULL" +
                                ")";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            // Execute the SQL statement
            stmt.execute(createTableSQL);
            System.out.println("Table created successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
	}


}
