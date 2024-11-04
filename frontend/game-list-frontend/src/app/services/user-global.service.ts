import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ipcRenderer } from 'electron';
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
    ipcRenderer.send('set-data', 'token', token);
    this.name = name;
    this.email = email;
    this.token = token;
  }

  logout() {
    ipcRenderer.send('set-data', 'token', null);
    this.name = "";
    this.email = "";
    this.token = "";
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

