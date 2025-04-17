# User Management Admin Dashboard

A full-stack user management system built with Spring Boot, Spring Security and JavaScript. The application provides login functionality, user-specific views, and an admin-only panel for managing users.

[English](README.md) | [Русский](README_RUS.md)

## 🔧 Features

- ✅ Secure login via username (email) and password
- 🔒 Role-based access control (`USER`, `ADMIN`)
- 🧑‍💼 Admin dashboard for:
  - Viewing all users
  - Creating, editing, and deleting users
- 👤 User panel to view personal information
- ⚡ Dynamic UI (no page reloads) via JavaScript + Fetch API
- 📄 REST API backend with Spring MVC
- 🎨 UI built with Thymeleaf and Bootstrap
- 🐬 MySQL containerized with Docker
- 📦 Frontend bundled using Webpack, Babel and `frontend-maven-plugin`

## 🛠 Tech Stack

### Backend

- Java 11
- Spring Boot 2.6.2
- Spring Security
- Spring Data JPA
- MySQL 8.0
- Lombok
- MapStruct
- OpenAPI UI (Swagger)

### Frontend

- Vanilla JavaScript
- Fetch API
- Webpack
- Babel
- Bootstrap 5
- Thymeleaf (Server-side templating)

### DevOps

- Docker
- Docker Compose
- Maven
- Node.js v16.13.0
- npm 8.1.0

## 📋 Prerequisites

- Java 11 or higher
- Maven 3.6+
- Docker and Docker Compose
- Node.js v16.13.0
- npm 8.1.0
- MySQL 8.0

## 🚀 Getting Started

1. **Clone the repository**

   ```bash
   git clone https://github.com/tennyros/spring-boot-rest-fetch-js.git
   cd spring-boot-rest-fetch-js
   ```

2. **Configure Environment Variables**

   ```bash
   # Edit .env file with your configurations
   cp .env.example .env
   ```

3. **Build the Application**

   ```bash
   ./mvnw clean package
   ```

4. **Configure docker-compose.yml file and run with Docker Compose**

   ```bash
   # Copy the example config (if not customized yet)  
   cp docker-compose.example.yml docker-compose.example.yml
    
   # And run it
   docker-compose up -d
   ```

   The application will be available at `http://localhost:8088`

## 🧪 REST API Endpoints

| Method | Endpoint                   | Description                  |
|--------|----------------------------|------------------------------|
| GET    | `/api/v1/admin/users/{id}` | Get certain user             |
| GET    | `/api/v1/admin/users`      | List all users (admin only)  |
| POST   | `/api/v1/admin/users`      | Create new user (admin only) |
| PUT    | `/api/v1/admin/users/{id}` | Edit user (admin only)       |
| DELETE | `/api/v1/admin/users/{id}` | Delete user (admin only)     |

## 🌐 How it works

- Admins can:
  - Access the user table
  - Create new users
  - Edit/delete existing users
- All users can:
  - Log in
  - View their profile info
- SPA-like behavior via Fetch API and JavaScript (no full-page reloads)

## 📝 License

This project is licensed under the terms of the license included in the repository.
