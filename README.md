# Forum Hub API

## Descripción
Foro Hub es una API REST que permite la gestión de un foro de discusión especializado. La aplicación permite a los usuarios crear, leer, actualizar y eliminar tópicos de discusión. 

Cada tópico está asociado a un curso específico y mantiene un registro del autor y el estado de la discusión.

## Características
- Gestión completa de tópicos (CRUD)
- Autenticación mediante JWT (JSON Web Tokens)
- Estados de tópicos (NO_RESPONDIDO, RESUELTO, CERRADO)
- Documentación con Swagger/OpenAPI
- Paginación y ordenamiento de resultados

## Tecnologías
- Java 21
- Spring Boot 3.2.1
- Spring Security + JWT
- MySQL
- Flyway para migraciones
- Swagger/OpenAPI para documentación

## Requisitos Previos
- JDK 21
- Maven 3.x
- MySQL
 
## Instalación 

### 1. Clonar el repositorio
```bash
git clone https://github.com/UNIJUAN/forum-hub.git
cd forum-hub
2. Configurar Base de Datos

Copiar application.properties.example a application.properties
Actualizar las credenciales en application.properties:

propertiesCopyspring.datasource.url=jdbc:mysql://localhost:3305/forum_hub?createDatabaseIfNotExist=true
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
3. Ejecutar la aplicación
bashCopymvn clean install
mvn spring-boot:run
Documentación y Endpoints
Autenticación
httpCopyPOST /auth/login
Content-Type: application/json

{
    "login": "admin@forum.com",
    "password": "admin123"
}
Respuesta de Autenticación
jsonCopy{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
Endpoints de Tópicos
Crear Tópico
httpCopyPOST /topics
Authorization: Bearer {token}
Content-Type: application/json

{
    "title": "Ejemplo de tópico",
    "message": "Contenido del tópico",
    "author": "usuario@ejemplo.com",
    "course": "Spring Boot"
}
Show Image
Listar Tópicos
httpCopyGET /topics?page=0&size=10
Authorization: Bearer {token}
Obtener Tópico
httpCopyGET /topics/{id}
Authorization: Bearer {token}
Actualizar Tópico
httpCopyPUT /topics
Authorization: Bearer {token}
Content-Type: application/json

{
    "id": 1,
    "title": "Título actualizado",
    "message": "Mensaje actualizado"
}
Eliminar Tópico
httpCopyDELETE /topics/{id}
Authorization: Bearer {token}
Documentación API
La documentación completa de la API está disponible en Swagger:
Copyhttp://localhost:8080/swagger-ui.html
[![La-documentaci-n-completa-de-la-API-est-disponible-en-Swagger.png](https://i.postimg.cc/Qd6srT0q/La-documentaci-n-completa-de-la-API-est-disponible-en-Swagger.png)](https://postimg.cc/Bj1RTXW8)
Estructura del Proyecto
Copyforum-hub/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/forum_hub/
│   │   │       ├── controller/
│   │   │       ├── domain/
│   │   │       ├── dto/
│   │   │       ├── infra/
│   │   │       ├── repository/
│   │   │       └── service/
│   │   └── resources/
│   │       └── db/migration/
│   └── test/
├── pom.xml
└── README.md
![Captura de pantalla 2025-01-19 102713](https://github.com/user-attachments/assets/89ded6fe-c567-4fa0-a4fc-0b20cecc4af4)

Base de Datos
La estructura de la base de datos se gestiona con Flyway migrations ubicadas en:
Copysrc/main/resources/db/migration/
Migraciones incluidas:

V1__create-table-topics.sql: Estructura de tópicos
V2__create-table-users.sql: Estructura de usuarios
V3__insert-admin-user.sql: Usuario administrador inicial

Seguridad

Autenticación basada en JWT
Tokens con expiración configurable (24 horas por defecto)
Endpoints protegidos requieren token válido en header: Authorization: Bearer {token}

Estado del Proyecto

 Estructura base implementada
 Configuración de seguridad JWT
 CRUD de tópicos
 Documentación Swagger
 Migraciones Flyway
