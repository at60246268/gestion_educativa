export interface Curso {
  id?: number;
  nombre: string;
  descripcion: string;
  categoria: string;
  estado: 'activo' | 'inactivo' | 'pendiente';
  duracion: number;
  profesorId: number;
  motivo?: string;
  createdAt?: string;
}
