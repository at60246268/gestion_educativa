import { Component, inject, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { CursoService } from '../../../core/services/curso.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  selector: 'app-form-curso',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatCardModule,
    MatIconModule,
    MatSnackBarModule
  ],
  templateUrl: './form-curso.component.html',
  styleUrl: './form-curso.component.scss'
})
export class FormCursoComponent implements OnInit, OnDestroy {
  private readonly fb = inject(FormBuilder);
  private readonly cursoService = inject(CursoService);
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);
  private readonly snackBar = inject(MatSnackBar);

  isEditing = false;
  cursoId: number | null = null;
  loading = false;
  mostrarMotivo = false;
  private estadoSub!: Subscription;

  // 'motivo' se incluye desde el inicio sin validadores — se activan dinámicamente
  form = this.fb.group({
    nombre:      ['', [Validators.required, Validators.minLength(3)]],
    descripcion: ['', Validators.required],
    categoria:   ['', Validators.required],
    estado:      ['activo' as 'activo' | 'inactivo' | 'pendiente', Validators.required],
    duracion:    [null as number | null, [Validators.required, Validators.min(1)]],
    profesorId:  [null as number | null, Validators.required],
    motivo:      ['']   // sin validador inicial — se aplica dinámicamente
  });

  ngOnInit(): void {
    // Formulario dinámico: activa/desactiva validación de 'motivo' según el estado
    this.estadoSub = this.form.get('estado')!.valueChanges.subscribe(valor => {
      const motivoCtrl = this.form.get('motivo')!;
      if (valor === 'inactivo') {
        motivoCtrl.setValidators(Validators.required);
        this.mostrarMotivo = true;
      } else {
        motivoCtrl.clearValidators();
        motivoCtrl.setValue('');
        this.mostrarMotivo = false;
      }
      motivoCtrl.updateValueAndValidity();
    });

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.cursoId = +id;
      this.isEditing = true;
      this.cursoService.getById(this.cursoId).subscribe(curso => {
        this.form.patchValue(curso);
        // Si el curso cargado está inactivo, activar el campo dinámico
        if (curso.estado === 'inactivo') {
          const motivoCtrl = this.form.get('motivo')!;
          motivoCtrl.setValidators(Validators.required);
          motivoCtrl.updateValueAndValidity();
          this.mostrarMotivo = true;
        }
      });
    }
  }

  ngOnDestroy(): void {
    this.estadoSub?.unsubscribe();
  }

  onSubmit(): void {
    if (this.form.invalid) return;
    this.loading = true;
    const data = {
      ...this.form.value,
      createdAt: new Date().toISOString().split('T')[0]
    } as any;

    const op = this.isEditing
      ? this.cursoService.update(this.cursoId!, data)
      : this.cursoService.create(data);

    op.subscribe({
      next: () => {
        this.snackBar.open(
          `Curso ${this.isEditing ? 'actualizado' : 'creado'} correctamente`,
          'OK', { duration: 3000 }
        );
        this.router.navigate(['/cursos']);
      },
      error: () => {
        this.loading = false;
        this.snackBar.open('Error al guardar el curso', 'Cerrar', { duration: 3000 });
      }
    });
  }

  cancelar(): void {
    this.router.navigate(['/cursos']);
  }
}
