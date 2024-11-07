import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {
    
  private token: string | null = null; 

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    if (this.token) {
      const cloned = req.clone({
        headers: req.headers.set('Authorization', `Bearer ${this.token}`)
      });
      return next.handle(cloned);
    }
  
    return new Observable(observer => {
      window.electron.ipcRenderer.send('get-data', 'token');

      window.electron.ipcRenderer.once('send-data', (event, token) => {
        this.token = token;
        const cloned = req.clone({
          headers: req.headers.set('Authorization', `Bearer ${this.token}`)
        });

        next.handle(cloned).subscribe(
          (event) => {
            observer.next(event); // Emitir el evento HTTP
          },
          (err) => {
            observer.error(err); // Emitir el error, si ocurre
          },
          () => {
            observer.complete(); // Completar el observable
          }
        );
      });
    });
  }
}


