# Панель администратора для управления пользователями

![CI Status](https://github.com/tennyros/spring-boot-admin-dashboard/workflows/Java%20CI%20with%20Maven/badge.svg)
![Java 11](https://img.shields.io/badge/Java-11-blue)
![Spring Boot 2.6.2](https://img.shields.io/badge/Spring_Boot-2.6.2-brightgreen)

Full-stack система управления пользователями, созданная с использованием Spring Boot, Spring Security и JavaScript. Приложение предоставляет функциональность входа в систему, пользовательские представления и панель администратора для управления пользователями.

[English](README.md) | [Русский](README_RUS.md)

## 🔧 Возможности

- ✅ Безопасный вход по имени пользователя (электронной почте) и паролю
- 🔒 Управление доступом на основе ролей (`USER`, `ADMIN`)
- 🧑‍💼 Панель администратора для:
  - Просмотра всех пользователей
  - Создания, редактирования и удаления пользователей
- 👤 Панель пользователя для просмотра личной информации
- ⚡ Динамический интерфейс (без перезагрузки страницы) с использованием JavaScript + Fetch API
- 📄 REST API бэкенд на Spring MVC
- 🎨 Интерфейс пользователя, построенный с помощью Thymeleaf и Bootstrap
- 🐬 Приложение и MySQL контейнеризованы с помощью Docker
- 📦 Фронтенд собран с использованием Webpack, Babel и `frontend-maven-plugin`

## 🛠 Технологический стек

### Бэкенд

- Java 11
- Spring Boot 2.6.2
- Spring Security
- Spring Data JPA
- MySQL 8.0
- Lombok
- MapStruct
- OpenAPI UI (Swagger)

### Фронтенд

- Vanilla JavaScript
- Fetch API
- Webpack
- Babel
- Bootstrap 5
- Thymeleaf (Шаблонизация на стороне сервера)

### DevOps

- Docker
- Docker Compose
- Maven
- Node.js v16.13.0
- npm 8.1.0

## 📋 Предварительные требования

🔹 Если запускаете с помощью Docker (рекомендуется):

- Docker
- Docker Compose
- ~750 MB свободного места на диске

🔹 Если запускаете вручную:

- Java 11 или выше
- Maven 3.6+
- MySQL 8.0

> ⚙️ Node.js и npm **не обязаны быть установлены** на вашей системе —
> они автоматически загружаются и используются плагином `frontend-maven-plugin` во время сборки проекта.
>
> 🛠 Однако, если вы планируете запускать фронтенд вручную (например, через `npm run build`),
> убедитесь, что у вас установлены:
>
> - Node.js v16.13.0
> - npm 8.1.0

## 🚀 Начало работы

1. **Клонируйте репозиторий**

   ```bash
   git clone https://github.com/tennyros/spring-boot-admin-dashboard.git
   cd spring-boot-admin-dashboard
   ```

2. **Настройте переменные окружения**

   ```bash
   # Отредактируйте файл .env с вашими конфигурациями
   cp .env.example .env
   ```

### 🔧 Вариант 1: запуск через Docker Compose

#### Настройте docker-compose.yml файл и запустите с помощью Docker Compose

   ```bash
   # Скопируйте пример конфигурации (если еще не настроено)
    cp docker-compose.example.yml docker-compose.yml
    
   # И запустите
   docker compose up --build -d
   ```

Приложение будет доступно по адресу `http://localhost:8088`

### 🔧 Вариант 2: ручной запуск

1. **Убедитесь, что у вас установлен MySQL**

   ```bash
   # Либо загрузите и запустите MySQL при помощи Docker Compose 
   # отдельно от приложения:
   docker compose up --build -d spring-boot-admin-dashboard-db
   
   # Предварительно выполнив команду:
   cp docker-compose.example.yml docker-compose.yml 
   ```

2. **Соберите приложение**

   ```bash
   ./mvnw clean package -Dspring.profiles.active=dev
   ```

3. **Запуск приложения**

   ```bash
   # Запуск через терминал:
   ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

   # Запуск через IntelliJ IDEA (Shift + F10):
   Установите профиль dev в Active profiles в 
   настройках конфигурации основного класса
   ```

Приложение будет доступно по адресу `http://localhost:8089`

> 🔁 В приложении по умолчанию Docker использует порт 8088, а при локальном запуске — 8089. Вы можете изменить это в application.properties.

## 🧪 REST API Endpoints

| Метод  | Конечная точка             | Описание                     |
|--------|----------------------------|------------------------------|
| GET    | `/api/v1/admin/users/{id}` | Получить конкретного пользователя |
| GET    | `/api/v1/admin/users`      | Список всех пользователей (только админ) |
| POST   | `/api/v1/admin/users`      | Создать нового пользователя (только админ) |
| PUT    | `/api/v1/admin/users/{id}` | Редактировать пользователя (только админ) |
| DELETE | `/api/v1/admin/users/{id}` | Удалить пользователя (только админ) |

## 🌐 Как это работает

- Администраторы могут:
  - Получать доступ к таблице пользователей
  - Создавать новых пользователей
  - Редактировать/удалять существующих пользователей
- Все пользователи могут:
  - Входить в систему
  - Просматривать информацию своего профиля
- Поведение, подобное SPA, через Fetch API и JavaScript (без полной перезагрузки страницы)

## 📝 Лицензия

Этот проект лицензирован на условиях лицензии, включенной в репозиторий.
