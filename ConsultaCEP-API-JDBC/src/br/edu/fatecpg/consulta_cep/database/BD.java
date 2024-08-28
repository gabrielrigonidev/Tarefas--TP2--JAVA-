package br.edu.fatecpg.consulta_cep.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BD {
   private static final String URL = "jdbc:postgresql://localhost:5432/db_consumir_cep";
   private static final String USER = "postgres";
   private static final String PASSWORD = "pass456";

   public static Connection getConnection() {
      try {
         return DriverManager.getConnection(URL, USER, PASSWORD);
      } catch (SQLException e) {
         System.err.println(e.getMessage());
         return null;
      }
   }
}