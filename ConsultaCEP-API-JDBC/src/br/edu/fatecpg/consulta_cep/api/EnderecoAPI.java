package br.edu.fatecpg.consulta_cep.api;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class EnderecoAPI {
   private final HttpClient httpClient;

   public EnderecoAPI() {
      this.httpClient = HttpClient.newHttpClient();
   }

   private String consomeHTTPRequest(String url) throws IOException, InterruptedException {
      HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(url))
              .build();
      HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
      return response.body();
   }

   public String consomeApi(String cep) throws IOException, InterruptedException {
      String url = "https://viacep.com.br/ws/" + cep + "/json";
      return consomeHTTPRequest(url);
   }

   public String consumirFusoHorario() throws IOException, InterruptedException {
      String url = "https://worldtimeapi.org/api/timezone/America/Sao_Paulo";
      return consomeHTTPRequest(url);
   }
}