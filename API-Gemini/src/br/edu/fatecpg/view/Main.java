package br.edu.fatecpg.view;
import br.edu.fatecpg.service.APIService;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String menu = """
                1 - Ask
                0 - Exit
                """;
        while (true) {
            System.out.println(menu);
            int resp = scan.nextInt();
            if (resp == 1) {
                System.out.println("Ask something: ");
                scan.nextLine();
                String question = scan.nextLine();
                try {
                    String answer = APIService.ask(question);
                    System.out.println(answer);
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
    }
}