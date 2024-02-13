package br.com.alura.spring.data;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CRUDCargoService cargoService;
	private final CRUDUnidadeDeTrabalhoService unidadeDeTrabalhoService;
	private final CRUDFuncionarioService funcionarioService;
	private final RelatoriosService relatoriosService;
	private final RelatorioDinamicoService relatorioDinamicoService;
	private boolean system = true;

    public SpringDataApplication(CRUDCargoService cargoService, CRUDUnidadeDeTrabalhoService unidadeDeTrabalhoService, CRUDFuncionarioService funcionarioService, RelatoriosService relatoriosService, RelatorioDinamicoService relatorioDinamicoService) {
        this.cargoService = cargoService;
        this.unidadeDeTrabalhoService = unidadeDeTrabalhoService;
        this.funcionarioService = funcionarioService;
        this.relatoriosService = relatoriosService;
        this.relatorioDinamicoService = relatorioDinamicoService;
    }


    public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	//Método executado logo após a finalização do Main acima
	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (system) {
			System.out.println("Qual função deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionário");
			System.out.println("3 - Unidade");
			System.out.println("4 - Relatório");
			System.out.println("5 - Relatório Dinâmico");

			Integer function = scanner.nextInt();

			switch (function) {
				case 1:
					cargoService.inicial(scanner);
					break;
				case 2:
					funcionarioService.inicial(scanner);
					break;
				case 3:
					unidadeDeTrabalhoService.inicial(scanner);
					break;
				case 4:
					relatoriosService.inicial(scanner);
					break;
				case 5:
					relatorioDinamicoService.inicial(scanner);
					break;
				default:
					System.out.println("Finalizando");
					system = false;
					break;
			}
		}
	}
}
