package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.UnidadeDeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.UnidadeDeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CRUDUnidadeDeTrabalhoService {

    private boolean system = true;
    private final UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository;

    public CRUDUnidadeDeTrabalhoService(UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository) {
        this.unidadeDeTrabalhoRepository = unidadeDeTrabalhoRepository;
    }

    public void inicial(Scanner scanner) {

        while (system) {
            System.out.println("1 - Salvar Unidade de Trabalho");
            System.out.println("2 - Atualizar Unidade de Trabalho");
            System.out.println("3 - Lista Unidades de Trabalhos");
            System.out.println("4 - Deleta Unidade de Trabalho");

            int opcaoEscolhida = scanner.nextInt();

            switch (opcaoEscolhida) {
                case 1:
                    salvaUnidade(scanner);
                    break;
                case 2:
                    atualizaUnidade(scanner);
                    break;
                case 3:
                    buscaUnidades();
                    break;
                case 4:
                    removeUnidade(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    //Método de salvar uma unidade
    private void salvaUnidade(Scanner scanner) {
        System.out.println("Digite a descrição da unidade:");
        String desc = scanner.next();

        scanner.nextLine();

        System.out.println("Digite o endereço da unidade:");
        String endereco = scanner.nextLine();

        UnidadeDeTrabalho unidade = new UnidadeDeTrabalho();
        unidade.setDescricao(desc);
        unidade.setEndereco(endereco);
        unidadeDeTrabalhoRepository.save(unidade);

        System.out.println("Salvo!");
    }

    //Método de atualizar uma unidade
    private void atualizaUnidade(Scanner scanner) {
        System.out.println("Digite o ID da unidade a ser atualizada:");
        int id = scanner.nextInt();

        System.out.println("Digite a descrição da unidade:");
        String desc = scanner.next();

        scanner.nextLine();

        System.out.println("Digite o endereço da unidade:");
        String endereco = scanner.nextLine();

        Optional<UnidadeDeTrabalho> optional = unidadeDeTrabalhoRepository.findById(id);
        UnidadeDeTrabalho unidade = optional.get();

        unidade.setDescricao(desc);
        unidade.setEndereco(endereco);
        unidadeDeTrabalhoRepository.save(unidade);

        System.out.println("Atualizado!");
    }

    //Método de listar todas unidades
    private void buscaUnidades() {
        Iterable<UnidadeDeTrabalho> unidades = unidadeDeTrabalhoRepository.findAll();
        unidades.forEach(unidade -> System.out.println(unidade));
    }

    //Método de remover uma unidade
    private void removeUnidade(Scanner scanner) {
        System.out.println("Digite o ID do cargo a ser removido:");
        int id = scanner.nextInt();

        unidadeDeTrabalhoRepository.deleteById(id);

        System.out.println("Deletado!");
    }
}
