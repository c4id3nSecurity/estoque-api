package com.c4id3n.estoque_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c4id3n.estoque_api.dto.BuyRequest;
import com.c4id3n.estoque_api.dto.CreateProductRequest;
import com.c4id3n.estoque_api.model.Produto;
import com.c4id3n.estoque_api.service.ProdutoService;

// Por que deste arquivo?
// Este arquivo é a porta de entrada para as requisições HTTP (GET, POST e etc).
// Faz a ponte entre o cliente ( Front-end ) e a camada de serviço ( Que contém a lógica de negócio ).
// Ele traduz dados do formato JSON para objetos Java ( DTOs ) e vice-versa.
// Controla quais URLs/Endpoints a API oferece.

// "@RestController" é uma anotação que indica que esta classe é um controlador REST, que automaticamente serializa objetos Java para JSON.
// "@RequestMapping" define a URL base para os endpoints deste controlador, neste caso "/api/produtos".
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    
    // Atributo "service" é uma instância do serviço de produtos, que contém a lógica de negócio.
    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }
    
    // "@PostMapping" indica que esse método responde a requisições POST em "/api/produtos".
    // Recebe um JSON que o Spring transforma em "CreateProductRequest" e valida "@Validated".
    // Converte o DTO para a entidade "Produto".
    // Chama o serviço para salvar no banco de dados e retorna a resposta com o produto salvo.
    @PostMapping
    public ResponseEntity<Produto> create(@Validated @RequestBody CreateProductRequest req){
        Produto p = new Produto(req.nome, req.quantidade, req.preco);
        Produto saved = service.create(p);
        return ResponseEntity.ok(saved);
    }
    
    // "@GetMapping" indica que esse método responde a requisições GET em "/api/produtos".
    // Retorna uma lista de todos os produtos disponíveis, obtidos através do serviço.
    @GetMapping
    public List<Produto> list(){
        return service.listAll();
    }

    // "@GetMapping" com "{id}" indica que esse método responde a requisições GET em "/api/produtos/{id}".
    // Pega o ID da URL com "@PathVariable"
    // Busca o produto pelo ID.
    // Usa "ResponseEntity.of(Optional)" para retornar:
    // HTTP 200 com o produto se encontrado, ou HTTP 404 se não encontrado.
    @GetMapping("/{id}")
    public ResponseEntity<Produto> get(@PathVariable Long id){
        return ResponseEntity.of(java.util.Optional.ofNullable(service.getById(id)));
    }

    // "@PostMapping" com "{id}/buy" indica que esse método responde a requisições POST em "/api/produtos/{id}/buy".
    // Recebe o ID do produto na URL e a quantidade a comprar no corpo da requisição ( DTO BuyRequest ).
    // Chama o serviço para fazer a compra ( Atualizar o estoque do produto ).
    // Retorna o produto atualizado com HTTP 200.
    // Se o produto não existir, o serviço lançará uma exceção que será tratada pelo controlador global de exceções.
    @PostMapping("/{id}/buy")
    public ResponseEntity<Produto> buy(@PathVariable Long id, @Validated @RequestBody BuyRequest req){
        Produto updated = service.buy(id, req.quantidade);
        return ResponseEntity.ok(updated);
    }
}
