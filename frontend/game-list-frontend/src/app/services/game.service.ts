import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor() { }

  saveGame(name:string, genre:string, realeaseYear:number): Observable<any> {

  }

  getGamesFromUser(): Observable<any> {

  }

  getAllGames(): Observable<any> {

  }
}
