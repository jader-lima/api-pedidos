package br.com.pedidos.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class GpConnectionFactory {
	
	public static Connection getConnection() throws ClassNotFoundException {
		
		 try {
			 //"jdbc:jtds:sqlserver://ATHENAS:1433", "sa", "A12345678a"
			 //jdbc:jtds:sqlserver://<yourDBServerIPAddress>\SQLEXPRESS:1433;databaseName=AdventureWorks;user=sa;password=*****;
			 String url = "jdbc:sqlserver://localhost:1433;user=sa;password=A12345678a";
			 //Class.forName("net.sourceforge.jtds.jdbc.Driver");
			 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			 return DriverManager.getConnection( url); 
		 } catch(SQLException e) {
			 throw new RuntimeException(e);
		 }
		 
	}

}
