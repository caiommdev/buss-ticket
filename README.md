# Bus Ticket API

API RESTful em Spring Boot para gerenciamento de reservas de passagens de onibus em memoria (ArrayList), com separacao por camadas:

- `controllers`
- `services`
- `dtos`
- `models`

## Requisitos

- Java 26 (conforme `pom.xml`)
- Maven Wrapper (`./mvnw`)

## Executar

```bash
./mvnw spring-boot:run
```

Aplicacao sobe em `http://localhost:8080`.

## Endpoints

- `GET /tickets` - lista todos os tickets
- `POST /tickets` - cria ticket (retorna `201`)
- `GET /tickets/{id}` - busca por id (retorna `404` se nao existir)
- `PUT /tickets/{id}` - atualiza por id (retorna `404` se nao existir)
- `DELETE /tickets/{id}` - remove por id (retorna `204` ou `404`)
- `GET /tickets/search?destination=...` - filtra por destino

## Regras de negocio

- Assento nao pode ser duplicado no `POST` (retorna `400`).
- No `PUT`, nao permite trocar para um assento ja usado por outro ticket (retorna `400`).
- Operacoes por `id` (`GET`, `PUT`, `DELETE`) retornam `404` quando o `id` nao existe.

## Exemplo de payload (`POST` e `PUT`)

```json
{
  "passenger": "Diego Alves",
  "seat": 30,
  "origin": "Campinas",
  "destination": "Santos",
  "travelDate": "2026-06-10",
  "status": "PENDING"
}
```

## Testes

```bash
./mvnw test
```

