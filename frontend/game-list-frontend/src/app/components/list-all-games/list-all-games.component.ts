import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Game } from '../../interfaces/Game.interface';
import { Subject, takeUntil } from 'rxjs';
import { GameService } from '../../services/game.service';

@Component({
  selector: 'app-list-all-games',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './list-all-games.component.html',
  styleUrl: './list-all-games.component.css'
})
export class ListAllGamesComponent {

  games!: Game[];

  private unsubscribe$ = new Subject<void>();

  constructor(private gameService: GameService) {}

  ngOnInit(): void {
    this.gameService.getGamesFromUser().pipe(takeUntil(this.unsubscribe$)).subscribe(
      (response) => {
        console.log('Informacion obtenida', response);
        this.games = response.data;
      },
      (error) => console.log(`Error`, error)
    );
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
}
