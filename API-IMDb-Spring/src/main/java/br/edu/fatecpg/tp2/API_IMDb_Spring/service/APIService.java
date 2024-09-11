package br.edu.fatecpg.tp2.API_IMDb_Spring.service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIService {
   public static String obterDado(String nome) throws IOException, InterruptedException {
      String nomeFormatado = nome.replace(" ", "+").toLowerCase().trim();

      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create("https://www.omdbapi.com/?t=" + nomeFormatado + "&apikey=212353bf"))
              .build();
      HttpResponse<String> response = client
              .send(request, HttpResponse.BodyHandlers.ofString());
      return response.body();
   }
}
