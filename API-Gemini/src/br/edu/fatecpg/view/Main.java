package br.edu.fatecpg.view;
import br.edu.fatecpg.service.APIService;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<String> respostas = new ArrayList<>();
        String menu = """
                1 - Ask
                0 - Exit
                """;

        while (true) {
            System.out.println(menu);
            int resp = scan.nextInt();
            scan.nextLine();

            if (resp == 1) {
                System.out.println("Ask something: ");
                String question = scan.nextLine();

                try {
                    String answer = APIService.ask(question);
                    respostas.add(answer);
                } catch (IOException e) {
                    System.out.println("Error connecting to API: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("An unexpected error occurred: " + e.getMessage());
                }
            } else {
                System.out.println("Closing...");
                break;
            }
        }
        while (respostas.size() < 3) {
            System.out.print("You need to ask at least 3 questions. Please enter another one: ");
            String question = scan.nextLine();

            try {
                String answer = APIService.ask(question);
                respostas.add(answer);
            } catch (IOException e) {
                System.out.println("Error connecting to API: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        scan.close();
        APIService.gravarResumoEmArquivo(APIService.gerarResumo(respostas));
        System.out.println("Summary saved to 'resumo.md'.");
    }
}