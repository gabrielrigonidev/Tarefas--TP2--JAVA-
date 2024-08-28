package br.edu.fatecpg.consulta_cep.view;
import br.edu.fatecpg.consulta_cep.api.EnderecoAPI;
import br.edu.fatecpg.consulta_cep.controller.EnderecoDTO;
import br.edu.fatecpg.consulta_cep.database.BD;
import br.edu.fatecpg.consulta_cep.model.Endereco;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
   public static void main(String[] args) throws IOException, InterruptedException, SQLException {
      Scanner scan = new Scanner(System.in);
      Gson gson = new Gson();

      try (var connection = BD.getConnection()) {
         while(true){
            String menu = """
                    ---- PROGRAMA CONSULTA-CEP 2024 ----
                    1 - Consultar VIA CEP
                    2 - Listar CEPs no Banco de Dados
                    """;
            System.out.println(menu);
            int escolha = scan.nextInt();

            switch(escolha){
               case 1:
                  System.out.println("Digite um CEP: ");
                  String cepDigitado = scan.next();
                  EnderecoAPI api = new EnderecoAPI();
                  String json = api.consomeApi(cepDigitado);
                  Endereco endereco = gson.fromJson(json, Endereco.class);
                  EnderecoDTO.inserirEndereco(endereco);
               case 2:
                  EnderecoDTO.selecionarEndereco();
            }
         }
      }
   }
}