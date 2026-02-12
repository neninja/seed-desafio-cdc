# Biblioteca

## Utilização

### Ambiente

- O banco é o *H2* e está configurado para persistir no arquivo `data/biblioteca.mv.db` a partir do *workdir* da execução. Caso ele não exista será criado.

### Compilação

- Empacote o *jar*

```shell
make package
```

### Execução

- Execute a pacote

```shell
java -Dspring.profiles.active=prod -jar target/biblioteca_dev_eficiente-0.0.1-SNAPSHOT.jar
```

> Caso precise modificar alguma configuração de `application.properties` ou `application-prod.properties`, como porta, utilize `-Dserver.port=8081` na execução do pacote ou variável como `-DDATASOURCE_URL=/caminho/para/biblioteca-prod`
