import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';
import { roleGuard } from './core/guards/role.guard';
import { loginGuard } from './core/guards/login.guard';

export const routes: Routes = [
  // Redirección raíz
  { path: '', redirectTo: '/login', pathMatch: 'full' },

  // Ruta pública — loginGuard evita acceso si ya está autenticado
  {
    path: 'login',
    loadComponent: () =>
      import('./features/auth/login/login.component').then(m => m.LoginComponent),
    canActivate: [loginGuard]
  },

  // Ruta pública — registro de nuevo usuario
  {
    path: 'registro',
    loadComponent: () =>
      import('./features/auth/register/register.component').then(m => m.RegisterComponent),
    canActivate: [loginGuard]
  },

  // Ruta privada — solo usuarios autenticados
  {
    path: 'dashboard',
    loadComponent: () =>
      import('./features/dashboard/dashboard.component').then(m => m.DashboardComponent),
    canActivate: [authGuard]
  },

  // Módulo Usuarios — solo admin (authGuard + roleGuard)
  {
    path: 'usuarios',
    loadChildren: () =>
      import('./features/usuarios/usuarios.routes').then(m => m.usuariosRoutes),
    canActivate: [authGuard, roleGuard],
    data: { roles: ['admin'] }
  },

  // Módulo Cursos — admin y profesor
  {
    path: 'cursos',
    loadChildren: () =>
      import('./features/cursos/cursos.routes').then(m => m.cursosRoutes),
    canActivate: [authGuard]
  },

  // Página de acceso denegado
  {
    path: 'acceso-denegado',
    loadComponent: () =>
      import('./pages/acceso-denegado/acceso-denegado.component').then(m => m.AccesoDenegadoComponent)
  },

  // Wildcard — página 404
  {
    path: '**',
    loadComponent: () =>
      import('./pages/not-found/not-found.component').then(m => m.NotFoundComponent)
  }
];
