# 📊 Guía de Exposición — Sistema de Gestión Educativa

---

## 🏗️ ¿DE QUÉ TRATA EL PROYECTO?

Sistema web fullstack de gestión educativa que permite administrar **usuarios** (admin, profesor, estudiante) y **cursos** con control de acceso basado en roles.

### Stack tecnológico

| Capa | Tecnología | Versión | Para qué sirve |
|------|-----------|---------|----------------|
| **Frontend** | Angular | 17 | Framework SPA — componentes, rutas, formularios |
| **Lenguaje** | TypeScript | ~5.2 | Tipado estático sobre JavaScript |
| **UI** | Angular Material | 17 | Componentes visuales (tablas, forms, botones) |
| **Estilos** | SCSS | — | CSS con variables y anidamiento |
| **HTTP Mock** | JSON Server + Node.js | — | Simula una API REST con autenticación JWT |
| **Seguridad** | JWT (jsonwebtoken) | — | Tokens firmados para autenticación stateless |
| **Cifrado** | bcrypt (concepto) | — | Contraseñas nunca viajan en texto plano |

### Arquitectura del proyecto

```
gestion-educativa/
├── src/
│   └── app/
│       ├── core/                ← Capa de dominio
│       │   ├── models/          ← Interfaces TypeScript (User, Curso)
│       │   ├── services/        ← HTTP Services (AuthService, UserService, CursoService)
│       │   ├── guards/          ← Protección de rutas (AuthGuard, RoleGuard, LoginGuard)
│       │   └── interceptors/    ← Middleware HTTP (JwtInterceptor)
│       ├── features/            ← Módulos funcionales (lazy loading)
│       │   ├── auth/            ← Login + Registro
│       │   ├── cursos/          ← CRUD cursos
│       │   ├── usuarios/        ← CRUD usuarios
│       │   └── dashboard/       ← Panel principal
│       ├── layout/              ← Navbar dinámico
│       ├── shared/              ← Pipes y Directivas reutilizables
│       └── pages/               ← 404, Acceso denegado
├── server.cjs                   ← API Node.js con JWT real
├── db.json                      ← Base de datos en archivo JSON
└── environments/                ← URLs por entorno (dev/prod)
```

---

## ▶️ PASO 1 — Abrir el proyecto

1. Abrir **VS Code**
2. Ir a `Archivo → Abrir carpeta` y seleccionar `D:\interfacesex2\gestion-educativa`
3. Abrir **dos terminales** (Ctrl + Ñ → botón `+` para la segunda)

---

## ▶️ PASO 2 — Instalar dependencias (solo la primera vez)

En **Terminal 1**:
```powershell
cd d:\interfacesex2\gestion-educativa
npm install
```
Esperar hasta que aparezca `added X packages`.

---

## ▶️ PASO 3 — Iniciar la API (Terminal 1)

```powershell
cd d:\interfacesex2\gestion-educativa
node server.cjs
```

**Resultado esperado:**
```
🚀 API con JWT corriendo en http://localhost:3000
📋 Usuarios: admin@edu.com/admin123 | profesor@edu.com/prof123 | ...
```

> ⚠️ **NO cerrar esta terminal.** Debe quedar abierta toda la exposición.

---

## ▶️ PASO 4 — Iniciar Angular (Terminal 2)

```powershell
cd d:\interfacesex2\gestion-educativa
npm start
```

**Resultado esperado:**
```
Application bundle generation complete.
➜  Local:   http://localhost:4200/
```

> ⚠️ **NO cerrar esta terminal tampoco.**

---

## ▶️ PASO 5 — Abrir la aplicación

Abrir el navegador en: **http://localhost:4200**

Se redirige automáticamente a `/login` ✅

---

## 🎬 GUIÓN DE DEMOSTRACIÓN

### Demo 1 — LoginGuard (ruta pública protegida)
1. Intentar ir a `http://localhost:4200/dashboard` sin iniciar sesión
2. **Resultado:** redirige automáticamente a `/login` → AuthGuard funcionando

---

### Demo 2 — Login con JWT
1. Ingresar: `admin@edu.com` / `admin123` → clic en **Iniciar Sesión**
2. Abrir **DevTools (F12) → Network**
3. Mostrar la petición `POST /auth/login`
4. Mostrar el **token JWT** en la respuesta
5. **Resultado:** redirige al Dashboard

---

### Demo 3 — RoleGuard (control por roles)
1. Cerrar sesión e iniciar como `profesor@edu.com` / `prof123`
2. Intentar ir manualmente a `http://localhost:4200/usuarios`
3. **Resultado:** redirige a `/acceso-denegado` → RoleGuard funcionando

---

### Demo 4 — HttpInterceptor JWT automático
1. Iniciar sesión como admin
2. Abrir **DevTools → Network → XHR**
3. Navegar a **Cursos**
4. Mostrar la petición `GET /cursos`
5. En **Headers** mostrar: `Authorization: Bearer eyJ...` → Interceptor funcionando

---

### Demo 5 — CRUD de Cursos (GET, POST, PUT, DELETE)
1. Ir a **Cursos** → tabla con datos → `GET /cursos` ✅
2. Clic en **+ Nuevo Curso** → completar formulario → Guardar → `POST /cursos` ✅
3. Clic en editar (✏️) de un curso → modificar → Actualizar → `PUT /cursos/:id` ✅
4. Clic en eliminar (🗑️) → confirmar → `DELETE /cursos/:id` ✅

---

### Demo 6 — Formulario dinámico
1. En el formulario de curso, cambiar **Estado → Inactivo**
2. **Resultado:** aparece con animación el campo "Motivo de inactivación"
3. Intentar guardar sin motivo → botón bloqueado → validación activa
4. Cambiar a **Activo** → el campo desaparece dinámicamente

---

### Demo 7 — Pipe personalizado y Directiva
1. En la lista de Cursos mostrar la columna **Estado**:
   - `activo` → `✅ Activo` (pipe `estadoCurso`)
   - `inactivo` → `❌ Inactivo`
   - `pendiente` → `⏳ Pendiente`
2. Pasar el cursor sobre una fila → se resalta en verde (directiva `appHighlight`)

---

### Demo 8 — Registro de usuario
1. En la pantalla de login, clic en **"Regístrate aquí"** (parte inferior)
2. Redirige a `/registro`
3. Completar: nombre, correo, contraseña, rol
4. Clic en **Registrarse**
5. **Resultado:** mensaje verde de éxito → redirige automáticamente al login
6. Mostrar en **DevTools → Network** la petición `POST /auth/register` con status 201
7. Iniciar sesión con el nuevo usuario creado

---

### Demo 9 — Página 404
1. Ir a `http://localhost:4200/cualquier-cosa-que-no-existe`
2. **Resultado:** página 404 personalizada

---

## 🌐 RUTAS HTTP DE LA API

### Probar desde PowerShell

#### Login — obtener token JWT
```powershell
Invoke-RestMethod -Uri "http://localhost:3000/auth/login" `
  -Method Post `
  -ContentType "application/json" `
  -Body '{"email":"admin@edu.com","password":"admin123"}'
```

#### Obtener todos los cursos (GET)
```powershell
Invoke-RestMethod -Uri "http://localhost:3000/cursos"
```

#### Obtener curso por ID (GET)
```powershell
Invoke-RestMethod -Uri "http://localhost:3000/cursos/1"
```

#### Crear un curso (POST) — requiere token
```powershell
$token = (Invoke-RestMethod -Uri "http://localhost:3000/auth/login" `
  -Method Post -ContentType "application/json" `
  -Body '{"email":"admin@edu.com","password":"admin123"}').token

Invoke-RestMethod -Uri "http://localhost:3000/cursos" `
  -Method Post `
  -ContentType "application/json" `
  -Headers @{ Authorization = "Bearer $token" } `
  -Body '{"nombre":"Nuevo Curso","descripcion":"Desc","categoria":"Tech","estado":"activo","duracion":20,"profesorId":2}'
```

#### Actualizar un curso (PUT) — requiere token
```powershell
Invoke-RestMethod -Uri "http://localhost:3000/cursos/1" `
  -Method Put `
  -ContentType "application/json" `
  -Headers @{ Authorization = "Bearer $token" } `
  -Body '{"nombre":"Curso Actualizado","descripcion":"Nueva desc","categoria":"Tech","estado":"activo","duracion":35,"profesorId":2}'
```

#### Eliminar un curso (DELETE) — requiere token
```powershell
Invoke-RestMethod -Uri "http://localhost:3000/cursos/4" `
  -Method Delete `
  -Headers @{ Authorization = "Bearer $token" }
```

#### Registrar nuevo usuario (POST) — público
```powershell
Invoke-RestMethod -Uri "http://localhost:3000/auth/register" `
  -Method Post `
  -ContentType "application/json" `
  -Body '{"nombre":"Ana Torres","email":"ana@edu.com","password":"ana123","rol":"estudiante"}'
```

#### Registrar con correo duplicado — debe dar 409
```powershell
Invoke-RestMethod -Uri "http://localhost:3000/auth/register" `
  -Method Post `
  -ContentType "application/json" `
  -Body '{"nombre":"Otro","email":"admin@edu.com","password":"x","rol":"admin"}'
```

#### Obtener todos los usuarios (GET)
```powershell
Invoke-RestMethod -Uri "http://localhost:3000/users"
```

#### Intentar POST sin token — debe dar 401
```powershell
Invoke-RestMethod -Uri "http://localhost:3000/cursos" `
  -Method Post `
  -ContentType "application/json" `
  -Body '{"nombre":"Sin token"}'
```

---

## 🔑 PUNTOS CLAVE A EXPLICAR EN LA EXPOSICIÓN

### Sobre TypeScript y POO
> *"Usamos interfaces TypeScript para modelar las entidades `User` y `Curso`, garantizando tipado estricto en toda la aplicación. Los servicios son clases con inyección de dependencias (`inject()`), siguiendo el principio de responsabilidad única."*

### Sobre las Rutas y Lazy Loading
> *"Cada módulo funcional se carga solo cuando el usuario navega a él — esto se llama lazy loading. Si el usuario nunca va a `/usuarios`, ese código nunca se descarga. Los módulos se definen con `loadComponent` y `loadChildren`."*

### Sobre los Guards
> *"Tenemos tres guards. `AuthGuard` verifica que exista un token válido antes de entrar a cualquier ruta privada. `RoleGuard` lee el campo `data.roles` de la definición de la ruta y lo compara con el rol del usuario actual. `LoginGuard` evita que alguien ya autenticado vea la pantalla de login."*

### Sobre el Interceptor JWT
> *"El `HttpInterceptor` es un middleware HTTP de Angular. Intercepta TODAS las peticiones salientes antes de que lleguen al servidor y les agrega automáticamente el header `Authorization: Bearer <token>`. Si el servidor responde con un 401, cierra la sesión y redirige al login."*

### Sobre el Formulario Dinámico
> *"Cuando el usuario selecciona estado 'Inactivo', usamos `valueChanges` para escuchar el cambio en tiempo real. Llamamos `setValidators(Validators.required)` para activar la validación y `clearValidators()` para desactivarla. Así el formulario se adapta dinámicamente sin recargar la página."*

### Sobre el Registro de Usuarios
> *"El endpoint `POST /auth/register` recibe nombre, correo, contraseña y rol. Antes de crear el usuario valida que el correo no esté registrado — si ya existe retorna un 409 Conflict. En el frontend el componente `RegisterComponent` usa un formulario reactivo con validaciones: correo válido, contraseña mínimo 6 caracteres. Al registrarse exitosamente redirige automáticamente al login usando `setTimeout` con el `Router` de Angular."*

### Sobre los Environments
> *"Tenemos dos archivos de entorno: `environment.ts` para desarrollo apuntando al servidor mock en `localhost:3000`, y `environment.prod.ts` para producción apuntando al microservicio Spring Boot en `localhost:8070/api/v1`. Angular reemplaza automáticamente el archivo según el comando de build: `ng serve` usa development, `ng build` usa production. Los servicios nunca tienen la URL hardcodeada, siempre leen `environment.apiUrl`."*

### Sobre `ApiResponse<T>`
> *"Cuando conectemos con el backend real Spring Boot, todos los endpoints devuelven una envoltura genérica: `{ success, message, data, timestamp }`. Tenemos el modelo `ApiResponse<T>` ya definido en TypeScript con genérico, de modo que `ApiResponse<User[]>` representa una lista de usuarios y `ApiResponse<Curso>` representa un solo curso. Esto nos permite tipar correctamente las respuestas del microservicio."*

---

## ⚠️ ERRORES COMUNES Y SOLUCIONES

| Error | Causa | Solución |
|---|---|---|
| `No hay cursos registrados` | La API no está corriendo | Ejecutar `node server.cjs` en Terminal 1 |
| `Credenciales inválidas` | La API no está corriendo | Igual que el anterior |
| `EADDRINUSE: port 3000` | La API ya está corriendo | No hacer nada, ya funciona |
| `ng: command not found` | Angular CLI no instalado | `npm install -g @angular/cli` |
| Pantalla en blanco | Angular no está corriendo | Ejecutar `npm start` en Terminal 2 |
| Token expirado | Pasó más de 1 hora | Cerrar sesión y volver a ingresar |
