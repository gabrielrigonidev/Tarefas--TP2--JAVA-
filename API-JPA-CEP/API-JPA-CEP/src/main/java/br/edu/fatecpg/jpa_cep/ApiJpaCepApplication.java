package br.edu.fatecpg.jpa_cep;
import br.edu.fatecpg.jpa_cep.model.Cep;
import br.edu.fatecpg.jpa_cep.repository.CepRepository;
import br.edu.fatecpg.jpa_cep.service.APIService;
import br.edu.fatecpg.jpa_cep.service.ConverteDado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ApiJpaCepApplication implements CommandLineRunner {

	@Autowired
	private CepRepository repository;
	private static final Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		SpringApplication.run(ApiJpaCepApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String menu = """
			----------------------------
			CATÁLOGO DE CEP
			1 -	Adcionar CEP
			2 - Buscar por CEP
			0 - Sair
			-----------------------------""";
		while (true) {
			System.out.println(menu);
			int escolha = scan.nextInt();

			switch (escolha) {
				case 1 -> adicionarCEP();
				case 2 -> buscarCEP();
				case 0 -> {
					System.out.println("Saindo...");
					scan.close();
					return;
				}
				default -> System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}

	public void adicionarCEP() throws InterruptedException, IOException {
		APIService service = new APIService();
		ConverteDado conversor = new ConverteDado();

		System.out.println("Digite um CEP: ");
		String cepDigitado = scan.next();

		String json = service.consomeApi(cepDigitado);
		System.out.println(json);
		Cep cep = conversor.obterDado(json, Cep.class);
		System.out.println(cep);

		if (repository.existsByCep(cep.getCep())) {
			System.out.println("Erro: O CEP já está cadastrado");
			return;
		}
		try {
			repository.save(cep);
			System.out.println("CEP salvo com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro ao salvar o CEP: " + e.getMessage());
		}
	}

	public void buscarCEP() {
		List<Cep> listaCeps = repository.findAll();
		listaCeps.forEach(System.out::println);
	}
}