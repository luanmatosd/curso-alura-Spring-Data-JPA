package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CRUDCargoService {
    private boolean system = true;
    private final CargoRepository cargoRepository;

    public CRUDCargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public void inicial(Scanner scanner) {

        while (system) {
            System.out.println("1 - Salvar cargo");
            System.out.println("2 - Atualizar cargo");
            System.out.println("3 - Lista cargos");
            System.out.println("4 - Deleta cargo");

            int opcaoEscolhida = scanner.nextInt();

            switch (opcaoEscolhida) {
                case 1:
                    salvaCargo(scanner);
                    break;
                case 2:
                    atualizaCargo(scanner);
                    break;
                case 3:
                    buscaCargos();
                    break;
                case 4:
                    removerCargo(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    //Método de salvar um cargo
    private void salvaCargo(Scanner scanner) {
        System.out.println("Digite a descrição do cargo:");
        String desc = scanner.next();

        Cargo cargo = new Cargo();
        cargo.setDescricao(desc);
        cargoRepository.save(cargo);

        System.out.println("Salvo!");
    }

    //Método de atualizar um cargo
    private void atualizaCargo(Scanner scanner) {
        System.out.println("Digite o ID do cargo a ser atualizado:");
        int id = scanner.nextInt();

        System.out.println("Digite a descrição do cargo:");
        String desc = scanner.next();

        Optional<Cargo> optional = cargoRepository.findById(id);
        Cargo cargo = optional.get();

        cargo.setDescricao(desc);
        cargoRepository.save(cargo);

        System.out.println("Atualizado!");
    }

    //Método de listar todos cargos
    private void buscaCargos() {
        Iterable<Cargo> cargos = cargoRepository.findAll();
        cargos.forEach(cargo -> System.out.println(cargo));
    }

    //Método de remover um cargo
    private void removerCargo(Scanner scanner) {
        System.out.println("Digite o ID do cargo a ser removido:");
        int id = scanner.nextInt();

        cargoRepository.deleteById(id);

        System.out.println("Deletado!");
    }
}
