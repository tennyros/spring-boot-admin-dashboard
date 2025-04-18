# User Management Admin Dashboard

![CI Status](https://github.com/tennyros/spring-boot-admin-dashboard/workflows/Java%20CI%20with%20Maven/badge.svg)
![Java 11](https://img.shields.io/badge/Java-11-blue)
![Spring Boot 2.6.2](https://img.shields.io/badge/Spring_Boot-2.6.2-brightgreen)

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
- 🐬 Application and MySQL containerized with Docker
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

🔹 If you're running with **Docker** (recommended):

- Docker
- Docker Compose
- ~750 MB of free disk space

🔹 If you're running manually:

- Java 11 or higher
- Maven 3.6+
- MySQL 8.0

> ⚙️ Node.js and npm are **not required** to be installed on your system —  
> they are automatically downloaded and used by the `frontend-maven-plugin` during the build process.
>
> 🛠 However, if you plan to run the frontend manually (e.g., using `npm run build`),  
> make sure you have the following versions installed:
>
> - Node.js v16.13.0
> - npm 8.1.0

## 🚀 Getting Started

1. **Clone the repository**

   ```bash
   git clone https://github.com/tennyros/spring-boot-admin-dashboard.git
   cd spring-boot-admin-dashboard
   ```

2. **Set up environment variables**

   ```bash
   # Edit the .env file with your own configuration
   cp .env.example .env
   ```

### 🔧 Option 1: Run using Docker Compose

#### Set up the docker-compose.yml file and start the application using Docker Compose

   ```bash
   # Copy the example config file (if not already configured)
   cp docker-compose.example.yml docker-compose.yml

   # Then start the containers
   docker compose up --build -d
   ```

The application will be available at `http://localhost:8088`

### 🔧 Option 2: Run manually

1. **Make sure MySQL is installed and running**

   ```bash
   # Or run a MySQL container using Docker Compose
   # application separated:
   docker compose up --build -d spring-boot-admin-dashboard-db

   # After preparing the config file:
   cp docker-compose.example.yml docker-compose.yml
   ```

2. **Build the application**

   ```bash
   ./mvnw clean package -Dspring.profiles.active=dev
   ```

3. **Run the application**

   ```bash
   # Run from terminal:
   ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

   # Or run from IntelliJ IDEA (Shift + F10):
   Set `dev` as the active profile in your run configuration
   ```

The application will be available at `http://localhost:8089`

> 🔁 By default, the app uses port 8088 when running in Docker and 8089 when running locally. You can change this in your application.properties file.

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
