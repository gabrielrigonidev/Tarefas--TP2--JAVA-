package view;
import database.DB;
import controller.TarefaDAO;
import model.Tarefa;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      try (var connection = DB.getConnection()) {
         if (connection != null) {
            System.out.println("Conectado!");
            TarefaDAO.criarTabela();
            boolean menu = true;
            while (menu) {
               System.out.println("""
                    ---LISTA DE TAREFAS - FATEC---
                       1 - Adicionar tarefas
                       2 - Exibir tarefas
                       3 - Editar tarefas
                       4 - Excluir tarefas
                       5 - Alterar Status
                       6 - SAIR
                    """);
               int escolha = scan.nextInt();
               scan.nextLine();

               switch (escolha) {
                  case 1:
                     System.out.print("Nome da tarefa:");
                     String nome = scan.nextLine();
                     System.out.print("Descrição da tarefa:");
                     String descricao = scan.nextLine();
                     System.out.print("Categoria da tarefa:");
                     String categoria = scan.nextLine();
                     TarefaDAO.inserirTarefa(nome, descricao, categoria);
                     break;

                  case 2:
                     TarefaDAO.selectAll();
                     boolean select = true;
                     while (select) {
                        System.out.println("Digite: ");
                        System.out.println("1 - Exibir por Status ");
                        System.out.println("2 - Exibir por Categoria");
                        System.out.println("3 - VOLTAR");
                        int filtro = scan.nextInt();
                        scan.nextLine();
                        switch (filtro) {
                           case 1:
                              System.out.println("Ver tarefas Concluidas ou Pendentes? p/c");
                              String resp = scan.nextLine();
                              if (resp.equals("p")) {
                                 boolean status = false;
                                 TarefaDAO.filtrarStatus(status);
                              } else {
                                 boolean status = true;
                                 TarefaDAO.filtrarStatus(status);
                              }
                              break;
                           case 2:
                              System.out.println("Qual categoria deseja exbir?");
                              String categoriaEscolha = scan.nextLine();
                              TarefaDAO.filtrarCategoria(categoriaEscolha);
                              break;
                           case 3:
                              select = false;
                        }
                     }
                     break;

                  case 3:
                     TarefaDAO.selectAll();
                     System.out.print("Digite o ID da tarefa que deseja editar: ");
                     int idbusc = scan.nextInt();
                     scan.nextLine();
                     System.out.print("Nome da tarefa:");
                     String n_nome = scan.nextLine();
                     System.out.print("Descrição da tarefa:");
                     String n_descricao = scan.nextLine();
                     System.out.print("Categoria da tarefa:");
                     String n_categoria = scan.nextLine();
                     TarefaDAO.editarTarefa(n_nome, n_descricao, n_categoria, idbusc);
                     break;

                  case 4:
                     TarefaDAO.selectAll();
                     System.out.print("Digite o ID da tarefa que deseja Excluir: ");
                     int idExc = scan.nextInt();
                     scan.nextLine();
                     TarefaDAO.excluirTarefa(idExc);
                     break;

                  case 5:
                     TarefaDAO.selectAll();
                     System.out.print("Digite o ID da tarefa marcar: ");
                     int idMarc = scan.nextInt();
                     scan.nextLine();
                     TarefaDAO.marcar(idMarc);
                     break;
                  case 6:
                     menu = false;
               }
            }
         }
      } catch (SQLException e) {
         throw new RuntimeException(e);
      }
   }
}