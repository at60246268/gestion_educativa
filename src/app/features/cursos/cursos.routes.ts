import { Routes } from '@angular/router';
import { roleGuard } from '../../core/guards/role.guard';

export const cursosRoutes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./lista-cursos/lista-cursos.component').then(m => m.ListaCursosComponent)
  },
  {
    path: 'nuevo',
    loadComponent: () =>
      import('./form-curso/form-curso.component').then(m => m.FormCursoComponent),
    canActivate: [roleGuard],
    data: { roles: ['admin', 'profesor'] }
  },
  {
    path: 'editar/:id',
    loadComponent: () =>
      import('./form-curso/form-curso.component').then(m => m.FormCursoComponent),
    canActivate: [roleGuard],
    data: { roles: ['admin', 'profesor'] }
  }
];
