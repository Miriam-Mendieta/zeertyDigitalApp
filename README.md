<p align="center">
  <img width="580" height="350" alt="logo" src="https://github.com/user-attachments/assets/727931ed-895f-43c2-86fb-c8b788f3fa73" />
</p>
<h1 align="center">Zeerty Digital App — MVP</h1>

<p align="center">
  <strong>Gestión básica de activos digitales, movimientos y auditoría.<br>
  Versión MVP con ejecución en Docker o en local con MySQL/H2.</strong>
</p>

---

## 📝 Descripción del Proyecto

Zeerty Digital App es un **Producto Mínimo Viable (MVP)** desarrollado con **Spring Boot**, orientado a validar la funcionalidad esencial de un sistema de gestión de activos digitales.

Este MVP incluye:

- Gestión básica de activos digitales  
- Registro automático de movimientos  
- Auditoría simple  
- Sistema de roles (ADMIN / USER)  
- Interfaz web con Thymeleaf  
- Ejecución profesional mediante Docker (Spring Boot + MySQL + volumen persistente)  
- Ejecución en local con MySQL o H2 (modo desarrollo)

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

# 🐳 Ejecución con Docker

Este MVP incluye un entorno Docker completo:

- **Contenedor Spring Boot**  
- **Contenedor MySQL**  
- **Volumen persistente** para la base de datos  

## 📦 Requisitos

- Docker  
- Docker Compose  

## 🧱 Arquitectura Docker

La aplicación se ejecuta mediante dos contenedores:

### **1. zeerty_app (Spring Boot)**  
- Puerto expuesto: **8081**  
- Conecta a MySQL mediante red interna Docker  

### **2. zeerty_mysql (MySQL 8)**  
- Puerto expuesto: **3306**  
- Volumen persistente: `mysql_data:/var/lib/mysql`  

## 🗂️ docker-compose.yml

```yaml
services:
  mysql:
    image: mysql:8.0
    container_name: zeerty_mysql
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: zeertydb
      MYSQL_USER: zeerty
      MYSQL_PASSWORD: zeerty123
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql

  app:
    image: zeerty-app:latest
    container_name: zeerty_app
    depends_on:
      - mysql
    ports:
      - "8081:8081"
    environment:
      DB_USER: zeerty
      DB_PASS: zeerty123

volumes:
  mysql_data:
```

## 🚀 Cómo ejecutar con Docker

1. Construir la imagen
   ```bash
   docker build -t zeerty-app:latest .
   ```
2. Levantar los servicios
    ```bash
   docker compose up -d
   ```
3. Acceder a la aplicación
     👉 http://localhost:8081

4. Detener los servicios
   ```bash
   docker compose down
    ```
   (La base de datos se conserva gracias al volumen persistente.)
---
# 🖥️ Ejecución en Local (sin Docker)

La aplicación puede ejecutarse en local de dos formas: **H2** (más simple) o **MySQL** (modo profesional).

## 🟩 Opción 1 — Ejecutar con H2 (modo desarrollo)

* ✅ No requiere MySQL
* ✅ No requiere configuración compleja
* ✅ Ideal para pruebas rápidas

### 1. Configurar H2 en `application.properties`

```properties
spring.datasource.url=jdbc:h2:file:./zeertydb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
```

### 2. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

👉 **URL:** http://localhost:8080

## 🟦 Opción 2 — Ejecutar con MySQL (modo profesional sin Docker)

### 1. Crear la base de datos

```sql
CREATE DATABASE zeertydb;
```

### 2. Configurar MySQL en `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/zeertydb
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

👉 **URL:** http://localhost:8080

## 🔐 Credenciales del MVP

### **ADMIN**
* **Usuario:** `admin`
* **Contraseña:** `admin123`

### **USER**
* **Usuario:** `user`
* **Contraseña:** `user123`
---

## 📁 Estructura del Proyecto
```bash
zeertyDigitalApp/
├── src/
│   ├── main/java/com/miriammendieta/zeerty_digital_app/
│   │       ├── config/        # Seguridad y configuración
│   │       ├── controller/    # Controladores MVC
│   │       ├── model/         # Entidades JPA
│   │       ├── repository/    # Repositorios JPA
│   │       └── service/       # Lógica de negocio
│   └── resources/
│       ├── static/            # CSS, imágenes
│       ├── templates/         # Vistas Thymeleaf
│       └── application.properties
├── Dockerfile
├── docker-compose.yml
└── pom.xml

```

### Login
<p align="center">
<img width="300" height="300" alt="login" src="https://github.com/user-attachments/assets/e9a273c3-4750-4571-b5cf-8fb2197d43f0" />
</p>


### Panel de Administración
<p align="center">
<img width="600" height="300" alt="panelAdmin" src="https://github.com/user-attachments/assets/f2f3a774-9712-4446-aa17-0c75211ca249" />
</p>

✨ Funcionalidades del MVP
- Gestión básica de activos digitales

- Registro automático de movimientos

- Auditoría simple

- Roles ADMIN / USER / auditoria

- Interfaz web con Thymeleaf

- Persistencia MySQL (Docker)
---
# 📌 Estado del Proyecto
🔧 MVP funcional  
🐳 Dockerizado completamente  
🗄️ Persistencia MySQL con volumen  
🧪 Modo local con H2 o MySQL  
🚧 Próximas mejoras en desarrollo

---
# 🚀 Roadmap — Evolución del Proyecto (Post-MVP)

---

## 🟩 Fase 1 — Consolidación del MVP *(Corto plazo)*

* [ ] Validaciones avanzadas
* [ ] Manejo centralizado de errores
* [ ] Logs estructurados
* [ ] Auditoría ampliada

---

## 🟦 Fase 2 — Gestión documental avanzada *(Medio plazo)*

* [ ] Subida de documentos reales
* [ ] Previsualización de archivos
* [ ] Descarga directa de documentos
* [ ] Clasificación por categorías
* [ ] Cifrado de archivos en repososo

---

## 🟨 Fase 3 — Automatización y productividad *(Medio plazo)*

* [ ] OCR para extracción de datos en facturas
* [ ] Recordatorios y notificaciones automáticas
* [ ] Panel de métricas y analíticas
* [ ] Generación e exportación de informes en PDF

---

## 🟪 Fase 4 — Integraciones externas *(Largo plazo)*

* [ ] APIs de verificación de dominios y certificados
* [ ] Conexión con almacenamiento en nube (Google Drive / OneDrive)
* [ ] Soporte para almacenamiento de objetos (Amazon S3 / S3-Compatible)
* [ ] Integración con sistemas ERP / CRM

---

## 🟥 Fase 5 — Versión empresarial *(Largo plazo)*

* [ ] Arquitectura Multi-tenant
* [ ] Transición a arquitectura de Microservicios
* [ ] Despliegue y orquestación con Kubernetes
* [ ] Autenticación avanzada (2FA y OAuth2 / OpenID Connect)
* [ ] Auditoría completa exportable e inmutable


## 📄 Licencia 
  Este proyecto se distribuye bajo licencia MIT.
  
<br>
<p align="center">
  👩‍💻 Autora <br>
  <strong>Miriam Mendieta</strong><br>
  Proyecto Intermodular 2026/2027
</p>
