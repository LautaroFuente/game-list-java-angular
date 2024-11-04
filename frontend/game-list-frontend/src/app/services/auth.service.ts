import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiLoginUrl = 'http://localhost:8080/api/login';

  constructor(private http: HttpClient) { }

  auth(email: string, password: string): Observable<any> {
    const data = {email, password};
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    return this.http.post(this.apiLoginUrl, data, {headers});
  }
}
