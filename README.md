# ongaku

_**Ongaku** means *"music"* in Japanese._

This project is a **REST API** that allows you to **add**, **retrieve**, **update**, and **delete** songs.

## Prerequisites

- [Docker](https://www.docker.com/)

## Technologies

- Java
- Spring Boot
- PostgreSQL
- Docker
- Docker Compose

## How to Run

To configure and start the application, simply run the following commands:

```bash
openssl genrsa > src/main/resources/private.key

openssl rsa -in src/main/resources/private.key -pubout -out src/main/resources/public.key

docker compose up -d
```

The API will be available at `http://localhost:8080`

## Endpoints

### `POST /songs`

Request example:

```json
{
    "title": "Bad"
}
```

Response example:

`201 CREATED`

```json
{
    "id": "6e37765e-4414-42c4-93d5-5462312700b8",
    "title": "Bad"
}
```

### `GET /songs`

Response example:

`200 OK`

```json
[
    {
        "id": "6e37765e-4414-42c4-93d5-5462312700b8",
        "title": "Bad"
    },
    {
        "id": "1090a946-f73c-47bb-b764-f621ebc8df1d",
        "title": "Smooth Criminal"
    }
]
```

### `GET /songs/{id}`

Response example:

`200 OK`

```json
{
    "id": "1090a946-f73c-47bb-b764-f621ebc8df1d",
    "title": "Smooth Criminal"
}
```

### `PUT /songs/{id}`

Request example

```json
{
    "title": "Leave Me Alone"
}
```

Response example:

`200 OK`

```json
{
    "id": "1090a946-f73c-47bb-b764-f621ebc8df1d",
    "title": "Leave Me Alone"
}
```

### `DELETE /songs/{id}`

Response example:

`204 NO CONTENT`

### `POST /sign-up`

Request example:

```json
{
    "username": "ada_lovelace",
    "rawPassword": "ada_lovelace@123"
}
```

Response example:

`201 CREATED`

```json
{
    "id": "df5673e3-0464-4577-ad0f-5ad72b39301a",
    "username": "ada_lovelace"
}
```

### `POST /sign-in`

Response example:

`200 OK`

```json
{
    "jwt": "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJvbmdha3UiLCJzdWIiOiJhZGFfbG92ZWxhY2UiLCJleHAiOjE3NTE1ODU2NTgsImlhdCI6MTc1MTU4MjA1OCwicm9sZXMiOlsiUk9MRV9VU0VSIl19.g-aP1bGOR8VW6hF_1yboQyZaj_PAd_B6-xyEfSyQz8eU0TlInjImuXxlXBLJZlXlCZPGNF3Sn7tyo-Nc8Nsp_4rjfEABokHSI0vosOKyXgHIn02Cd1DsNHfbGFtGyDfNODUsOwCNkgPOwmNvqqKdyq5jYka5ZRXLt9o5DBKmrbKfKdQGB8MOtRInXSJakobHC6LsFUWxFR9m7r3Fm-qeXp8MWYXuKMVf2ABYLwwXu2dVmEK2KeB2gn0Yopije3ENKmNoWJJcYiZLZdA_53FLcnisGxfil-7ZcOAxaViMQw28FlVZJzClkmujC5gnQcOTMrE7tYLKUQrN-yZr-E0szQ"
}
```

## Model

### Song

Represents a song entity managed by the API.

| Field   | Type   | Description           |
|---------|--------|-----------------------|
| `id`    | UUID   | Unique identifier     |
| `title` | String | The title of the song |

### User

Represents a user entity managed by the API.

| Field          | Type       | Description                   |
|----------------|------------|-------------------------------|
| `id`           | UUID       | Unique identifier             |
| `username`     | String     | The username of the user      |
| `passwordHash` | String     | The hash of the user password |
