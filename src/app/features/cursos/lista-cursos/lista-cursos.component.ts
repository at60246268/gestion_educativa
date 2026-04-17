import { Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CursoService } from '../../../core/services/curso.service';
import { AuthService } from '../../../core/services/auth.service';
import { InscripcionService } from '../../../core/services/inscripcion.service';
import { Curso } from '../../../core/models/curso.model';
import { Inscripcion } from '../../../core/models/inscripcion.model';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTooltipModule } from '@angular/material/tooltip';
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
    MatTooltipModule,
    EstadoCursoPipe,
    HighlightDirective
  ],
  templateUrl: './lista-cursos.component.html',
  styleUrl: './lista-cursos.component.scss'
})
export class ListaCursosComponent implements OnInit {
  private readonly cursoService = inject(CursoService);
  private readonly authService = inject(AuthService);
  private readonly inscripcionService = inject(InscripcionService);
  private readonly router = inject(Router);
  private readonly snackBar = inject(MatSnackBar);

  cursos: Curso[] = [];
  inscripciones: Inscripcion[] = [];
  loading = true;
  displayedColumns = ['nombre', 'categoria', 'estado', 'duracion', 'acciones'];

  get isAdmin(): boolean { return this.authService.getUserRole() === 'admin'; }
  get isAdminOrProfesor(): boolean {
    const r = this.authService.getUserRole();
    return r === 'admin' || r === 'profesor';
  }
  get isEstudiante(): boolean { return this.authService.getUserRole() === 'estudiante'; }
  get estudianteId(): number { return this.authService.getCurrentUser()?.id ?? 0; }

  ngOnInit(): void {
    this.cargarCursos();
    if (this.isEstudiante) {
      this.cargarInscripciones();
    }
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

  cargarInscripciones(): void {
    this.inscripcionService.getByEstudiante(this.estudianteId).subscribe({
      next: data => { this.inscripciones = data; },
      error: () => { this.inscripciones = []; }
    });
  }

  estaInscrito(cursoId: number): boolean {
    return this.inscripciones.some(i => i.cursoId === cursoId);
  }

  getInscripcionId(cursoId: number): number | undefined {
    return this.inscripciones.find(i => i.cursoId === cursoId)?.id;
  }

  inscribirse(cursoId: number): void {
    const inscripcion: Inscripcion = {
      estudianteId: this.estudianteId,
      cursoId,
      fecha: new Date().toISOString().split('T')[0]
    };
    this.inscripcionService.inscribir(inscripcion).subscribe({
      next: () => {
        this.snackBar.open('¡Inscripción exitosa!', 'OK', { duration: 3000 });
        this.cargarInscripciones();
      },
      error: () => this.snackBar.open('Error al inscribirse', 'Cerrar', { duration: 3000 })
    });
  }

  cancelarInscripcion(cursoId: number): void {
    const id = this.getInscripcionId(cursoId);
    if (!id) return;
    if (!confirm('¿Cancelar tu inscripción en este curso?')) return;
    this.inscripcionService.cancelar(id).subscribe({
      next: () => {
        this.snackBar.open('Inscripción cancelada', 'OK', { duration: 3000 });
        this.cargarInscripciones();
      },
      error: () => this.snackBar.open('Error al cancelar', 'Cerrar', { duration: 3000 })
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
