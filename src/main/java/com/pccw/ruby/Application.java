
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
			
			String table = "recom.temp20201028";
//			String table = "TEMP_CUSTOMER";
			
			// First SQL SELECT Query
			String query1 = "SELECT * FROM "+table;
			// Second SQL SELECT Query
			String query2 = "INSERT INTO "+table+"(CUSTOMER_ID,CUST_NUM) VALUES ('2','2')";
			// Third SQL SELECT Query
			String query3 = "SELECT * FROM "+table; // 2 records, 1 and 2";
			// Fourth SQL SELECT Query
			String query4 = "UPDATE "+table+" set CUSTOMER_ID = 3 WHERE CUSTOMER_ID = 2";
			// Fifth SQL SELECT Query
			String query5 = "SELECT * FROM "+table; // 2 records, 1 and 3";
			// Sixth SQL SELECT Query
			String query6 = "DELETE FROM "+table+" WHERE CUSTOMER_ID = 3";
			// Seventh SQL SELECT Query
			String query7 = "SELECT * FROM "+table; // 1 record, 1";

			// Executing first SELECT query
			ResultSet rs = stmt.executeQuery(query1);

			System.out.println("Result of executing query1 - "+query1);
			System.out.println("CUSTOMER_ID");

			// looping through the number of row/rows retrieved after executing query1
			while (rs.next()) {
				System.out.println(rs.getString("CUSTOMER_ID") + "\t");
			}
			
			// Executing second SELECT query
			int rowsInserted = stmt.executeUpdate(query2);
			if (rowsInserted > 0) {
			    System.out.println("A new user was inserted successfully!");
			}
			
			// Executing third SELECT query
			ResultSet rs3 = stmt.executeQuery(query3);
			
			System.out.println("Result of executing query3 - "+query3);
			System.out.println("CUSTOMER_ID");
			
			while (rs3.next()) {
				System.out.println(rs3.getString("CUSTOMER_ID") + "\t");
			}
			
			// Executing fourth query
			int rowsUpdated = stmt.executeUpdate(query4);
			System.out.println("Result of executing query4 - "+query4);
			if (rowsUpdated > 0) {
			    System.out.println("An existing CUSTOMER_ID was updated successfully!");
			}
			
			// Executing fifth query
			ResultSet rs5 = stmt.executeQuery(query5);
			
			System.out.println("Result of executing query5 - "+query5);
			System.out.println("CUSTOMER_ID");
			
			while (rs5.next()) {
				System.out.println(rs5.getString("CUSTOMER_ID") + "\t");
			}
			
			// Executing sixth query
			int rowsDeleted = stmt.executeUpdate(query6);
			System.out.println("Result of executing query6 - "+query6);
			if (rowsDeleted > 0) {
			    System.out.println("A user was deleted successfully!");
			}
			
			// Executing seventh query
			ResultSet rs7 = stmt.executeQuery(query7);
			
			System.out.println("Result of executing query7 - "+query7);
			System.out.println("CUSTOMER_ID");
			
			while (rs7.next()) {
				System.out.println(rs7.getString("CUSTOMER_ID") + "\t\n\n\nEND\n\n");
			}
			
		}

		exit(0);
	}

}

