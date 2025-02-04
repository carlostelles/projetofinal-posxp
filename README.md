# Sistema de Vendas Online 🛒

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-brightgreen)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-blue)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-Apache%202.0-yellow)](https://opensource.org/licenses/Apache-2.0)

Um sistema de gerenciamento de vendas online com API RESTful, desenvolvido em **Java Spring Boot**, seguindo o padrão **MVC**.  
Disponibiliza operações CRUD para clientes, produtos e pedidos, além de integração com parceiros via endpoints públicos.

---

## 📋 Tabela de Conteúdo
- [Tecnologias](#-tecnologias)
- [Funcionalidades](#-funcionalidades)
- [Documentação da API](#-documentação-da-api)
- [Instalação](#-instalação)
- [Testes](#-testes)
- [Contribuição](#-contribuição)
- [Licença](#-licença)

---

## 🛠 Tecnologias
- **Java 17**
- **Spring Boot 3.1.5**
- **Spring Data JPA** (Persistência de dados)
- **MySQL** / **H2** (Banco de dados)
- **Lombok** (Redução de boilerplate)
- **Gradle** (Gerenciamento de dependências)
- **Postman** (Testes de API)

---

## 🚀 Funcionalidades
### Cliente
- CRUD completo de clientes.
- Busca por nome (`findByName`).
- Contagem total de clientes (`count`).

### Produto
- CRUD de produtos.
- Controle de estoque (`reduzirEstoque`, `aumentarEstoque`).
- Consulta por nome e categoria.

### Pedido
- Criação de pedidos com múltiplos itens.
- Atualização de status (`PENDENTE`, `ENVIADO`, `ENTREGUE`, etc.).
- Cálculo automático do total do pedido.

---

## 📚 Documentação da API
### Endpoints Principais

#### Clientes
| Método | Endpoint                      | Descrição                           |
|--------|-------------------------------|-------------------------------------|
| POST   | `/clientes`                   | Cria um novo cliente.               |
| PUT    | `/clientes/{id}`              | Atualiza os dados de um cliente.    |
| GET    | `/clientes`                   | Lista todos os clientes.            |
| GET    | `/clientes/{id}`              | Busca cliente por ID.               |
| GET    | `/clientes/search/findByName` | Busca clientes por nome.            |
| GET    | `/clientes/count`             | Retorna o total de clientes.        |

#### Produtos
| Método | Endpoint                      | Descrição                          |
|--------|-------------------------------|------------------------------------|
| POST   | `/produtos`                   | Cria um novo produto.              |
| GET    | `/produtos`                   | Lista todos os produtos.           |
| GET    | `/produtos/search/findByName` | Busca produtos por nome.           |

#### Pedidos
| Método | Endpoint                      | Descrição                          |
|--------|-------------------------------|------------------------------------|
| POST   | `/pedidos`                    | Cria um novo pedido.               |
| POST   | `/pedidos/{id}/itens`         | Adiciona item a um pedido.         |
| PUT    | `/pedidos/{id}/status`        | Atualiza o status do pedido.       |

### Exemplo de Requisição (Criar Pedido)
```json
POST /pedidos
{
  "clienteId": 1,
  "itens": [
    {
      "produtoId": 1,
      "quantidade": 2
    }
  ]
}
```

## 🔧 Instalação

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/sistema-vendas-online.git
```

2. Configure o banco de dados (MySQL ou H2):

Edite o arquivo `src/main/resources/application.properties`:

````properties
spring.datasource.url=jdbc:mysql://localhost:3306/vendas_online
spring.datasource.username=root
spring.datasource.password=sua-senha
````

3. Execute o projeto:

````bash
./gradlew bootRun
````

## 🧪 Testes
### Via Postman
1. Importe a coleção do Postman fornecida.
2. Configure as variáveis de ambiente:
   * `base_url`: `http://localhost:8080`
   * `cliente_id`, `produto_id`, `pedido_id` (atualize após criar recursos).

## 🤝 Contribuição
Contribuições são bem-vindas! Siga os passos:

1. Faça um fork do projeto.
2. Crie uma branch: `git checkout -b feature/nova-feature`.
3. Faça commit das mudanças: `git commit -m 'Adiciona nova feature'`.
4. Push para a branch: `git push origin feature/nova-feature`.
5. Abra um Pull Request.

## 📄 Licença
Este projeto está sob a licença Apache 2.0.

Feito com ❤️ por Carlos Telles

📧 Contato: carlostelles.noia@gmail.com