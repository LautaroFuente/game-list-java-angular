import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {
    
  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('jwt'); // Obtener el token del localStorage

    if (token) {
      // Si hay un token, clonar la solicitud y agregar el encabezado Authorization
      const cloned = req.clone({
        headers: req.headers.set('Authorization', `Bearer ${token}`)
      });
      return next.handle(cloned); // Continuar con la solicitud clonada
    }

    return next.handle(req); // Si no hay token, continuar con la solicitud original
  }
}

