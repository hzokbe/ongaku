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

To start the application, simply run the following command:

```bash
docker compose up
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

## Model

### Song

Represents a song entity managed by the API.

| Field   | Type   | Description           |
|---------|--------|-----------------------|
| `id`    | UUID   | Unique identifier     |
| `title` | String | The title of the song |
