import { Pipe, PipeTransform } from '@angular/core';

/**
 * Pipe personalizado: transforma el estado técnico de un curso
 * en una etiqueta visual amigable con emoji.
 * Uso: {{ curso.estado | estadoCurso }}
 */
@Pipe({
  name: 'estadoCurso',
  standalone: true
})
export class EstadoCursoPipe implements PipeTransform {
  transform(estado: string): string {
    const etiquetas: Record<string, string> = {
      activo: '✅ Activo',
      inactivo: '❌ Inactivo',
      pendiente: '⏳ Pendiente'
    };
    return etiquetas[estado] ?? estado;
  }
}
