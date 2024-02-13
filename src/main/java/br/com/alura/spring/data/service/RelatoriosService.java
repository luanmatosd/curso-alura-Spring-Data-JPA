package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeDeTrabalhoRepository;
import br.com.alura.spring.data.specification.SpecificationFuncionario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;
    private final UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository;

    public RelatoriosService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository, UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
        this.unidadeDeTrabalhoRepository = unidadeDeTrabalhoRepository;
    }

    public void inicial(Scanner scanner) {

        while (system) {
            System.out.println("1 - Lista funcionário pelo nome: ");
            System.out.println("2 - Lista funcionário pelo nome, data de contratação e salário:");
            System.out.println("3 - Lista funcionário pelo data de contratação acima de:");
            System.out.println("4 - Lista todos os funcionários:");

            int opcaoEscolhida = scanner.nextInt();

            switch (opcaoEscolhida) {
                case 1:
                    listaNomeFuncionario(scanner);
                    break;
                case 2:
                    buscaFuncionarioSalarioMaiorData(scanner);
                    break;
                case 3:
                    buscaFuncionarioDataMaior(scanner);
                    break;
                case 4:
                    buscaFuncionarioSalario();
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void listaNomeFuncionario(Scanner scanner) {
        System.out.println("Digite o nome do funcionário:");
        String nome = scanner.next();

        List<Funcionario> funcionarioList = funcionarioRepository.findByNome(nome);
        funcionarioList.forEach(System.out::println);
    }

    private void buscaFuncionarioSalarioMaiorData(Scanner scanner){
        System.out.println("Digite o nome do funcionário:");
        String nome = scanner.next();

        System.out.println("Digite a data de contratação:");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter); //Convertendo de String para LocalDate

        System.out.println("Digite o salário:");
        Double salario = scanner.nextDouble();

        List<Funcionario> funcionarioList = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
        funcionarioList.forEach(System.out::println);
    }

    private void buscaFuncionarioDataMaior(Scanner scanner){
        System.out.println("Digite a data de contratação:");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        List<Funcionario> funcionarioList = funcionarioRepository.findDataContratacaoMaior(localDate);
        funcionarioList.forEach(System.out::println);
    }

    private void buscaFuncionarioSalario(){
        List<FuncionarioProjecao> projecaoList = funcionarioRepository.findFuncionarioSalario();
        projecaoList.forEach(funcionarioProjecao -> System.out.println("Id: " + funcionarioProjecao.getId()
        + " | Nome: " + funcionarioProjecao.getNome() + " | Salário: " + funcionarioProjecao.getSalario()));
    }
}
