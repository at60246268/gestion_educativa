# 🎓 Sistema de Gestión Educativa

Aplicación web SPA desarrollada con **Angular 17** para la gestión centralizada de cursos y usuarios en una institución educativa. Incluye autenticación JWT, control de acceso por roles y una API REST simulada con json-server.

---

## 👥 Integrantes del equipo

| Nombre | Rol en el proyecto |
|---|---|
| Integrante 1 | Desarrollo de autenticación y guards |
| Integrante 2 | Módulo de cursos y servicios REST |
| Integrante 3 | Módulo de usuarios y UI/UX |

---

## 🛠️ Tecnologías utilizadas

| Tecnología | Versión | Uso |
|---|---|---|
| Angular | 17 | Framework principal (standalone components) |
| TypeScript | 5.2 | Lenguaje de programación |
| Angular Material | 17 | Librería de componentes UI |
| RxJS | 7.8 | Manejo de Observables y flujo de datos |
| json-server | 0.17 | API REST simulada |
| jsonwebtoken | 9.0 | Generación y verificación de tokens JWT |

---

## 📁 Estructura del proyecto

```
gestion-educativa/
├── src/
│   └── app/
│       ├── core/                        # Lógica central reutilizable
│       │   ├── guards/
│       │   │   ├── auth.guard.ts        # Protege rutas privadas
│       │   │   ├── role.guard.ts        # Controla acceso por rol
│       │   │   └── login.guard.ts       # Evita re-login si ya autenticado
│       │   ├── interceptors/
│       │   │   └── jwt.interceptor.ts   # Agrega Bearer token a cada petición
│       │   ├── models/
│       │   │   ├── user.model.ts        # Interfaces TypeScript de usuario
│       │   │   └── curso.model.ts       # Interfaces TypeScript de curso
│       │   └── services/
│       │       ├── auth.service.ts      # Login, logout, token, sesión
│       │       ├── user.service.ts      # CRUD usuarios
│       │       └── curso.service.ts     # CRUD cursos
│       ├── features/                    # Módulos funcionales (lazy loading)
│       │   ├── auth/login/              # Pantalla de inicio de sesión
│       │   ├── dashboard/               # Panel principal por rol
│       │   ├── usuarios/                # Lista + formulario de usuarios
│       │   └── cursos/                  # Lista + formulario de cursos
│       ├── layout/navbar/               # Barra de navegación superior
│       ├── pages/
│       │   ├── not-found/               # Página 404
│       │   └── acceso-denegado/         # Página 403
│       └── shared/
│           ├── pipes/
│           │   └── estado-curso.pipe.ts # Pipe personalizado de estado
│           └── directives/
│               └── highlight.directive.ts # Directiva de resaltado
├── db.json                              # Base de datos simulada
├── server.cjs                           # Servidor API con JWT
└── angular.json                         # Configuración Angular CLI
```

---

## ⚙️ Requisitos previos

- **Node.js** v18 o superior → [Descargar](https://nodejs.org)
- **Angular CLI** v17 → `npm install -g @angular/cli`
- **Git** → [Descargar](https://git-scm.com)

---

## 🚀 Instalación y ejecución

### 1. Clonar el repositorio
```bash
git clone https://github.com/at60246268/gestion_educativa.git
cd gestion_educativa
```

### 2. Instalar dependencias
```bash
npm install
```

### 3. Iniciar la API REST (Terminal 1)
```bash
npm run api
```
> La API queda disponible en `http://localhost:3000`

### 4. Iniciar la aplicación Angular (Terminal 2)
```bash
npm start
```
> La aplicación queda disponible en `http://localhost:4200`

---

## 👤 Usuarios de prueba

| Email | Contraseña | Rol | Acceso |
|---|---|---|---|
| admin@edu.com | admin123 | Administrador | Dashboard + Usuarios + Cursos |
| profesor@edu.com | prof123 | Profesor | Dashboard + Cursos |
| estudiante@edu.com | est123 | Estudiante | Solo Dashboard |

---

## 🗺️ Rutas de la aplicación

| Ruta | Componente | Guards | Roles permitidos |
|---|---|---|---|
| `/` | Redirige a `/login` | — | — |
| `/login` | LoginComponent | `loginGuard` | No autenticados |
| `/dashboard` | DashboardComponent | `authGuard` | Todos |
| `/cursos` | ListaCursosComponent | `authGuard` | Todos |
| `/cursos/nuevo` | FormCursoComponent | `authGuard` + `roleGuard` | admin, profesor |
| `/cursos/editar/:id` | FormCursoComponent | `authGuard` + `roleGuard` | admin, profesor |
| `/usuarios` | ListaUsuariosComponent | `authGuard` + `roleGuard` | solo admin |
| `/usuarios/nuevo` | FormUsuarioComponent | `authGuard` + `roleGuard` | solo admin |
| `/usuarios/editar/:id` | FormUsuarioComponent | `authGuard` + `roleGuard` | solo admin |
| `/acceso-denegado` | AccesoDenegadoComponent | — | — |
| `/**` | NotFoundComponent | — | — |

---

## 🔐 Sistema de autenticación JWT

### Flujo de autenticación

```
Usuario → POST /auth/login → API genera token JWT
       ← token + datos de usuario ←
Angular guarda token en localStorage
Cada petición HTTP → HttpInterceptor agrega Authorization: Bearer <token>
Token expira en 1h → HttpInterceptor detecta 401 → logout automático
```

### Guards implementados

| Guard | Función |
|---|---|
| `authGuard` | Bloquea acceso a rutas privadas si no hay sesión activa |
| `roleGuard` | Verifica que el rol del usuario coincida con `data.roles` de la ruta |
| `loginGuard` | Si ya está autenticado, redirige al dashboard en vez de mostrar login |

---

## 🌐 Endpoints de la API

### Autenticación
```
POST   /auth/login          → Recibe email+password, devuelve token JWT
```

### Cursos (requieren token para escritura)
```
GET    /cursos               → Lista todos los cursos
GET    /cursos/:id           → Obtiene un curso por ID
POST   /cursos               → Crea un nuevo curso
PUT    /cursos/:id           → Actualiza un curso
DELETE /cursos/:id           → Elimina un curso
```

### Usuarios (requieren token para escritura)
```
GET    /users                → Lista todos los usuarios
GET    /users/:id            → Obtiene usuario por ID
POST   /users                → Crea un nuevo usuario
PUT    /users/:id            → Actualiza un usuario
DELETE /users/:id            → Elimina un usuario
```

---

## 📋 Criterios de la rúbrica cubiertos

### ✅ Buenas prácticas de desarrollo (4 pts — Sobresaliente)
- Arquitectura modular: `core/`, `features/`, `shared/`, `layout/`
- Separación de responsabilidades: servicios, componentes y modelos independientes
- **Pipe personalizado**: `EstadoCursoPipe` — transforma estados técnicos en etiquetas visuales
- **Directiva personalizada**: `HighlightDirective` — resalta filas al pasar el cursor con `@HostListener`
- Tipado estricto con interfaces TypeScript en todos los modelos

### ✅ Implementación de rutas (4 pts — Sobresaliente)
- **Lazy loading** en todos los módulos funcionales
- **Rutas anidadas** dentro de `/usuarios` y `/cursos`
- **Rutas paramétricas** con `:id` para edición
- **Redirección** raíz `''` → `/login`
- **Ruta wildcard** `**` → página 404
- Ruta `/acceso-denegado` para permisos insuficientes

### ✅ Implementación de guards (4 pts — Sobresaliente)
- **AuthGuard**: valida token en localStorage y verifica expiración
- **RoleGuard**: lee `data.roles` de la ruta y compara con el rol del usuario
- **LoginGuard**: evita que usuarios ya autenticados vean el login
- Redirecciones automáticas: sin sesión → `/login`, sin rol → `/acceso-denegado`

### ✅ Integración con APIs REST (4 pts — Sobresaliente)
- `HttpClient` con tipado genérico en todos los métodos
- Servicios separados por entidad: `AuthService`, `UserService`, `CursoService`
- Operaciones GET, POST, PUT, DELETE implementadas
- `catchError` + `throwError` en cada método de servicio
- `Observables` para gestión del flujo de datos asíncrono

### ✅ Autenticación con token JWT (4 pts — Sobresaliente)
- **HttpInterceptor** (`jwtInterceptor`) inserta el token automáticamente en cada petición
- Token almacenado en `localStorage` con clave segura
- Manejo global de errores 401: logout automático + redirección a `/login`
- Verificación de expiración del token en el cliente mediante decodificación del payload

---

## 🧪 Pruebas funcionales

| Escenario | Resultado esperado | Estado |
|---|---|---|
| Login con credenciales correctas | Redirige al dashboard | ✅ |
| Login con credenciales incorrectas | Muestra mensaje de error | ✅ |
| Acceder a `/dashboard` sin sesión | Redirige a `/login` | ✅ |
| Acceder a `/usuarios` como Profesor | Redirige a `/acceso-denegado` | ✅ |
| Acceder a `/login` ya autenticado | Redirige al `/dashboard` | ✅ |
| Crear un curso nuevo | Se guarda en la API y aparece en la lista | ✅ |
| Editar un curso existente | Se actualiza en la API | ✅ |
| Eliminar un curso | Se elimina de la API y desaparece de la lista | ✅ |
| Seleccionar estado "Inactivo" en formulario | Aparece campo dinámico "Motivo" | ✅ |
| Acceder a ruta inexistente | Muestra página 404 | ✅ |
| Petición HTTP sin token | API responde 401 | ✅ |
