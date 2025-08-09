
# Estoque API

API REST para gerenciamento de estoque de produtos.

## Tecnologias

- Java 21
- Spring Boot
- Spring Data JPA
- H2 Database (banco em memória para testes)
- Jakarta Validation

## Funcionalidades

- Criar produtos com nome, quantidade e preço
- Listar todos os produtos
- Consultar produto por ID
- Comprar produtos (atualiza a quantidade em estoque com validação)

## Como rodar

### Pré-requisitos

- Java 21 instalado
- Maven ou Gradle (dependendo do seu build)

### Rodando com Maven

```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080/api/produtos`

## Endpoints

| Método | Endpoint                     | Descrição                          |
|--------|------------------------------|------------------------------------|
| POST   | `/api/produtos`              | Criar um novo produto              |
| GET    | `/api/produtos`              | Listar todos os produtos           |
| GET    | `/api/produtos/{id}`         | Buscar produto por ID              |
| POST   | `/api/produtos/{id}/comprar` | Comprar produto (diminuir estoque) |

## Exemplos de requisição

### Criar Produto

```json
POST /api/produtos
Content-Type: application/json

{
  "nome": "Caneta",
  "quantidade": 100,
  "preco": 2.50
}
```

### Comprar Produto

```json
POST /api/produtos/1/comprar
Content-Type: application/json

{
  "quantidade": 5
}
```

## Tratamento de erros

- Se o produto não for encontrado, retorna HTTP 404.
- Se a quantidade para compra for maior que o estoque, retorna erro com mensagem "Estoque insuficiente".
- Validações automáticas para campos obrigatórios e valores mínimos.

## Como contribuir

- Faça um fork deste repositório
- Crie uma branch para sua feature ou correção (`git checkout -b feature/nova-funcionalidade`)
- Faça commit das mudanças com mensagens claras
- Envie um pull request

## Licença

Este projeto está licenciado sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para detalhes.

---

Feito com ❤️ por você!
