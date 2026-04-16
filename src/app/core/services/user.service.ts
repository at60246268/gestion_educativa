import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { User } from '../models/user.model';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class UserService {
  private readonly http = inject(HttpClient);
  private readonly API = `${environment.apiUrl}/users`;

  getAll(): Observable<User[]> {
    return this.http.get<User[]>(this.API).pipe(
      catchError(err => throwError(() => err))
    );
  }

  getById(id: number): Observable<User> {
    return this.http.get<User>(`${this.API}/${id}`).pipe(
      catchError(err => throwError(() => err))
    );
  }

  create(user: User): Observable<User> {
    return this.http.post<User>(this.API, user).pipe(
      catchError(err => throwError(() => err))
    );
  }

  update(id: number, user: User): Observable<User> {
    return this.http.put<User>(`${this.API}/${id}`, user).pipe(
      catchError(err => throwError(() => err))
    );
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API}/${id}`).pipe(
      catchError(err => throwError(() => err))
    );
  }
}
