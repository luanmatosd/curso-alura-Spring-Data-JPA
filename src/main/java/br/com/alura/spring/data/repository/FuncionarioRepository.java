package br.com.alura.spring.data.repository;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

//PagingAndSortingRepository - Paginação e Ordenação de resultados
//JpaSpecificationExecutor - Usando consultas dinâmicas
@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>, JpaSpecificationExecutor<Funcionario> {

    //Usando uma Query Creation invés de JPQL
    List<Funcionario> findByNome(String nome);

    //Usando JPQL
    @Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.salario >= :salario AND f.dataContratacao = :dataContratacao") //f é um alias para Funcionario
    List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate dataContratacao);

    //Usando Query Nativa
    @Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :data", nativeQuery = true)
    List<Funcionario> findDataContratacaoMaior(LocalDate data);

    //Utilizando o conceito de Projeção
    @Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios F", nativeQuery = true)
    List<FuncionarioProjecao> findFuncionarioSalario();
}
