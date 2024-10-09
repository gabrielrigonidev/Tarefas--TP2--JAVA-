package br.edu.fatecpg.email.main;
import br.edu.fatecpg.email.service.APIService;
import br.edu.fatecpg.email.service.ConverteDado;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ConverteDado conversor = new ConverteDado();
        String json = APIService.obterDado();
        JsonNode email = conversor.obterDado(json);

        // Utilizando Lambdas
        email.forEach(node -> System.out.println(node.get("email").asText()));

        /*  JsonNode não possui um método .stream diretamente
        Então, nesse código foi preciso converter ele usando StreamSupport */
        StreamSupport.stream(email.spliterator(), false)
                .map(node -> node.get("email").asText())
                .forEach(System.out::println);
    }
}