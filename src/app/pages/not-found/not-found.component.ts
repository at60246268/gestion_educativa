import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-not-found',
  standalone: true,
  imports: [RouterLink, MatButtonModule, MatIconModule],
  template: `
    <div class="not-found">
      <mat-icon>error_outline</mat-icon>
      <h1>404</h1>
      <p>La página que buscas no existe.</p>
      <a mat-raised-button color="primary" routerLink="/dashboard">
        <mat-icon>home</mat-icon> Volver al inicio
      </a>
    </div>
  `,
  styles: [`
    .not-found {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      min-height: 80vh;
      gap: 16px;
      text-align: center;

      mat-icon { font-size: 80px; height: 80px; width: 80px; color: #1565c0; }
      h1 { font-size: 5rem; margin: 0; color: #1565c0; font-weight: 700; }
      p { font-size: 1.1rem; color: #666; }
    }
  `]
})
export class NotFoundComponent {}
