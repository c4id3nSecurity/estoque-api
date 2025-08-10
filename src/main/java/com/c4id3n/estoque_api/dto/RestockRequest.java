package com.c4id3n.estoque_api.dto;

import jakarta.validation.constraints.Min;

public class RestockRequest {

    @Min(value = 1, message = "A quantidade para reposição deve ser maior que zero.")
    public int quantidade;
}
