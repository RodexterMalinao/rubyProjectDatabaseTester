package com.pccw.ruby;

import static java.lang.System.exit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

//for jsr310 java 8 java.time.*
//@EntityScan(
//        basePackageClasses = { SpringBootConsoleApplication.class, Jsr310JpaConverters.class }
//)
@SpringBootApplication
public class Application implements CommandLineRunner {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	DataSource dataSource;

//    @Autowired
//    CustomerRepository customerRepository;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Transactional(readOnly = true)
	@Override
	public void run(String... args) throws Exception {
		TimeZone timeZone = TimeZone.getTimeZone("Asia/Kolkata");
		TimeZone.setDefault(timeZone);
		Connection conn = dataSource.getConnection();

		if (conn != null) {
			System.out.println("Test connection successfully!");

			Statement stmt = conn.createStatement();

			// First SQL SELECT Query
			String query1 = "SELECT * FROM recom.temp20201028";

			// Executing first SELECT query
			ResultSet rs = stmt.executeQuery(query1);

			System.out.println("Result of executing query1");
			System.out.println("Customer_ID");

			// looping through the number of row/rows retrieved after executing query2
			while (rs.next()) {
				System.out.print(rs.getString("Customer_ID") + "\t");
			}
		}

		exit(0);
	}

}
