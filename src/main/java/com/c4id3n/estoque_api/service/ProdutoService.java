package com.c4id3n.estoque_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.c4id3n.estoque_api.exception.InsufficientStockException;
import com.c4id3n.estoque_api.model.Produto;
import com.c4id3n.estoque_api.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

// Por que deste arquivo?
// Este arquivo é responsável por definir a lógica de negócios relacionada aos produtos.
// Ele é um intermediário entre o Controller ( Que atende as requisições HTTP ) e o Repository ( Que interage com o banco de dados ).

// O "@Service" diz ao Spring que essa classe é um componente de serviço ( parte da lógica de negócios ).
// Isso permite que o Spring gerencie a instância dessa classe e a injete onde for necessária.
// Isso é útil para manter o código organizado e seguir o padrão de injeção de dependência.
// A anotação "@Service" também permite que o Spring trate essa classe como um bean,
// o que significa que ele pode ser configurado, testado e gerenciado pelo contêsto do Spring.
@Service
public class ProdutoService {

    // Declara uma variável que guarda a referência ao repositório ( Camada que acessa o banco de dados ).
    // "final" indica que ela só pode ser atribuida uma vez ( Bom para garantir a imutabilidade ).
    private final ProdutoRepository repo;
    
    // Construtor da classe, que recebe o "ProdutoRepository" por parâmetro.
    // O Spring irá injetar automaticamente essa dependência ( Injeção de Dependência ).
    // Isso permite que o serviço utilize o repositório para realizar operações no banco de dados.
    public ProdutoService(ProdutoRepository repo) {
        this.repo = repo;
    }

    // Metodo que cria um novo produto.
    // Recebe um objeto "Produto" e chama "repo.save(p)" para salvar o produto no banco de dados.
    // Retorna o produto salvo ( que pode incluir um ID gerado pelo banco de dados ).
    public Produto create(Produto p){
        return repo.save(p);
    }

    // Metodo que lista todos os produtos.
    // Chama "repo.findAll()" para buscar todos os produtos no banco de dados.
    public List<Produto> listAll(){
        return repo.findAll();
    }

    // Método para procurar um produto pelo ID
    // "repo.findById(id)" retorna um Optinal<Produto>, que pode ou não ter valor.
    // Se não encontrar, lança uma exceção "RuntimeException" com a mensagem "Produto não encontrado".
    public Produto getById(Long id){
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    // Método que realiza a compra de um produto.
    // "@Transactional" significa que o método vai rodar dentro de uma transação no banco.
    // Ou tudo ocorre com sucesso, ou nada é salvo( Evita dados inconsistentes ).
    @Transactional
    public Produto buy(Long produtoId, int quantidade){
        // Busca o produto pelo id
        // Se não existir, lança uma exceção.
        Produto p = repo.findById(produtoId).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // Este if é declarado pois é ele que valida se a quantidade é positiva
        if (quantidade <= 0){
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        // Verifica se há estoque suficiente para a compra.
        // Se não houver, lança exceção personalizada "InsufficientStockException".
        if (p.getQuantidade() < quantidade){
            throw new InsufficientStockException("Estoque insuficiente");
        }

        // Atualiza a quantidade do produto, subtraindo a quantidade comprada.
        p.setQuantidade(p.getQuantidade() - quantidade);

        // Salva o produto atualizado no banco de dados e retorna.
        // Mesmo com o @Transactional, é uma boa prática retornar o objeto atualizado.
        return repo.save(p);
    }

    public Produto restock(Long id, int quantidade) {
        Produto p = getById(id);
        if (p == null) {
            throw new RuntimeException("Produto não encontrado");
        }

        p.setQuantidade(p.getQuantidade() + quantidade);
        return repo.save(p);
    }
}
