package br.edu.fatecpg.consulta_cep.controller;
import br.edu.fatecpg.consulta_cep.api.EnderecoAPI;
import br.edu.fatecpg.consulta_cep.database.BD;
import br.edu.fatecpg.consulta_cep.model.Endereco;
import br.edu.fatecpg.consulta_cep.model.Tempo;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDTO {
   public static void inserirEndereco(Endereco endereco) {
      try (Connection connection = BD.getConnection()) {
         if(connection != null) {
            String query = "INSERT INTO endereco(cep,rua,bairro,cidade,uf) VALUES(?,?,?,?,?)";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
               stmt.setString(1, endereco.getCep());
               stmt.setString(2, endereco.getRua());
               stmt.setString(3, endereco.getBairro());
               stmt.setString(4, endereco.getCidade());
               stmt.setString(5, endereco.getUF());
               stmt.execute();
               registrarLog(endereco);
            }
         }
      } catch (SQLException | IOException | InterruptedException e) {
         throw new RuntimeException("Erro ao inserir endereço: " + e.getMessage(), e);
      }
   }

   private static void registrarLog(Endereco endereco) throws IOException, InterruptedException {
      Gson gson = new Gson();
      EnderecoAPI api = new EnderecoAPI();

      String jsonTempo = api.consumirFusoHorario();
      Tempo tempo = gson.fromJson(jsonTempo, Tempo.class);

      try (FileWriter escrita = new FileWriter("log.txt", true)) {
         String log = endereco.toString() + tempo.tempo;
         escrita.write(log + "\n");
      }
   }

   public static void selecionarEndereco() {
      try (Connection connection = BD.getConnection()) {
         if(connection != null) {
            String querySelect = "SELECT * FROM endereco";
            try (PreparedStatement stmt = connection.prepareStatement(querySelect)) {
               ResultSet rs = stmt.executeQuery();
               List<Endereco> enderecos = new ArrayList<>();

               while (rs.next()) {
                  Endereco endereco = new Endereco();
                  endereco.setId(rs.getInt("id"));
                  endereco.setCep(rs.getString("cep"));
                  endereco.setRua(rs.getString("rua"));
                  endereco.setBairro(rs.getString("bairro"));
                  endereco.setCidade(rs.getString("cidade"));
                  endereco.setEstado(rs.getString("uf"));
                  enderecos.add(endereco);
               }
               enderecos.forEach(endereco -> System.out.println(endereco.toString()));
            }
         }
      } catch (SQLException e) {
         throw new RuntimeException("Erro ao selecionar endereços: " + e.getMessage(), e);
      }
   }
}