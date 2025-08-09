package com.c4id3n.estoque_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

// Normalmente vai ser recebido dados do cliente ( Front-end ) em requisições HTTP, por exemplo, para criar um novo produto.
// Estes dados chegam geralmente em formato JSON.
// É importante validar esses dados antes de tentar salvar no banco de dados, para garantir que estão corretos e completos.
// A classe "CreateProductRequest" é uma classe DTO ( Data Transfer Object ) usada para representar esses dados de entrada.
public class CreateProductRequest {
    
    // @NotBlank: Garante que o nome do produto não seja nulo, vazio ou apenas espaços em branco.
    @NotBlank
    public String nome;

    // @Min(0): Garante que a quantidade seja um número inteiro maior ou igual a zero.
    @Min(0)
    public int quantidade;

    // @NotNull: Garante que o preço não seja nulo.
    @NotNull
    public BigDecimal preco;
}
