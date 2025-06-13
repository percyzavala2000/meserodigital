# 🍽️ Mesero Digital

**Mesero Digital** es un sistema web y móvil que moderniza el proceso de toma y gestión de pedidos en restaurantes. El proyecto está desarrollado como parte del curso **Curso Integrador I** y aplica principios de **Clean Architecture**, **SOLID**, y el patrón **MVC + Servicios** usando **Spring Boot**, **React Native (opcional)** y **Thymeleaf**.

---

## 🚀 Funcionalidades principales

- 📋 Gestión de productos: agregar, editar, eliminar, activar/desactivar.
- 📦 Administración de categorías.
- 📝 Gestión de pedidos por mesa.
- ⚡ Visualización en tiempo real del estado del pedido.
- 🔑 Autenticación de usuario administrador.
- 📱 Generación de menú digital accesible mediante código QR.

---

## ⚙️ Stack tecnológico

| Tecnología | Descripción |
|------------|-------------|
| **Spring Boot 3.x** | Backend principal con arquitectura limpia. |
| **Thymeleaf** | Motor de plantillas para el panel web del administrador. |
| **Spring Security + JWT** | Seguridad de la aplicación (en avance). |
| **Spring Data JPA + Hibernate** | Acceso y persistencia de datos. |
| **MySQL 8.0** | Base de datos relacional. |
| **Docker ()** | Contenedorización de la base de datos. |
| **Swagger / SpringDoc** | Documentación de la API REST (en avance). |

---

## 🗂️ Estructura del proyecto
```
com.meserodigital
├── domain # Modelos y servicios del dominio
    ├── model
│   ├── service
│   └── repository
├── application # Casos de uso, DTOs, lógica de negocio
    ├── service
│   └── dto
    └── mapper
├── infrastructure # Adaptadores de persistencia, entidades JPA
    └── persistence
│       ├── entity
│       └── repository
│       └── Adapter
├── presentation # Controladores Web (Thymeleaf) y API REST
    ├── admin
│   └── api
├── config # Configuración de seguridad, Swagger, etc.
└── resources/templates # Vistas Thymeleaf (HTML)
```
---

## 💻 Requisitos

- Java 17+
- Maven o Gradle
- MySQL 8 (o contenedor Docker de MySQL)
- IDE recomendado: IntelliJ / Eclipse / VSCode

---

## ⚡ Ejecución

## 1️⃣  Clona el repositorio:
```bash
git clone https://github.com/percyzavala2000/meserodigital.git 
```

## 2️⃣ Configura tu base de datos:
CREATE DATABASE mesero_digital;

## 3️⃣ Ejecuta el proyecto:
```bash
mvn spring-boot:run
```
## 4️⃣ Accede a la aplicación:
- **Panel de administración**: [http://localhost:8080/admin](http://localhost:8080/admin/productos)
- **API REST**: [http://localhost:8080/api](http://localhost:8080/api/productos)
- **Documentación Swagger**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

