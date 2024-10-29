import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserGlobalService {
  private name :string = "";
  private email :string = "";
  private token :string = "";

  constructor() {}

  login(name: string, email: string, token: string ) {
    localStorage.setItem('token', JSON.stringify(token));
    this.name = name;
    this.email = email;
    this.token = token;
  }

  logout() {
    localStorage.removeItem('token');
    this.name = "";
    this.email = "";
    this.token = "";
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

