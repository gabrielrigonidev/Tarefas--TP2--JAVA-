package br.edu.fatecpg.tp2.API_IMDb_Spring;

import br.edu.fatecpg.tp2.API_IMDb_Spring.model.Filme;
import br.edu.fatecpg.tp2.API_IMDb_Spring.model.Serie;
import br.edu.fatecpg.tp2.API_IMDb_Spring.service.APIService;
import br.edu.fatecpg.tp2.API_IMDb_Spring.service.ConverteDado;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ApiImDbSpringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiImDbSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scan = new Scanner(System.in);
		ConverteDado convesor = new ConverteDado();

		while (true) {
			System.out.println("""
					BUSCA DO IMDB
					1 - Série
					2 - Filme
					0 - Sair
					""");
			int opcao;
			opcao = scan.nextInt();
			scan.nextLine();

			switch (opcao) {
				case 0:
					System.out.println("Saindo...");
					return;
				case 1:
					System.out.println("Digite o nome da série: ");
					String nomeSerie = scan.nextLine();

					String jsonSerie = APIService.obterDado(nomeSerie);
					Serie serie = convesor.obterDado(jsonSerie, Serie.class);

					if(serie.tipo().equals("series")){
						System.out.println(serie);
					} else {
						System.out.println("Você pesquisou por um Filme, tente a opção 2!");
					}
					break;
				case 2:
					System.out.println("Digite o nome do Filme: ");
					String nomeFilme = scan.nextLine();

					String jsonFilme = APIService.obterDado(nomeFilme);
					Filme filme = convesor.obterDado(jsonFilme, Filme.class);

					if(filme.tipo().equals("movie")){
						System.out.println(filme);
					} else {
						System.out.println("Você pesquisou por uma Série, tente a opção 1!");
					}
					break;
				default:
					System.out.println("Opção inválida! Tente novamente.");
					break;
			}
		}
	}
}
