package com.c4id3n.estoque_api.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Uso "BigDecimal" para valores monetários, pois evita erros de pontos flutuantes

@Entity(name = "produtos") // Basicamente o "@Entity" é para dizer ao SpringBoot que esta classe deve ser mapeada como uma tabela
@Table(name = "produtos")
public class Produto {

    // Aqui ele estamos classificando o ID para cada produto
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Aqui estamos classificando o NOME para cada produto
    @Column(nullable = false, length = 100) // O "nullable = false" diz que o nome não pode ser nulo e "length = 100" limita o tamanho do nome a 100 caracteres
    private String nome;
    
    // Aqui estamos classificando a QUANTIDADE em estoque de cada produto
    // O "nullable = false" diz que a quantidade não pode ser nula, ou seja, deve ter um valor definido.
    // O tipo "int" é usado para representar a quantidade de produtos em estoque.
    @Column(nullable = false)
    private int quantidade;

    // Aqui estamos classificando o PREÇO de cada produto 
    @Column(nullable= false, precision = 19, scale = 2)
    private BigDecimal preco;

    // Você deve estar se perguntando 'Por que ele chamou o Produto duas vezes?', e aqui vai a resposta:
    // Quando chamo o Produto vazio pela primeira vez, ele me permite criar um objeto sem inicializar todos os campos imediatamente.
    // Isso é útil quando você quer definir os valores posteriormente, usando setters.

    // Você deve ter se perguntado também: "No segundo Produto, por que ele não chamou o ID?"
    // Não é necessário pré-definir o ID; o banco de dados define automaticamente.
    public Produto(){}

    public Produto(String nome, int quantidade, BigDecimal preco){
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    // Aqui estamos lidando com métodos de acesso de cada atributo da classe( Getters e Setters )
    // - Getter -> Lê o valor ( Todos podem ler o os Getters por isso "public" )
    // - Setter -> Altera o valor ( Ninguem pode alterar os Setters por isso "void" )

    // Retorna o valor atual do ID
    public Long getId() { return id; }
    
    // Retorna o nome do produto
    public String getNome() { return nome; }

    // Altera o nome do produto.
    public void setNome(String nome) { this.nome = nome; }

    // Ler e altera a quantidade
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    // Ler e altera o preço
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

}
