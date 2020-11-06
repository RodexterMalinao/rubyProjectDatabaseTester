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
			// Second SQL SELECT Query
			String query2 = "INSERT INTO recom.temp20201028(customer_id) VALUES (2)";
			// Third SQL SELECT Query
			String query3 = "SELECT * FROM recom.temp20201028"; // 2 records, 1 and 2";
			// Fourth SQL SELECT Query
			String query4 = "UPDATE recom.temp20201028 set customer_id = 3 WHERE customer_id = 2";
			// Fifth SQL SELECT Query
			String query5 = "SELECT * FROM recom.temp20201028"; // 2 records, 1 and 3";
			// Sixth SQL SELECT Query
			String query6 = "DELETE FROM recom.temp20201028 WHERE customer_id = 3";
			// Seventh SQL SELECT Query
			String query7 = "SELECT * FROM recom.temp20201028"; // 1 record, 1";

			// Executing first SELECT query
			ResultSet rs = stmt.executeQuery(query1);

			System.out.println("Result of executing query1 - SELECT * FROM recom.temp20201028");
			System.out.println("CUSTOMER_ID");

			// looping through the number of row/rows retrieved after executing query1
			while (rs.next()) {
				System.out.print(rs.getString("CUSTOMER_ID") + "\t");
			}
			
			// Executing second SELECT query
			ResultSet rs2 = stmt.executeQuery(query2);
			
			System.out.println("Result of executing query2 - INSERT INTO recom.temp20201028(customer_id) VALUES (2)");
			System.out.println("CUSTOMER_ID");
			
			while (rs2.next()) {
				System.out.print(rs.getString("CUSTOMER_ID") + "\t");
			}
			
			// Executing third SELECT query
			ResultSet rs3 = stmt.executeQuery(query3);
			
			System.out.println("Result of executing query3 - SELECT * FROM recom.temp20201028 ");
			System.out.println("CUSTOMER_ID");
			
			while (rs3.next()) {
				System.out.print(rs.getString("CUSTOMER_ID") + "\t");
			}
			
			// Executing fourth query
			int rowsUpdated = stmt.executeUpdate(query4);
			System.out.println("Result of executing query4 - UPDATE recom.temp20201028 set customer_id = 3 WHERE customer_id = 2");
			if (rowsUpdated > 0) {
			    System.out.println("An existing CUSTOMER_ID was updated successfully!");
			}
			
			// Executing fifth query
			ResultSet rs5 = stmt.executeQuery(query5);
			
			System.out.println("Result of executing query5 - SELECT * FROM recom.temp20201028");
			System.out.println("CUSTOMER_ID");
			
			while (rs5.next()) {
				System.out.print(rs.getString("CUSTOMER_ID") + "\t");
			}
			
			// Executing sixth query
			int rowsDeleted = stmt.executeUpdate(query6);
			System.out.println("Result of executing query6 - DELETE FROM recom.temp20201028 WHERE customer_id = 3");
			if (rowsDeleted > 0) {
			    System.out.println("A user was deleted successfully!");
			}
			
			// Executing seventh query
			ResultSet rs7 = stmt.executeQuery(query7);
			
			System.out.println("Result of executing query7 - SELECT * FROM recom.temp20201028");
			System.out.println("CUSTOMER_ID");
			
			while (rs7.next()) {
				System.out.print(rs.getString("CUSTOMER_ID") + "\t\n\n\nEND\n\n");
			}
			
		}

		exit(0);
	}

}
