import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

/**
 * Controla el acceso por rol.
 * La ruta debe incluir `data: { roles: ['admin'] }` para restringir el acceso.
 */
export const roleGuard: CanActivateFn = (route) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  const requiredRoles: string[] = route.data['roles'] ?? [];
  const userRole = authService.getUserRole();

  if (requiredRoles.length === 0 || requiredRoles.includes(userRole ?? '')) {
    return true;
  }
  return router.createUrlTree(['/acceso-denegado']);
};
