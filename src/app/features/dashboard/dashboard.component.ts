import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';
import { User } from '../../core/models/user.model';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { DatePipe, TitleCasePipe, LowerCasePipe } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [MatCardModule, MatIconModule, MatButtonModule, DatePipe, TitleCasePipe, LowerCasePipe],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent {
  private readonly authService = inject(AuthService);
  readonly router = inject(Router);

  currentUser: User | null = this.authService.getCurrentUser();
  today = new Date();

  get greeting(): string {
    const hour = new Date().getHours();
    if (hour < 12) return 'Buenos días';
    if (hour < 18) return 'Buenas tardes';
    return 'Buenas noches';
  }

  get menuItems(): { icon: string; label: string; route: string; color: string; roles: string[] }[] {
    const allItems = [
      { icon: 'people', label: 'Usuarios', route: '/usuarios', color: '#1565c0', roles: ['admin'] },
      { icon: 'menu_book', label: 'Cursos', route: '/cursos', color: '#2e7d32', roles: ['admin', 'profesor'] },
      { icon: 'school', label: 'Mis Cursos', route: '/cursos', color: '#f57f17', roles: ['estudiante'] },
    ];
    return allItems.filter(item => item.roles.includes(this.currentUser?.rol ?? ''));
  }
}
