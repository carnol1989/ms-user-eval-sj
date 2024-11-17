# ms-user-eval-sj

**Authors**:       Carlos Nole

**Document Version**:  1.0.0

**Supported Spring Boot Version**: 3.2.11

### Descripción y funciones
RESTful API for creating users.

1. User registration.
2. Get a list of users.

![Diagrama de solución](src/main/resources/img/ds-ms-users-sj.jpg)

### Prerequisitos
Follow the instructions below to start the project as a consumption example.
- JDK 17
- Maven 3.9.6
- First create a user with the endpoint and then check it with the get user list endpoint.

### ¿Cómo usar?
- Construir el proyecto (Build Project)
- Hacer `clean install` con maven
- Hacer clic en `Run`
- Puede probar los sgtes. endpoints:

**1. User registration**:  
Request:
```bash
curl --location 'http://localhost:8081/api/v1/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Juan Rodriguez",
    "email": "juan.rodri@dominio.cl",
    "password": "Hunter22",
    "phones": [
        {
            "number": "1234567",
            "cityCode": "1",
            "contryCode": "57"
        },
        {
            "number": "1234567",
            "cityCode": "3",
            "contryCode": "47"
        }
    ]
}'
```  
Response:
```json
{
  "id": "aaf59014-bced-4d52-ba00-402c5227d0f3",
  "name": "Juan Rodriguez",
  "email": "juan.rodri@dominio.cl",
  "password": "Hunter22",
  "created": "2024-11-17T09:04:57.191747",
  "modified": "2024-11-17T09:04:57.228613",
  "lastLogin": "2024-11-17T09:04:57.191747",
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWFuLnJvZHJpQGRvbWluaW8uY2wiLCJpYXQiOjE3MzE4NTIyOTcsImV4cCI6MTczMTg1NTg5N30.YbPIySkMY6IMTNaCjaEjKgTBsc6nB6FIFnCxFfz5O64",
  "active": true,
  "phones": [
    {
      "id": 1,
      "number": "1234567",
      "cityCode": "1",
      "contryCode": "57"
    },
    {
      "id": 2,
      "number": "1234567",
      "cityCode": "3",
      "contryCode": "47"
    }
  ]
}
```
**2. Get a list of users**  
Request:
```bash
curl --location 'http://localhost:8081/api/v1/users'
```
Response:
```json
[
  {
    "id": "aaf59014-bced-4d52-ba00-402c5227d0f3",
    "name": "Juan Rodriguez",
    "email": "juan.rodri@dominio.cl",
    "password": "Hunter22",
    "created": "2024-11-17T09:04:57.191747",
    "modified": "2024-11-17T09:04:57.228613",
    "lastLogin": "2024-11-17T09:04:57.191747",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWFuLnJvZHJpQGRvbWluaW8uY2wiLCJpYXQiOjE3MzE4NTIyOTcsImV4cCI6MTczMTg1NTg5N30.YbPIySkMY6IMTNaCjaEjKgTBsc6nB6FIFnCxFfz5O64",
    "active": true,
    "phones": [
      {
        "id": 1,
        "number": "1234567",
        "cityCode": "1",
        "contryCode": "57"
      },
      {
        "id": 2,
        "number": "1234567",
        "cityCode": "3",
        "contryCode": "47"
      }
    ]
  }
]
```

### Documentación APIs
- [API Docs (yml)](http://localhost:8081/v3/api-docs)
- [API Docs (GUI)](http://localhost:8081/swagger-ui/index.html)

### Dependencias
| Nombre                              | Versión | 
|-------------------------------------|---------|
| spring-boot-starter-web             | 3.2.11  |
| spring-boot-starter-test            | 3.2.11  |
| spring-boot-starter-data-jpa        | 3.2.11  |
| spring-boot-starter-validation      | 3.2.11  |
| jjwt-api                                  | 0.11.5  |
| jjwt-impl                           | 0.11.5   |
| jjwt-jackson                           | 0.11.5   |
| lombok                              | 1.18.34 |
| h2                              | 2.2.224 |
| mockito-core                        | 5.3.1   |
| springdoc-openapi-starter-webmvc-ui | 2.3.0   |

### Licencia
This project is licensed under GNU.