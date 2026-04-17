import { Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';
import { InscripcionService } from '../../core/services/inscripcion.service';
import { CursoService } from '../../core/services/curso.service';
import { User } from '../../core/models/user.model';
import { Curso } from '../../core/models/curso.model';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatChipsModule } from '@angular/material/chips';
import { DatePipe, TitleCasePipe, LowerCasePipe } from '@angular/common';
import { EstadoCursoPipe } from '../../shared/pipes/estado-curso.pipe';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [MatCardModule, MatIconModule, MatButtonModule, MatChipsModule, DatePipe, TitleCasePipe, LowerCasePipe, EstadoCursoPipe],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements OnInit {
  private readonly authService = inject(AuthService);
  private readonly inscripcionService = inject(InscripcionService);
  private readonly cursoService = inject(CursoService);
  readonly router = inject(Router);

  currentUser: User | null = this.authService.getCurrentUser();
  today = new Date();
  cursosInscritos: Curso[] = [];

  get isEstudiante(): boolean { return this.currentUser?.rol === 'estudiante'; }

  ngOnInit(): void {
    if (this.isEstudiante && this.currentUser?.id) {
      this.inscripcionService.getByEstudiante(this.currentUser.id).subscribe({
        next: inscripciones => {
          if (inscripciones.length === 0) return;
          this.cursoService.getAll().subscribe({
            next: cursos => {
              const ids = inscripciones.map(i => i.cursoId);
              this.cursosInscritos = cursos.filter(c => ids.includes(c.id!));
            }
          });
        }
      });
    }
  }

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
