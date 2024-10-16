package br.edu.fatecpg.products.view;
import br.edu.fatecpg.products.service.APIService;
import br.edu.fatecpg.products.service.ConverteDado;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {
        String menu = """
                ----------------------------
                CATÁLOGO DE PRODUTOS
                1 - Buscar por ÍMPERDÍVEIS
                2 - Buscar por PROMOÇÃO
                0 - Sair
                -----------------------------""";
        while (true) {
            System.out.println(menu);
            int escolha = scan.nextInt();

            switch (escolha) {
                case 1 -> imperdivel();
                case 2 -> promocao();
                case 0 -> {
                    System.out.println("Saindo...");
                    scan.close();
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void imperdivel() throws IOException, InterruptedException {
        ConverteDado conversor = new ConverteDado();
        String json = APIService.obterDado();
        JsonNode jsonNode = conversor.obterDado(json);
        List<JsonNode> jsonList = new ArrayList<>();
        jsonNode.forEach(jsonList::add);

        List<String> productNameList = jsonList.stream()
                .filter(node -> node.get("price").asDouble() < 30)
                .map(node -> node.get("title").asText())
                .toList();
        productNameList.forEach(System.out::println);
    }

    public static void promocao() throws IOException, InterruptedException {
        System.out.println("Digite um valor para filtrar: ");
        double preco = scan.nextDouble();

        ConverteDado conversor = new ConverteDado();
        String json = APIService.obterDado();
        JsonNode jsonNode = conversor.obterDado(json);
        List<JsonNode> jsonList = new ArrayList<>();
        jsonNode.forEach(jsonList::add);

        List<String> productNameList = jsonList.stream()
                .filter(node -> node.get("price").asDouble() < preco)
                .map(node -> node.get("title").asText().toUpperCase())
                .toList();
        productNameList.forEach(System.out::println);
    }
}