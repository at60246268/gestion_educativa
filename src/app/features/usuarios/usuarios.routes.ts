import { Routes } from '@angular/router';

export const usuariosRoutes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./lista-usuarios/lista-usuarios.component').then(m => m.ListaUsuariosComponent)
  },
  {
    path: 'nuevo',
    loadComponent: () =>
      import('./form-usuario/form-usuario.component').then(m => m.FormUsuarioComponent)
  },
  {
    path: 'editar/:id',
    loadComponent: () =>
      import('./form-usuario/form-usuario.component').then(m => m.FormUsuarioComponent)
  }
];
