<p align="center">
  <img width="580" height="350" alt="logo" src="https://github.com/user-attachments/assets/727931ed-895f-43c2-86fb-c8b788f3fa73" />
</p>
<h1 align="center">zeertyDigitalApp</h1>


**Solución centralizada para el inventario, custodia de credenciales y gestión documental de activos digitales para PYMEs y autónomos.**

---

## 📝 Descripción del Proyecto

Aplicación web desarrollada con **Spring Boot** y **Thymeleaf**, orientada a resolver la fragmentación en la gestión de recursos digitales como dominios, certificados SSL, accesos, facturas, licencias y documentación asociada.

El sistema permite:

- Organizar activos digitales
- Gestionar usuarios y roles (RBAC)
- Controlar caducidades
- Subir y almacenar documentos
- Administrar la plataforma desde un panel seguro

---

## 🛠️ Stack Tecnológico

- **Lenguaje:** Java 25
- **Framework Backend:** Spring Boot 4.1.1
- **Gestor de Dependencias:** Maven
- **Módulos Principales:**
  - Spring Web (MVC + REST)
  - Spring Security (Autenticación y Roles)
  - Spring Data JPA (Persistencia)
  - Validation (Validación de formularios)
  - Lombok (Getters/Setters/Constructores)
  - DevTools (Recarga en desarrollo)
- **Frontend:** Thymeleaf + HTML5 + CSS3
- **Base de Datos:** MySQL 8 (entorno local)

---

## 📦 Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- Java 25 
- Maven 3.9+  
- MySQL 8  
- Git  
- IDE recomendado: VS Code o IntelliJ IDEA  

---

## 🚀 Cómo ejecutar el proyecto

### 1. Clonar el repositorio

```bash
git clone https://github.com/Miriam-Mendieta/zeertyDigitalApp.git
cd zeertyDigitalApp
```
### 2. Crear la base de datos en MySQL
```bash
CREATE DATABASE zeerty_digital_app;
```
### 3. Configurar credenciales en application.properties
Ubicación:
src/main/resources/application.properties
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/zeerty_digital_app
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

```
### 4. Ejecutar la aplicación
```bash
mvn spring-boot:run
```
La aplicación estará disponible en:
    👉 http://localhost:8080

## ✨ Funcionalidades Principales
- Gestión de usuarios y roles (RBAC)

- Panel de administración

- Gestión de activos digitales (dominios, certificados, accesos…)

- Control de caducidades

- Subida y almacenamiento de documentos

- Validación de formularios

- Seguridad con Spring Security

- Arquitectura MVC con Thymeleaf
---
## 📁 Estructura del Proyecto
```bash
zeertyDigitalApp/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/miriammendieta/zeerty_digital_app/
│   │   │       ├── config/        # Configuración de seguridad y beans
│   │   │       ├── controller/    # Controladores MVC
│   │   │       ├── model/         # Entidades JPA
│   │   │       ├── repository/    # Repositorios Spring Data JPA
│   │   │       └── service/       # Lógica de negocio
│   │   └── resources/
│   │       ├── static/            # CSS, imágenes, favicon
│   │       ├── templates/         # Vistas Thymeleaf
│   │       └── application.properties
└── pom.xml                        # Dependencias y configuración Maven

```

### Login
<p align="center">
<img width="300" height="300" alt="login" src="https://github.com/user-attachments/assets/e9a273c3-4750-4571-b5cf-8fb2197d43f0" />
</p>


### Panel de Administración
<p align="center">
<img width="600" height="300" alt="panelAdmin" src="https://github.com/user-attachments/assets/f2f3a774-9712-4446-aa17-0c75211ca249" />
</p>

##  📌 Estado del Proyecto  
  En desarrollo activo — versión inicial en construcción.

## 📄 Licencia 
  Este proyecto se distribuye bajo licencia MIT.
  
<br>
<p align="center">
  👩‍💻 Autora <br>
  <strong>Miriam Mendieta</strong><br>
  Proyecto Intermodular 2026/2027
</p>
