import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private apiRegisterUrl = 'http://localhost:8080/api/user';

  constructor(private http: HttpClient) { }

  saveUser(name: string, email: string, password: string): Observable<any> {
    const data = { name, email, password};
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    return this.http.post(this.apiRegisterUrl, data, {headers});
  }
}
