import { Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CursoService } from '../../../core/services/curso.service';
import { AuthService } from '../../../core/services/auth.service';
import { Curso } from '../../../core/models/curso.model';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { EstadoCursoPipe } from '../../../shared/pipes/estado-curso.pipe';
import { HighlightDirective } from '../../../shared/directives/highlight.directive';

@Component({
  selector: 'app-lista-cursos',
  standalone: true,
  imports: [
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatProgressSpinnerModule,
    MatSnackBarModule,
    EstadoCursoPipe,
    HighlightDirective
  ],
  templateUrl: './lista-cursos.component.html',
  styleUrl: './lista-cursos.component.scss'
})
export class ListaCursosComponent implements OnInit {
  private readonly cursoService = inject(CursoService);
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);
  private readonly snackBar = inject(MatSnackBar);

  cursos: Curso[] = [];
  loading = true;
  displayedColumns = ['nombre', 'categoria', 'estado', 'duracion', 'acciones'];

  get isAdmin(): boolean { return this.authService.getUserRole() === 'admin'; }
  get isAdminOrProfesor(): boolean {
    const r = this.authService.getUserRole();
    return r === 'admin' || r === 'profesor';
  }

  ngOnInit(): void {
    this.cargarCursos();
  }

  cargarCursos(): void {
    this.loading = true;
    this.cursoService.getAll().subscribe({
      next: data => { this.cursos = data; this.loading = false; },
      error: () => {
        this.loading = false;
        this.snackBar.open('Error al cargar cursos', 'Cerrar', { duration: 3000 });
      }
    });
  }

  nuevo(): void { this.router.navigate(['/cursos/nuevo']); }

  editar(id: number): void { this.router.navigate(['/cursos/editar', id]); }

  eliminar(id: number): void {
    if (!confirm('¿Eliminar este curso?')) return;
    this.cursoService.delete(id).subscribe({
      next: () => {
        this.snackBar.open('Curso eliminado', 'OK', { duration: 3000 });
        this.cargarCursos();
      },
      error: () => this.snackBar.open('Error al eliminar', 'Cerrar', { duration: 3000 })
    });
  }
}
