import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { Curso } from '../models/curso.model';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class CursoService {
  private readonly http = inject(HttpClient);
  private readonly API = `${environment.apiUrl}/cursos`;

  getAll(): Observable<Curso[]> {
    return this.http.get<Curso[]>(this.API).pipe(
      catchError(err => throwError(() => err))
    );
  }

  getById(id: number): Observable<Curso> {
    return this.http.get<Curso>(`${this.API}/${id}`).pipe(
      catchError(err => throwError(() => err))
    );
  }

  create(curso: Curso): Observable<Curso> {
    return this.http.post<Curso>(this.API, curso).pipe(
      catchError(err => throwError(() => err))
    );
  }

  update(id: number, curso: Curso): Observable<Curso> {
    return this.http.put<Curso>(`${this.API}/${id}`, curso).pipe(
      catchError(err => throwError(() => err))
    );
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API}/${id}`).pipe(
      catchError(err => throwError(() => err))
    );
  }
}
