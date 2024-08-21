package controller;
import database.DB;
import model.Tarefa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {
   public static void criarTabela() throws SQLException {
      try (Connection connection = DB.getConnection()) {
         if (connection != null) {
            String query = "CREATE TABLE IF NOT EXISTS tarefa(id SERIAL PRIMARY KEY,titulo VARCHAR(100) NOT NULL,descricao VARCHAR(100) NOT NULL,categoria VARCHAR(100),status BOOLEAN DEFAULT FALSE)";
            try (Statement stmt = connection.createStatement()) {
               stmt.execute(query);
               System.out.println("Tabela Tarefas criada ou ja existia");
            } catch (SQLException e) {
               System.out.println("Erro ao Criar tabela: " + e.getMessage());
            }
         }
      }
   }

   public static void selectAll() throws SQLException {
      List<Tarefa> tarefas = new ArrayList<>();
      try (Connection connection = DB.getConnection()) {
         String querySelect = "SELECT * FROM tarefa";
         try (PreparedStatement stmt = connection.prepareStatement(querySelect)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {tarefas.add(new Tarefa(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("descricao"),
                    rs.getString("categoria"),
                    rs.getBoolean("status")));
            }
         }
         for (Tarefa tarefa : tarefas) {
            System.out.println(tarefa.toString());
         }
      }
   }

   public static void inserirTarefa(String titulo, String descricao, String categoria) throws SQLException {
      try (Connection connection = DB.getConnection()) {
         if (connection != null) {
            String query = "INSERT INTO tarefa(titulo,descricao,categoria) VALUES(?,?,?)";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
               stmt.setString(1, titulo);
               stmt.setString(2, descricao);
               stmt.setString(3, categoria);
               stmt.execute();
            } catch (SQLException e) {
               System.out.println("Erro ao tentar INSERIR no banco de dados: " + e.getMessage());
            }
         }
      }
   }

   public static void editarTarefa(String titulo, String descricao, String categoria, int id) throws SQLException {
      try (Connection connection = DB.getConnection()) {
         if (connection != null) {
            String query = "UPDATE tarefa SET titulo = ?, descricao = ?, categoria = ? WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
               stmt.setString(1, titulo);
               stmt.setString(2, descricao);
               stmt.setString(3, categoria);
               stmt.setInt(4, id);

               int linhasEditadas = stmt.executeUpdate();
               if (linhasEditadas > 0) {
                  System.out.println("Tarefa editada com sucesso");
               } else {
                  System.out.println("Nenhuma tarefa encontrada com o id especificado.");
               }
            } catch (SQLException e) {
               System.out.println("Erro ao tentar EDITAR no banco de dados: " + e.getMessage());
            }
         }
      }
   }

   public static void excluirTarefa(int id) throws SQLException {
      try (Connection connection = DB.getConnection()) {
         if (connection != null) {
            String query = "DELETE from tarefa WHERE id = ?";
            try {
               PreparedStatement stmt = connection.prepareStatement(query);
               stmt.setInt(1, id);

               int linhasExcluidas = stmt.executeUpdate();
               if (linhasExcluidas > 0) {
                  System.out.println("Tarefa excluida com sucesso!");
               } else {
                  System.out.println("Nenhuma tarefa encontrada com o id especificado.");
               }
            } catch (SQLException e) {
               System.out.println("Erro ao tentar EXCLUIR no banco de dados: " + e.getMessage());
            }
         }
      }
   }

   public static void marcar(int id) throws SQLException {
      try (Connection connection = DB.getConnection()) {
         if (connection != null) {
            String querySelect = "SELECT status FROM tarefa WHERE id = ?";
            String queryUpdate = "UPDATE tarefa SET status = ? WHERE id = ?";
            try(PreparedStatement stmtSelect = connection.prepareStatement(querySelect)) {
               stmtSelect.setInt(1, id);
               ResultSet rs = stmtSelect.executeQuery();

               boolean status = false;
               if (rs.next()) {
                  status = rs.getBoolean("status");
               }
               status = !status;

               PreparedStatement stmtUpdate = connection.prepareStatement(queryUpdate);
               stmtUpdate.setBoolean(1, status);
               stmtUpdate.setInt(2, id);
               stmtUpdate.executeUpdate();

               System.out.println("Status da tarefa atualizado com sucesso.");
            }catch (SQLException e){
               System.out.println("Erro ao tentar ALTERAR STATUS no banco de dados: " +e.getMessage());
            }
         }
      }
   }

   public static void filtrarStatus(boolean status) throws SQLException {
      try (Connection connection = DB.getConnection()) {
         if (connection != null) {
            List<Tarefa> tarefas = new ArrayList<>();
            String query = "SELECT * FROM tarefa WHERE status = ?";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setBoolean(1,status);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
               tarefas.add(new Tarefa(
                       rs.getInt("id"), rs.getString("titulo"),
                       rs.getString("descricao"), rs.getString("categoria"),
                       rs.getBoolean("status")));
            }
            for (Tarefa tarefa : tarefas) {
               System.out.println(tarefa.toString());
            }
         }
      }
   }

   public static void filtrarCategoria(String categoria) throws SQLException {
      try (Connection connection = DB.getConnection()) {
         if (connection != null) {
            List<Tarefa> tarefas = new ArrayList<>();
            String query = "SELECT * FROM tarefa WHERE categoria = ?";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,categoria);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
               tarefas.add(new Tarefa(
                       rs.getInt("id"), rs.getString("titulo"),
                       rs.getString("descricao"), rs.getString("categoria"),
                       rs.getBoolean("status")));
            }
            for (Tarefa tarefa : tarefas) {
               System.out.println(tarefa.toString());
            }
         }
      }
   }
}