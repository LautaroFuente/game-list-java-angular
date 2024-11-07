import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserGlobalService {
  private name :string = "";
  private email :string = "";
  private token :string = "";

  private apiDeleteUserUrl = 'http://localhost:8080/api/user/delete/';

  constructor(private http: HttpClient) {}

  login(name: string, email: string, token: string ) {
    this.name = name;
    this.email = email;
    this.token = token;
    window.electron.ipcRenderer.send('set-data', 'token', token);
  }

  logout() {
    this.name = "";
    this.email = "";
    this.token = "";
    window.electron.ipcRenderer.send('set-data', 'token', null);
  }

  deleteAccount(): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    const emailUserToDelete = this.email;
    this.logout();
    return this.http.delete(`${this.apiDeleteUserUrl}${emailUserToDelete}`,{ headers });
  }

  getName(): string {
    return this.name;
  }

  getEmail(): string {
    return this.email;
  }

  getToken(): string {
    return this.token;
  }
}

