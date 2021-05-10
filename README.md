# Product-MS

Neste microserviço deve ser possível criar, alterar, visualizar e excluir um determinado produto, além de visualizar a lista de produtos atuais dispononíveis. Também é ser possível realizar a busca de produtos filtrando por *name, description e price*.


### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
Java 11 (Java version: 11.0.11) e Maven (Apache Maven 3.8.1). O código foi editado utilizando o IntelliJ IDEA.

### 🎲 Rodando o Back End (servidor)

- Clone este repositório
- Acesse a pasta do projeto no terminal
- Entre na pasta target
- Execute o arquivo jar

### Disponível no Heroku
https://gleyser-product-ms-api.herokuapp.com/

### Formato das Requisições

O formato esperado de um produto é o seguinte:

```javascript
  {
    "id": "string",
    "name": "string",
    "description": "string",
    "price": 59.99
  }
```
Durante a criação e alteração, os campos *name, description e price* são obrigatórios. Em relação ao campo *price* o valor deve ser positivo.

### Endpoints

Devem ser disponibilizados os seguintes endpoints para operação do catálogo de produtos:


| Verbo HTTP  |  Resource path    |           Descrição           |
|-------------|:-----------------:|------------------------------:|
| POST        |  /products        |   Criação de um produto       |
| PUT         |  /products/{id}   |   Atualização de um produto   |
| GET         |  /products/{id}   |   Busca de um produto por ID  |
| GET         |  /products        |   Lista de produtos           |
| GET         |  /products/search |   Lista de produtos filtrados |
| DELETE      |  /products/{id}   |   Deleção de um produto       |

#### POST /products

Esse endpoint deve criar um novo produto na base de dados, ao receber o JSON do produto o mesmo deverá ser validado em acordo com as regras da seção **Formato**, e, caso esteja vÃ¡lido, persistido na base de dados e retornado com o *id* gerado e HTTP 201.

Entrada:
```javascript
  {
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  }
```

Retorno:
```javascript
  {
    "id": "id gerado",
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  }
```

Em caso de algum erro de validação, a API deve retornar um HTTP 400 indicando uma requisição inválida. O JSON retornado nesse caso deve seguir o seguinte formato:

```javascript
  {
    "status_code": integer,
    "message": "string"
  }
```
No campo *status_code* deve vir o código HTTP do erro de validação (400 Bad Request). No campo *message* deverá vir uma mensagem genéricaindicando o erro ocorrido.

#### PUT /products/\{id\}

Esse endpoint deve atualizar um produto baseado no {id} passado via path param. Antes de alterar, deve ser consultada a base de dados pelo *id*, se o produto NÃO for localizado entÃ£o devolver um HTTP 404 ao cliente. Se localizar o produto, então os campos *name, description e price* devem ser atualizados conforme recebidos no body da requisição.

Entrada:
```javascript
  {
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  }
```

Retorno:
```javascript
  {
    "id": "id atualizado",
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  }
```

Importante que antes da atualização as mesmas regras de validação do POST /products devem ser executadas para garantir consistência, e, se ocorrer erro retornar no mesmo formato:

```javascript
  {
    "status_code": integer,
    "message": "string"
  }
```

#### GET /products/\{id\}

Esse endpoint retorna o product localizado na base de dados com um HTTP 200. Em caso de não localização do produto, a API retorna um HTTP 404 indicando que o recurso não foi localizado.

Retorno:
```javascript
  {
    "id": "id buscado",
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  }
```

#### GET /products

Nesse endpoint a API deve retornar a lista atual de todos os produtos com HTTP 200. Se não existir produtos, retornar uma lista vazia.

Retorno com produtos:
```javascript
[
  {
    "id": "id produto 1",
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  },
  {
    "id": "id produto 2",
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  }
]
```

Retorno vazio:
```javascript
[]
```

#### GET /products/search

Nesse endpoint a API deve retornar a lista atual de todos os produtos filtrados de acordo com query parameters passados na URL.

Os query parameters aceitos serão: q, min_price e max_price.

**Importante: nenhum deles é obrigatório na requisição**

Onde:

| Query param |  Ação de filtro
|-------------|:---------------------------------------------------------------:|
| q           |  deverá bater o valor contra os campos *name* e *description*   |
| min_price   | deverá bater o valor ">=" contra o campo *price*                |
| max_price   | deverá bater o valor "<=" contra o campo *price*                |

**Exemplo: /products/search?min_price=10.5&max_price=50&q=superget**

Retorno com produtos filtrados/buscados:
```javascript
[
  {
    "id": "id produto 1",
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  },
  {
    "id": "id produto 2",
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  }
]
```

Retorno vazio:
```javascript
[]
```

#### DELETE /products/\{id\}

Esse endpoint deve deletar um registro de produto na base de dados. Caso encontre o produto filtrando pelo *id* então deve deletar e retornar um HTTP 200. Se o *id* passado não foi localizado deve retornar um HTTP 404



