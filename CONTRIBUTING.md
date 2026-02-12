# Contribuindo

## Desenvolvimento

### Ambiente local

- Baixe JDK 21
- Baixe as dependências
```shell
make clean
```
- Duplique `application-local.properties.example` para `application-local.properties` e altere ou adicione configurações que julgue necessárias

### Execução

Após ambiente configurado:

- Inicie a aplicação
```shell
make run
```

> Debug disponivel com JDWP, conecte a IDE como *Remote Debug* na porta 5005

### Padronização

- Formate o código
```shell
make fmt
```

### Dicas

- Utilize as chamadas HTTP de `requests` e configure, se necessário, o `http-client.private.env.json`
