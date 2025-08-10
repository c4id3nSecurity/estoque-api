package com.c4id3n.estoque_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c4id3n.estoque_api.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Por que Interface?
    // A interface define um contrato para operações de acesso a dados(CRUD -> Criar, Ler, Atualizar, Deletar) para a entidade Produto.
    // Não há código nela, pois o Spring Data JPA gera a implementação em tempo de execução.
    // Isso permite que você se concentre na definição das operações sem se preocupar com a implementação
    // específica do banco de dados.
}
