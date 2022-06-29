# spring_themovies
API desenvolvida usando o Spring Boot para treinamento.

### Dependências utilizadas:
- Spring Data JPA
- Spring Web
- MySql Database
- H2 Database

## Executando o projeto

Para executar o projeto (com o [Maven](https://maven.apache.org/) instalado) execute o comando no diretório do projeto:

```
   mvn spring-boot:run
```

### Endpoints

Os endpontis disponibilizados para API (com as configurações padrão o serviço subira em http://localhost:3030) são:

| Verbo HTTP  |  Resource path    |                                                           Descrição |
|-------------|:-----------------:|--------------------------------------------------------------------:|
| POST        |  /movies        |                                                 Criação de um filme |
| PUT         |  /movies/{id}   |                                             Atualização de um filme |
| GET         |  /movies/{id}   |                                            Busca de um filme por ID |
| GET         |  /movies        | Listar os filmes. Querys aceitas: release_year, page, sort_by, size |
| DELETE      |  /movies/{id}   |                                                 Deleção de um filme |