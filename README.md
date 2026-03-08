# 🎵 Ongaku

**Ongaku** é uma plataforma moderna de streaming de música inspirada no **Spotify**.

## 💻 Tecnologias

- 🔷 **TypeScript**
- ▲ **Next.js**
- 🐍 **Python**
- ⚡ **FastAPI**
- 🐘 **PostgreSQL**

## 🗂️ Entidades

**Ongaku** utiliza um modelo de dados claro e escalável. As principais entidades e seus campos incluem:

- **User**
  - `id` (UUID) – Identificador único do usuário
  - `name` (str) – Nome completo do usuário
  - `email` (str) – E-mail para login
  - `password` (str) – Hash da senha
  - `created_at` (datetime) – Data de criação da conta

- **Artist**
  - `id` (UUID) – Identificador único
  - `name` (str) – Nome do artista ou banda
  - `biography` (Optional[str]) – Breve biografia (opcional)

- **Album**
  - `id` (UUID) – Identificador único
  - `title` (str) – Nome do álbum
  - `artist_id` (UUID) – Referência ao artista
  - `release_date` (datetime) – Data de lançamento

- **Track**
  - `id` (UUID) – Identificador único
  - `title` (str) – Nome da faixa
  - `duration` (int) – Duração em segundos
  - `album_id` (UUID) – Referência ao álbum
  - `artist_id` (UUID) – Referência ao artista

- **Playlist**
  - `id` (UUID) – Identificador único
  - `name` (str) – Nome da playlist
  - `user_id` (UUID) – Usuário criador da playlist
  - `description` (Optional[str]) – Descrição opcional
  - `tracks` (List[str]) – Lista de IDs das faixas

- **Genre**
  - `id` (UUID) – Identificador único
  - `name` (str) – Nome do gênero

## Licença

Este projeto está licenciado sob a **Licença MIT**.
