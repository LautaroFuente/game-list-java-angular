import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private apiGamesUrl = 'http://localhost:8080/api/game';

  constructor( private http: HttpClient) { }

  saveGame(name:string, genre:string, realeaseYear:number, email:string): Observable<any> {
    const data = { name, genre, realeaseYear};
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    return this.http.post(`${this.apiGamesUrl}/add/${email}`, data, {headers});
  }

  getGamesFromUser(): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    return this.http.get(`${this.apiGamesUrl}/user`,{ headers });
  }

  getAllGames(): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    return this.http.get(`${this.apiGamesUrl}/all`,{ headers });
  }

  deleteGame(gameId:number): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    return this.http.delete(`${this.apiGamesUrl}/delete/${gameId}`,{ headers });
  }
}
