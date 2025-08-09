package com.c4id3n.estoque_api.exception;

// Por que deste arquivo?
// Nesta API tem uma regra clara: Não é possível comprar mais produtos do que há em estoque.
// Se alguém tentar comprar mais do que há em estoque, deve ser lançada uma exceção para indicar que o estoque é insuficiente.
// Essa exceção personalizada "InsufficientStockException" é usada para sinalizar esse erro específico de forma clara e compreensível.
public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message) {
        super(message);
    }
}
