# 🧾 API Control de Clientes — OrionTek

Este proyecto es una API RESTful desarrollada en Java con Spring Boot para gestionar el control de clientes y sus direcciones,
 como parte de una prueba técnica solicitada por OrionTek.

##  Características

- Gestión de **Clientes** y sus múltiples **Direcciones**.
- Autenticación y autorización mediante **JWT**.
- Arquitectura en capas: Controller, Service, Repository.
- Manejo centralizado de errores personalizados.
- Código preparado para escalabilidad y mantenibilidad.

## 🛠️ Tecnologías utilizadas

☕ Java 21

🛠️ Maven

🐘 MySQL instalado localmente

🧰 Spring Boot 3.4.5

🔐 Spring Security

🔑 JWT (JSON Web Tokens)

Es nesesario adjustar las peticiones mediante POSTMAN ya he dejado un collection pero sus valores 
deverian ser modificados al antojo de quien prueba esta api

Es necesario ajustar ciertas configuraciones según el entorno en el que se despliegue la aplicación. 
Por ejemplo, la conexión a la base de datos puede configurarse mediante la inyección de credenciales 
en el archivo application.yaml.

Cabe destacar que este proyecto, al estar orientado a pruebas, incluye algunas prácticas que no son 
recomendadas para entornos de producción. Entre ellas se encuentran la exposición directa de credenciales 
de base de datos y secretos (como claves JWT y otras variables sensibles) dentro del código fuente o archivos 
de configuración internos.

Para un entorno productivo, se recomienda externalizar dichas variables mediante archivos de configuración
externos o utilizar un sistema de gestión de secretos (como AWS Secrets Manager, Vault, o variables de entorno 
gestionadas por el servidor de despliegue) para garantizar la seguridad y la escalabilidad de la aplicación.



## 📂 Estructura del proyecto

api-control-customer-info/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/oriontek/
│   │   │       ├── controller/         # Controladores REST (AuthController, CustomersController)
│   │   │       ├── models/             # Entidades JPA (Customer, Address, User)
│   │   │       ├── repository/         # Repositorios Spring Data JPA
│   │   │       ├── services/           # Interfaces y servicios (CustomerService, UserService)
│   │   │       ├── security/           # Seguridad y filtros JWT
│   │   │       ├── exception/          # Excepciones personalizadas
│   │   │       ├── dto/                # Objetos de transferencia de datos (LoginDto, etc.)
│   │   │       └── utils/              # Utilidades (validadores, JwtUtils)
│   │   └── resources/
│   │       ├── application.yml         # Configuración general de la aplicación
│   │       └── logback-spring.xml      # Configuración de los logs
├── .gitignore
├── mvnw / mvnw.cmd                     # Wrappers de Maven
├── pom.xml                             # Dependencias y configuración del proyecto
└── README.md                           # Documentación del proyecto




## 🔐 Seguridad

La autenticación se maneja con JWT. Los endpoints públicos y protegidos están claramente definidos.

### Endpoints públicos:

/auth**


### Endpoints protegidos (requieren JWT):

Todos los restantes

Desarrollado por: [JHON RODERICK MARTINEZ]
Correo: [jhon.martinez.dev@outlook.com]
gitHub: [https://github.com/jhongraph]

