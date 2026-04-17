import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { Inscripcion } from '../models/inscripcion.model';

@Injectable({ providedIn: 'root' })
export class InscripcionService {
  private readonly http = inject(HttpClient);
  private readonly API = 'http://localhost:3000/inscripciones';

  getByEstudiante(estudianteId: number): Observable<Inscripcion[]> {
    return this.http.get<Inscripcion[]>(`${this.API}?estudianteId=${estudianteId}`).pipe(
      catchError(err => throwError(() => err))
    );
  }

  inscribir(inscripcion: Inscripcion): Observable<Inscripcion> {
    return this.http.post<Inscripcion>(this.API, inscripcion).pipe(
      catchError(err => throwError(() => err))
    );
  }

  cancelar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API}/${id}`).pipe(
      catchError(err => throwError(() => err))
    );
  }
}
