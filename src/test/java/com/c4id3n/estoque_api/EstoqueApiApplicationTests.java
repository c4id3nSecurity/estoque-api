package com.c4id3n.estoque_api;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.c4id3n.estoque_api.model.Produto;
import com.c4id3n.estoque_api.repository.ProdutoRepository;

/*
 * Por que deste arquivo?
 * Este arquivo verifica a aplicação carrega corretamente.
 */

/*
 * "@SpringBootTest" é uma anotação que indica que a classe é um teste de integração que carrega o contexto completo da aplicação Spring Boot.
 * Isso permite que você teste a aplicação como um todo, incluindo a configuração do Spring, os beans, e outros componentes necessários.
 */
@SpringBootTest
class EstoqueApiApplicationTests {

	/*
	 * Injeta automaticamente a instância do repositório ProdutoRepository.
	 */
	@Autowired
	private ProdutoRepository produtoRepository;

	@Test
	void contextLoads() {
		List<Produto> produtos = produtoRepository.findAll(); // Busca todos os produtos do repositório e verifica se a lista não está vazia.
		assertFalse(produtos.isEmpty()); // Verifica se a lista de produtos não está vazia, garantindo que a aplicação está carregando corretamente os dados.
		produtos.forEach(System.out::println); // Imprime cada produto encontrado no console para visualização.
	}

}
