package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeDeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeDeTrabalhoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CRUDFuncionarioService {

    private boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;
    private final UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository;

    public CRUDFuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository, UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
        this.unidadeDeTrabalhoRepository = unidadeDeTrabalhoRepository;
    }

    public void inicial(Scanner scanner) {

        while (system) {
            System.out.println("1 - Salvar funcionário");
            System.out.println("2 - Atualizar funcionário");
            System.out.println("3 - Lista funcionários");
            System.out.println("4 - Deleta funcionário");

            int opcaoEscolhida = scanner.nextInt();

            switch (opcaoEscolhida) {
                case 1:
                    salvaFuncionario(scanner);
                    break;
                case 2:
                    atualizaFuncionario(scanner);
                    break;
                case 3:
                    buscaFuncionarios(scanner);
                    break;
                case 4:
                    removerFuncionario(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    //Método para salvar um funcionário
    private void salvaFuncionario(Scanner scanner) {
        System.out.println("Digite o nome");
        String nome = scanner.next();

        System.out.println("Digite o cpf");
        String cpf = scanner.next();

        System.out.println("Digite o salario");
        Double salario = scanner.nextDouble();

        System.out.println("Digite a data de contracao");
        String dataContratacao = scanner.next();

        System.out.println("Digite o cargoId");
        Integer cargoId = scanner.nextInt();

        List<UnidadeDeTrabalho> unidades = unidade(scanner);

        Funcionario funcionario = new Funcionario();

        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));

        Optional<Cargo> cargo = cargoRepository.findById(cargoId);

        funcionario.setCargo(cargo.get());
        funcionario.setUnidadeTrabalhos(unidades);

        funcionarioRepository.save(funcionario);
        System.out.println("Salvo");
    }

    //Método listas as unidades de trabalho
    private List<UnidadeDeTrabalho> unidade(Scanner scanner) {
        Boolean isTrue = true;
        List<UnidadeDeTrabalho> unidades = new ArrayList<>();

        while (isTrue) {
            System.out.println("Digite o unidadeId (Para sair digite 0)");
            Integer unidadeId = scanner.nextInt();

            if(unidadeId != 0) {
                Optional<UnidadeDeTrabalho> unidade = unidadeDeTrabalhoRepository.findById(unidadeId);
                unidades.add(unidade.get());
            } else {
                isTrue = false;
            }
        }

        return unidades;
    }

    //Método para atualizar um funcionário
    private void atualizaFuncionario(Scanner scanner) {
        System.out.println("Digite o id");
        Integer id = scanner.nextInt();

        System.out.println("Digite o nome");
        String nome = scanner.next();

        System.out.println("Digite o cpf");
        String cpf = scanner.next();

        System.out.println("Digite o salario");
        Double salario = scanner.nextDouble();

        System.out.println("Digite a data de contracao");
        String dataContratacao = scanner.next();

        System.out.println("Digite o cargoId");
        Integer cargoId = scanner.nextInt();

        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargo(cargo.get());

        funcionarioRepository.save(funcionario);
        System.out.println("Alterado");
    }

    //Método para buscar os funcionários através de paginação
    private void buscaFuncionarios(Scanner scanner) {
        System.out.println("Qual página deseja visualizar:");
        Integer page = scanner.nextInt();

        //Realizando a paginação de resultados e ordenação pelo nome de A para Z
        Pageable pageable = PageRequest.of(page, 3, Sort.by(Sort.Direction.ASC, "nome"));

        Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);

        System.out.println(funcionarios);
        System.out.println("Página atual: " + funcionarios.getNumber());
        System.out.println("Total elemento: " + funcionarios.getTotalElements());
        funcionarios.forEach(funcionario -> System.out.println(funcionario));
    }

    //Método para remover um funcionário
    private void removerFuncionario(Scanner scanner) {
        System.out.println("Digite o ID do funcionario a ser removido:");
        int id = scanner.nextInt();

        funcionarioRepository.deleteById(id);

        System.out.println("Deletado!");
    }
}


