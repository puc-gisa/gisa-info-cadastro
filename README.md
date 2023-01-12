# GISA

https://github.com/puc-gisa/gisa-info-cadastro

## Associado

- Criar

```bash
curl --location --request POST 'localhost:8080/associate/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "John",
    "lastName" : "Doe",
    "email" : "johndoe@gmail.com",
    "birthDate": "31/12/2000"
}'
```

- Buscar por ID

```bash
curl --location --request GET 'localhost:8080/associate/1'
```

- Buscar todos

```bash
curl --location --request GET 'localhost:8080/associate/'
```

## Actuator

```bash
curl http://localhost:8080/health
```

# Configurações

Variaveis de ambiente

```
    DB_URL      = jdbc:postgresql://localhost:5432/gisadb
    DB_USER     = gisadb
    DB_PASSWORD = ***
```

# Postgres

```
docker run --name gisa-postgres -e POSTGRES_USER=gisadb -e POSTGRES_PASSWORD=*** -p 5432:5432 -d postgres:14.5
```