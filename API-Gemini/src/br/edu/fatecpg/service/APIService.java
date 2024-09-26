package br.edu.fatecpg.service;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class APIService {
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=AIzaSyA7Pl3mDlNuXOUT3il4BpUpmISyRLzXQZE";
    private static final Pattern ANSWER_PATTERN = Pattern.compile("\"text\"\\s*:\\s*\"([^\"]+)\"");

    public static String ask(String pergunta) throws IOException, InterruptedException {
        String jsonRequest = generateJsonRequest(pergunta);
        String jsonAnswer = sendRequest(jsonRequest);
        return extractAnswer(jsonAnswer);
    }

    private static String generateJsonRequest(String question) {
        String formatedPrompt = "Question: " + question;
        return "{\"contents\":[{\"parts\":[{\"text\":\"" + formatedPrompt + "\"}]}]}";
    }

    private static String sendRequest(String jsonRequest)throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    private static String extractAnswer(String JsonAnswer) {
        StringBuilder answer = new StringBuilder();
        for(String line : JsonAnswer.lines().toList()){
            Matcher matcher = ANSWER_PATTERN.matcher(line);
            if(matcher.find()){
                answer.append(matcher.group(1)).append(" ");
            }
        }
        return answer.toString().trim();
    }

    public static String gerarResumo(List<String> respostas) {
        StringBuilder resumo = new StringBuilder("Resumo das Respostas:\n");
        for (String resposta : respostas) {
            resumo.append("Respostas: ").append(resposta).append("\n");
        }
        return resumo.toString().trim();
    }

    public static void gravarResumoEmArquivo(String resumo) {
        try (FileWriter escrita = new FileWriter("resumo.md", false)) {
            escrita.write(resumo + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}