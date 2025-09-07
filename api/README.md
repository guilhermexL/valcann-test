# API de Gerenciamento de Usuários

Uma API REST simples, construída com Java e Spring Boot, que gerencia uma lista de usuários a partir de um arquivo JSON local, simulando um banco de dados em memória.

A API suporta listagem com paginação, filtros dinâmicos e busca por ID.

## Tecnologias Utilizadas

* Java 21
* Spring Boot 3+
* Maven

## Pré-requisitos

* JDK 21 instalado.
* Maven.

## Como Executar

1.  Clone este repositório para a sua máquina local:

    ```bash
    git clone https://github.com/guilhermexL/valcann-test.git
    ```

2.  Navegue até a pasta raiz do projeto:

    ```bash
    cd valcann-test
    ```

3.  Compile e execute a aplicação.

    * **Se você usa Maven:**
      ```bash
      ./mvnw spring-boot:run
      ```

4.  A API estará disponível e pronta para receber requisições em `http://localhost:8080`.

## Exemplos de Uso com `cURL`

Estes são exemplos de como testar os endpoints da API diretamente do seu terminal.

### Listar Usuários (com paginação e filtros)

Este endpoint retorna uma lista paginada de usuários e aceita parâmetros de consulta para filtrar os resultados.

**1. Listar a primeira página de usuários**

```bash
curl -X GET http://localhost:8080/users
```

**2. Buscar a segunda página, com 3 usuários por página**

```bash
curl -X GET "http://localhost:8080/users?page=2&page_size=3"
```

**3. Filtrar todos os usuários com o cargo (role) de "developer"**

```bash
curl -X GET "http://localhost:8080/users?role=developer"
```

**4. Fazer uma busca por nome ou email que contenha "bruno"**

```bash
curl -X GET "http://localhost:8080/users?q=bruno"
```

**5. Filtrar por usuários inativos**

```bash
curl -X GET "http://localhost:8080/users?is_active=false"
```

### Buscar Usuário por ID

Este endpoint retorna um único usuário com base no seu ID.

**1. Buscar o usuário com ID 1**

```bash
curl -X GET http://localhost:8080/users/1
```

**2. Tentar buscar um usuário que não existe (ex: ID 99)**
*O parâmetro `-i` é para incluir os cabeçalhos HTTP na resposta, mostrando o status `404 Not Found`.*

```bash
curl -i -X GET http://localhost:8080/users/99
```

> **Dica:** Para uma visualização mais legível do JSON no terminal, instale a ferramenta `jq` e adicione `| jq` ao final dos seus comandos `curl`. Exemplo: `curl -X GET http://localhost:8080/users | jq`