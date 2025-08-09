package com.c4id3n.estoque_api.config;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.c4id3n.estoque_api.model.Produto;
import com.c4id3n.estoque_api.repository.ProdutoRepository;

/*
 * Por que deste arquivo?
 * Este arquivo é uma classe de configuração do Spring Boot responsável por inicializar o banco de dados
 * com alguns dados padrão quando a aplicação é iniciada. Isso é útil para testes, demos ou para garantir
 * que o banco de dados sempre tenha alguns dados iniciais disponíveis.
 */

/*
 * "@Configuration" indica que esta classe configura beans do Spring( Classe de configuração ).
 */
@Configuration
public class DataInitializer {

    /*
     * "@Bean" indica que este método deve ser chamado pelo Spring para inicializar o banco de dados.
     * "CommandLineRunner" é uma interface que permite executar código após a aplicação ser iniciada.
     * "ProdutoRepository" é um repositório que permite interagir com a tabela de produtos no banco de dados.
     */
    @Bean
    public CommandLineRunner initDatabase(ProdutoRepository repository){
        return args -> {
            // Verifica se a tabela de produtos está vazia
            if (repository.count() == 0) {
                // Se estiver vazia, adiciona alguns produtos iniciais:
                repository.save(new Produto("Produto A", 10, BigDecimal.valueOf(19.99)));
                repository.save(new Produto("Produto B", 5, BigDecimal.valueOf(29.99)));
                repository.save(new Produto("Produto C", 20, BigDecimal.valueOf(9.99)));
            }
        };
    }
}
