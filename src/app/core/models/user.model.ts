export interface User {
  id?: number;
  nombre: string;
  email: string;
  password?: string;
  rol: 'admin' | 'profesor' | 'estudiante';
  activo?: boolean;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface LoginResponse {
  token: string;
  user: User;
}

export interface RegisterRequest {
  nombre: string;
  email: string;
  password: string;
  rol: 'admin' | 'profesor' | 'estudiante';
}

// Formato de respuesta del backend real (Spring Boot)
export interface LoginResponseBackend {
  token: string;
  tipo: string;
  username: string;
  roles: string[];
  expiracionms: number;
}

// Envolvente genérico que usa el backend real en todos sus endpoints
export interface ApiResponse<T> {
  success: boolean;
  message: string;
  data: T;
  timestamp: string;
}
