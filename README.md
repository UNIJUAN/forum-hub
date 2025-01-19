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
### 1. Clonar el repositorio:
```bash
git clone https://github.com/UNIJUAN/forum-hub.git
cd forum-hub

2. Configurar Base de Datos

Copiar application.properties.example a application.properties
Actualizar las credenciales en application.properties:
spring.datasource.url=jdbc:mysql://localhost:3305/forum_hub?createDatabaseIfNotExist=true
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password

3. Ejecutar la aplicación
mvn clean install
mvn spring-boot:run

Documentación y Endpoints
Autenticación
POST /auth/login
Content-Type: application/json
{
    "login": "admin@forum.com",
    "password": "admin123"
}

Respuesta de Autenticación
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}

### Endpoints de Tópicos
Crear Tópico
POST /topics
Authorization: Bearer {token}
Content-Type: application/json
{
    "title": "Ejemplo de tópico",
    "message": "Contenido del tópico",
    "author": "usuario@ejemplo.com",
    "course": "Spring Boot"
}
![Ejemplo de petición exitosa en Insomnia](https://github.com/user-attachments/assets/92405935-cd69-46ce-8e54-ccd6532cfaf4)

Listar Tópicos
GET /topics?page=0&size=10
Authorization: Bearer {token}

Obtener Tópico
GET /topics/{id}
Authorization: Bearer {token}

Actualizar Tópico
PUT /topics
Authorization: Bearer {token}
Content-Type: application/json
{
    "id": 1,
    "title": "Título actualizado",
    "message": "Mensaje actualizado"
}

Eliminar Tópico
DELETE /topics/{id}
Authorization: Bearer {token}

Documentación API
La documentación completa de la API está disponible en Swagger:
http://localhost:8080/swagger-ui.html
![Interfaz de Sawgger](https://github.com/user-attachments/assets/9b9ed7b1-df6f-49d0-b804-fc420ab22e7a)


### Estructura del Proyecto
forum-hub/
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
![Estructura en Intellij IDEA](https://github.com/user-attachments/assets/b146e3b6-0efa-41f3-b5d9-70ab6931f50f)


Base de Datos
La estructura de la base de datos se gestiona con Flyway migrations ubicadas en:
src/main/resources/db/migration/

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














