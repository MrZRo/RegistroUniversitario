# 🎓 Registro Universitario – Backend

Sistema backend para la **gestión de registros estudiantiles universitarios**, diseñado con arquitectura limpia y tecnologías modernas. Permite realizar operaciones **CRUD** completas sobre entidades académicas con validaciones, documentación automática y estructura escalable.

---

## 🛠 Tecnologías Utilizadas

- **Lenguaje:** Java 17  
- **Framework Backend:** Spring Boot 3.4.3  
- **Base de Datos:** PostgreSQL 15  
- **ORM:** Hibernate + Spring Data JPA  
- **Documentación API:** Swagger / OpenAPI 2.5.0  
- **Dependencias adicionales:**  
  - Lombok (reducción de código boilerplate)  
  - Maven (gestión de dependencias y construcción)

---

## 🚀 Instalación y Ejecución

### 1. Clonar el Repositorio

```bash
git clone https://github.com/MrZro/RegistroUniversitario.git
cd registro-universitario
```

### 2. Configurar la Base de Datos

- Crea la base de datos en PostgreSQL:

```sql
CREATE DATABASE RegistroUniversitario;
```

- Edita el archivo `src/main/resources/application.properties` con tus credenciales:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/RegistroUniversitario
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

### 3. Ejecutar la Aplicación

```bash
mvn clean install
mvn spring-boot:run
```

---

## 📚 Documentación de la API

Una vez iniciada la aplicación, puedes acceder a la documentación interactiva:

- **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)  
- **OpenAPI JSON:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 🏗 Estructura del Proyecto

```bash
src/
├── main/
│   ├── java/com/universidad/
│   │   ├── controller/      # Controladores REST (endpoints)
│   │   ├── dto/             # Data Transfer Objects (DTOs)
│   │   ├── model/           # Entidades JPA que representan tablas
│   │   ├── repository/      # Interfaces DAO (Spring Data JPA)
│   │   ├── service/         # Lógica de negocio y validaciones
│   │   └── validation/      # Validaciones personalizadas y excepciones
│   └── resources/           # Archivos de configuración y propiedades
```

---

## 🌟 Características Principales

- ✅ **CRUD Completo de Estudiantes:** Crear, leer, actualizar y eliminar registros.
- 🔐 **Validación de Datos:** Validaciones automáticas en controladores y servicios.
- 📑 **Documentación de API:** Swagger y OpenAPI integrados para exploración y pruebas.
- 🧱 **Arquitectura por Capas:** Separación clara entre controladores, servicios y repositorios.
- 🔄 **Uso de DTOs:** Seguridad y eficiencia en el intercambio de datos.
- ❌ **Manejo Centralizado de Errores:** Respuestas uniformes y controladas para errores de validación y excepciones.
- 💡 **Preparado para Escalar:** Ideal para agregar nuevas entidades como docentes, materias o cursos.

---

## 🔌 Ejemplos de Endpoints

```http
GET /api/estudiantes           # Listar todos los estudiantes
GET /api/estudiantes/{id}      # Obtener estudiante por ID
POST /api/estudiantes          # Crear nuevo estudiante
PUT /api/estudiantes/{id}      # Actualizar estudiante
DELETE /api/estudiantes/{id}   # Eliminar estudiante
```

> Puedes probar estos endpoints directamente desde Swagger UI.

---

## 📦 Ejemplo de JSON para Crear Estudiante

```json
{
  "nombre": "Juan",
  "apellido": "Pérez",
  "email": "juan.perez@umsa.edu.bo",
  "fechaNacimiento": "2000-05-20",
  "ci": "4567890"
}
```

---

## 🧪 Pruebas con Postman

1. Abre Postman
2. Importa el archivo de colección si está disponible (opcional)
3. Realiza peticiones a `http://localhost:8080/api/estudiantes`
4. Usa encabezado `Content-Type: application/json` para los métodos POST/PUT

---

## 🤝 Contribuciones

¡Tus contribuciones son bienvenidas! Para colaborar:

```bash
# Haz un fork del repositorio
git checkout -b feature/mi-nueva-funcionalidad
# Realiza tus cambios
git commit -m "Agrega nueva funcionalidad"
git push origin feature/mi-nueva-funcionalidad
# Abre un Pull Request
```

---

## 🧑‍💻 Autor

Construido con dedicación y buen café ☕ por **Daniel Zeballos**   
[GitHub](https://github.com/MrZRo)  
[MúsicaParaProgramar](https://open.spotify.com/intl-es/track/086myS9r57YsLbJpU0TgK9?si=2cf5a567f64f4f36)
