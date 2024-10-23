package br.edu.fatecpg.jpa_cep.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIService {
    private String consomeHTTPRequest(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String consomeApi(String cep) throws IOException, InterruptedException {
        String url = "https://viacep.com.br/ws/" + cep + "/json";
        return consomeHTTPRequest(url);
    }
}
