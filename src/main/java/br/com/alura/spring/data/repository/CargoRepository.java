package br.com.alura.spring.data.repository;

import br.com.alura.spring.data.orm.Cargo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//Invés de criar na mão os comandos para operar no Banco de dados com JPA puro
//Utiliza-se as Interfaces do Spring Data que vem com todos esses comandos, simplificando a vida do DEV
@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer> {
}
