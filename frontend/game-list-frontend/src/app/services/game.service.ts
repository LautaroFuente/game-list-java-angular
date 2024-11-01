import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private apiGamesUrl = 'http://localhost:8080/game';

  constructor( private http: HttpClient) { }

  saveGame(name:string, genre:string, realeaseYear:number): Observable<any> {
    const data = { name, genre, realeaseYear};
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    return this.http.post(`${this.apiGamesUrl}/`, data, {headers});
  }

  getGamesFromUser(): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    return this.http.get(`${this.apiGamesUrl}/`,{ headers });
  }

  getAllGames(): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    return this.http.get(`${this.apiGamesUrl}/`,{ headers });
  }

  deleteGame(gameId:number): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    return this.http.delete(`${this.apiGamesUrl}/${gameId}`,{ headers });
  }
}
