# If you need more admins then add it to ```data.sql``` file

# Student ReportHub

A Spring Boot–based Student Result Management System with Thymeleaf frontend and PostgreSQL (Docker).  
Features include Admin authentication, Student/Class/Subject CRUD, Notice board, Result entry & reporting (with per-student details).

---

## 📋 Table of Contents

1. [Tech Stack](#-tech-stack)  
2. [Prerequisites](#-prerequisites)  
3. [Database Setup (Docker)](#-database-setup-docker)  
4. [Configuration](#-configuration)  
5. [Import & Build in Eclipse](#-import--build-in-eclipse)  
6. [Run the Application](#-run-the-application)  
7. [Available Modules & Routes](#-available-modules--routes)  
8. [Dashboard & Navigation](#-dashboard--navigation)  
9. [Project Structure](#-project-structure)  
10. [Troubleshooting](#-troubleshooting)  
11. [Future Enhancements](#-future-enhancements)  

---

## 🔧 Tech Stack

- **Backend**: Java 17, Spring Boot 3, Spring MVC, Spring Data JPA  
- **Frontend**: Thymeleaf templates, custom CSS  
- **Database**: PostgreSQL 15 (Docker)  
- **Build**: Maven  
- **IDE**: Eclipse IDE for Enterprise Java Developers  

---

## 🛠️ Prerequisites

- Install **JDK 17** and set `JAVA_HOME`  
- **Docker** & **docker-compose**  
- **Eclipse IDE** (with Spring Tools), or IntelliJ IDEA  
- **Maven** (bundled with IDE)  

---

## 🐳 Database Setup (Docker)

1. Create a `docker-compose.yml` alongside your project:

   ```yaml
   version: '3.8'
   services:
     postgres:
       image: postgres:15
       environment:
         POSTGRES_DB: srmsdb
         POSTGRES_USER: srmsuser
         POSTGRES_PASSWORD: srms_pass
       ports:
         - "5432:5432"
       volumes:
         - pgdata:/var/lib/postgresql/data
   volumes:
     pgdata:
```

2. Launch the container:

   ```bash
       docker-compose up -d
   ```

3. (Optional) Verify or create additional DBs:

   ```bash
   docker exec -it <container_name> psql -U srmsuser -d srmsdb
   # then in psql>:
   CREATE DATABASE srms;  -- if needed
   \q

   docker run --name my-srms -e POSTGRES_PASSWORD=srms_pass -e POSTGRES_DATABASE=srms -v pgdata:/var/lib/postgresql/data -d -p 5432:5432 postgres

   docker start my-srms

   docker exec -it my-srms psql -U srmsuser -d srmsdb
   ```
---
### Use PgAdmin or DBeaver to connect to the database and see the tables.

## ⚙️ Configuration

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/srmsdb
spring.datasource.username=srmsuser
spring.datasource.password=srms_pass
spring.datasource.initialization-mode=always

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
logging.level.org.hibernate.SQL=DEBUG

spring.thymeleaf.cache=false

server.port=8080
```

---

## 🚀 Import & Build in Eclipse

1. **File → Import** → **Existing Maven Projects** → select your project directory.
2. Right-click project → **Maven → Update Project…**
3. Ensure no compilation errors in **Package Explorer**.

---

## ▶️ Run the Application

1. Right-click `com.technohacks.srms.SrmsApplication` → **Run As → Spring Boot App**.
2. Watch console for “Tomcat started on port(s): 8080”.
3. Open browser: [http://localhost:8080/](http://localhost:8080/)

---

## 📚 Available Modules & Routes

| Module                | URL                     | Access  |
| --------------------- | ----------------------- | ------- |
| **Home (Dashboard)**  | `/`, `/home`            | Public² |
| **Login / Logout**    | `/login`, `/logout`     | Public  |
| **Students**          | `/students`             | Admin³  |
| Add Student           | `/students/new`         | Admin³  |
| Edit / Delete Student | `/students/edit/{id}`   | Admin³  |
| **Classes**           | `/classes`              | Admin³  |
| Add Class             | `/classes/new`          | Admin³  |
| **Subjects**          | `/subjects`             | Admin³  |
| Add Subject           | `/subjects/new`         | Admin³  |
| **Notices**           | `/notices`              | Admin³  |
| Add Notice            | `/notices/new`          | Admin³  |
| Public Announcements  | `/announcements`        | Public  |
| **Results**           | `/results`              | Admin³  |
| Enter Result          | `/results/new`          | Admin³  |
| Save Result           | `/results/save` (POST)  | Admin³  |
| All Raw Results       | `/results`              | Public  |
| Reports (Summaries)   | `/results/report`       | Public² |
| Student Detail Report | `/results/student/{id}` | Public² |

> ² Public (no login)
> ³ Admin (requires login)

---

## 🖥️ Dashboard & Navigation

* A **common nav fragment** (`fragments/nav.html`) is included in every template.
* Admin‐only links appear when logged in (`session.adminUser != null`).
* Quick links and colored cards on `home.html` give fast access to major features.

---

## 📁 Project Structure

```
src/
└─ main/
   ├─ java/com/technohacks/srms/
   │   ├─ config/GlobalModel.java
   │   ├─ controller/
   │   ├─ model/
   │   ├─ repository/
   │   ├─ service/
   │   └─ SrmsApplication.java
   └─ resources/
      ├─ templates/
      │   ├─ fragments/nav.html
      │   ├─ home.html
      │   ├─ login.html
      │   ├─ students.html
      │   ├─ student-form.html
      │   ├─ class-rooms.html
      │   ├─ class-room-form.html
      │   ├─ subjects.html
      │   ├─ subject-form.html
      │   ├─ notices.html
      │   ├─ notice-form.html
      │   ├─ notice-list-public.html
      │   ├─ result-entry.html
      │   ├─ results.html
      │   ├─ reports.html
      │   └─ student-report.html
      ├─ static/css/styles.css
      └─ application.properties
```

---

## 🐞 Troubleshooting

* **404 /error** on missing routes → verify controller `@GetMapping` path and template filename.
* **Thymeleaf parsing errors** → check `xmlns:th` on `<html>` and valid `th:` attributes.
* **DB connection errors** → confirm `application.properties` URL matches Docker DB.
* **NoSQL logs** → ensure `spring.jpa.show-sql=true` and `logging.level.org.hibernate.SQL=DEBUG`.

---

## 🌱 Future Enhancements

* Integrate **Spring Security** with BCrypt passwords.
* Add **file export** (PDF/Excel) for reports.
* Implement **pagination & filtering** for lists.
* Deploy with **Docker Compose** including the app container.
* Add **REST API** with Swagger/OpenAPI docs.

---
