package br.edu.fatecpg.tp2.API_FIPE_Spring;
import br.edu.fatecpg.tp2.API_FIPE_Spring.model.Carro;
import br.edu.fatecpg.tp2.API_FIPE_Spring.service.APIService;
import br.edu.fatecpg.tp2.API_FIPE_Spring.service.ConverteDado;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ApiFipeSpringApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ApiFipeSpringApplication.class, args);
	}
	public static final String BASE_URL = "https://parallelum.com.br/fipe/api/v1/carros/marcas";
	@Override
	public void run(String... args) throws Exception {
		Scanner scan = new Scanner(System.in);
		ConverteDado convesor = new ConverteDado();

		while (true) {
			System.out.println("""
					BUSCA TABELA FIP
					1 - Buscar
					0 - Sair
					""");
			int opcao;
			opcao = scan.nextInt();
			scan.nextLine();

			switch (opcao) {
				case 0:

				case 1:
					String jsonMarcas = APIService.obterDado(BASE_URL);
					System.out.println(jsonMarcas);

					System.out.println("Digite o ID da Marca: ");
					int idMarca = scan.nextInt();
					String jsonMarca = APIService.obterDado(BASE_URL + "/" + idMarca + "/modelos");
					System.out.println(jsonMarca);

					System.out.println("Digite o ID do Modelo: ");
					int idModelo = scan.nextInt();
					String jsonModelo = APIService.obterDado(BASE_URL + "/" + idMarca + "/modelos" + "/" + idModelo + "/" +"anos");
					System.out.println(jsonModelo);

					System.out.println("Digite o Ano: ");
					String idAnos = scan.next();
					String jsonAno = APIService.obterDado(BASE_URL + "/" + idMarca + "/modelos" + "/" + idModelo + "/" + "anos" + "/" + idAnos);
					Carro carro = convesor.obterDado(jsonAno, Carro.class);
					System.out.println(carro);
					break;
				default:
					System.out.println("Opção inválida! Tente novamente.");
					break;
			}
		}
	}
}