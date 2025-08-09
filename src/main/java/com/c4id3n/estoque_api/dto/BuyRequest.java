package com.c4id3n.estoque_api.dto;

import jakarta.validation.constraints.Min;

// Por que este arquivo é preciso?
// Esse arquivo define um DTO chamado "BuyRequest" usado para receber dados de requisições de compra.

public class BuyRequest {

    // Declara o campo "quantidade", que representa a quantidade de itens a serem comprados.
    // A anotação @Min(1) garante que a quantidade seja pelo menos 1.
    // Isso evita que sejam feitas requisições de compra com quantidades inválidas, como zero ou números negativos.
    @Min(1)
    public int quantidade;
}
