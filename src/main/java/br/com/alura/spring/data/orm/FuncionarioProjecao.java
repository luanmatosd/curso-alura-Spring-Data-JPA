package br.com.alura.spring.data.orm;
//Interface criada para projetar apenas esses três dados na consulta "findFuncionarioSalario"
//da Interface FuncionarioRepository
public interface FuncionarioProjecao {
    Integer getId();
    String getNome();
    Double getSalario();
}
