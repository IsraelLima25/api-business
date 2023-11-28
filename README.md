# API COMMERCE

[![NPM](https://img.shields.io/npm/l/react)](https://github.com/IsraelLima25/sec-piloto-api/blob/master/LICENSE)

# Sobre o projeto

Api Restful que simula o processamento de pedidos no formato json e xml. Esta API foi documentada com o swagger, segue link abaixo para acessar os documentos quando a API estiver em execução.

http://localhost:8080/swagger-ui/index.html

# Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Spring MVC
- Swagger
- JPA / Hibernate
- Mysql Database
- Maven

## Implantação em produção
- Amazon EC2

# Como executar o projeto no Host
Pré-requisitos: Maven

```bash
# Clonar repositório
git clone git@github.com:IsraelLima25/api-business.git

# Executar na pasta do projeto o comando
mvn spring-boot:run
```

# Como executar o projeto em ambiente docker compose

Pré-requisitos: Docker Compose

```bash
# Na raiz do projeto o arquivo do docker compose já está configurado, executar o comando abaixo para subir os containers
docker-compose up -d
```
# Representações para facilitar os testes

- Processar pedidos em formato JSON
````json
[
  {
    "codigo": 1246,
    "dataCadastro": "2023-11-26",
    "produtos":
     [
       {
        "nome":"Corel intel I3",
        "valor": 1000.0,"quantidade":1
       },
       {
        "nome":"Placa de video",
        "valor": 500.0,"quantidade":2
       }
     ],
    "cliente":
      {
        "id": 4
      }
  }
]
````

- Processar pedidos em formato XML
````xml
 <List>
    <PedidoFormDTO>
        <codigo>46</codigo>
        <dataCadastro></dataCadastro>
        <produtos>
            <ProdutoPedidoFormDTO>
                <nome>Core Intel i3</nome>
                <valor>1000</valor>
                <quantidade>2</quantidade>
            </ProdutoPedidoFormDTO>
            <ProdutoPedidoFormDTO>
                <nome>Placa de video</nome>
                <valor>500.0</valor>
                <quantidade>2</quantidade>
            </ProdutoPedidoFormDTO>
        </produtos>
        <cliente>
            <id>2</id>
        </cliente>
    </PedidoFormDTO>
    <PedidoFormDTO>
        <codigo>45</codigo>
        <dataCadastro></dataCadastro>
        <produtos>
            <ProdutoPedidoFormDTO>
                <nome>Corel Intel i5</nome>
                <valor>2500.0</valor>
                <quantidade>1</quantidade>
            </ProdutoPedidoFormDTO>
            <ProdutoPedidoFormDTO>
                <nome>SSD 256</nome>
                <valor>800.0</valor>
                <quantidade>3</quantidade>
            </ProdutoPedidoFormDTO>
        </produtos>
        <cliente>
            <id>4</id>
        </cliente>
    </PedidoFormDTO>
</List>
````

- Filtrar pedidos
````json
{
  "codigo":"46",
  "dataCadastro":"2023-11-28",
  "cliente": 
  {
    "nome":""
  }
}
````

# Observações: 
- No cenário de execução com docker ficar atento para possíveis conflitos na definição das portas host/container.
- Para executar o projeto localhost será necessário server mysql local ou usando o próprio docker compose subindo a base de dados pelo container

# Autor

Israel Santos Lima Filho

https://www.linkedin.com/in/israelsantoslima