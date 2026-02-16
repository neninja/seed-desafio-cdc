# Biblioteca

## Utilização

### Ambiente

- O banco é o *H2* e está configurado para persistir no arquivo `data/biblioteca.mv.db` a partir do *workdir* da execução. Caso ele não exista será criado.


### Execução

- Crie as variáveis de ambiente

```shell
DATASOURCE_URL=jdbc:postgresql://localhost:5432/mydatabase
DATASOURCE_URL=sa
DATASOURCE_URL=pwd
```

- Execute o artefato *jar*

```shell
java -Dspring.profiles.active=prod -jar target/biblioteca_dev_eficiente-0.0.1-SNAPSHOT.jar
```

> Caso precise modificar alguma configuração de [application.properties](src/main/resources/application.properties) ou [application-prod.properties](src/main/resources/application-prod.properties), como porta, utilize `-Dserver.port=8081` na execução do pacote ou variável como `-DSERVER_PORT=8081`