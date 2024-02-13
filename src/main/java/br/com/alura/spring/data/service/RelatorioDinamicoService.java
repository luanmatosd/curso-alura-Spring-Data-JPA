package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specification.SpecificationFuncionario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

//Classe criada usar a regra de negócio das consultas dinâmicas
@Service
public class RelatorioDinamicoService {

    private final FuncionarioRepository funcionarioRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RelatorioDinamicoService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial (Scanner scanner){
        System.out.println("Digite o nome:");
        String nome = scanner.next();

        if (nome.equalsIgnoreCase("NULL")){
            nome = null;
        }

        System.out.println("Digite o cpf:");
        String cpf = scanner.next();

        if (cpf.equalsIgnoreCase("NULL")){
            cpf = null;
        }

        System.out.println("Digite o salário:");
        Double salario = scanner.nextDouble();

        if (salario == 0){
            salario = null;
        }

        System.out.println("Digite a data de contratação:");
        String data = scanner.next();

        LocalDate dataContratacao;

        if (data.equalsIgnoreCase("NULL")){
            dataContratacao = null;
        }else{
            dataContratacao = LocalDate.parse(data, formatter);
        }

        List<Funcionario> funcionarioList = funcionarioRepository.findAll(Specification.where(
                SpecificationFuncionario.nome(nome))
                .or(SpecificationFuncionario.cpf(cpf))
                .or(SpecificationFuncionario.salario(salario))
                .or(SpecificationFuncionario.dataContratacao(dataContratacao)));

        funcionarioList.forEach(System.out::println);
    }
}
