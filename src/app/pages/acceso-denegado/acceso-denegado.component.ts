import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-acceso-denegado',
  standalone: true,
  imports: [RouterLink, MatButtonModule, MatIconModule],
  template: `
    <div class="denied">
      <mat-icon>lock</mat-icon>
      <h2>Acceso Denegado</h2>
      <p>No tienes permisos para acceder a esta sección.</p>
      <a mat-raised-button color="warn" routerLink="/dashboard">
        <mat-icon>arrow_back</mat-icon> Volver al Dashboard
      </a>
    </div>
  `,
  styles: [`
    .denied {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      min-height: 80vh;
      gap: 16px;
      text-align: center;

      mat-icon { font-size: 80px; height: 80px; width: 80px; color: #f44336; }
      h2 { font-size: 2.5rem; margin: 0; color: #f44336; }
      p { font-size: 1.1rem; color: #666; }
    }
  `]
})
export class AccesoDenegadoComponent {}
