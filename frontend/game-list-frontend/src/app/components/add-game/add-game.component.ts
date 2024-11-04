import { Component, OnInit, OnDestroy } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  FormsModule,
  Validators,
} from '@angular/forms';
import { RouterModule } from '@angular/router';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { GameService } from '../../services/game.service';
import { ApiResponseDTO } from '../../interfaces/ApiResponseDTO.interface';
import { Game } from '../../interfaces/Game.interface';
import { UserGlobalService } from '../../services/user-global.service';

@Component({
  selector: 'app-add-game',
  standalone: true,
  imports: [ ReactiveFormsModule, FormsModule, RouterModule ],
  templateUrl: './add-game.component.html',
  styleUrl: './add-game.component.css'
})
export class AddGameComponent implements OnInit, OnDestroy{

  formGame!: FormGroup;
  name: string = "";
  genre: string = "";
  realeaseYear: number = 0;

  private unsubscribe$ = new Subject<void>();

  constructor(private fb: FormBuilder, private gameService: GameService, private userGlobal: UserGlobalService) {}

  ngOnInit(): void {
    this.formGame = this.fb.group({
      name: ['', Validators.required],
      genre: ['', Validators.required],
      realeaseYear:['', Validators.required]
    });
  }

  onSubmit(): void {
    if(this.formGame.valid){
      const {name, genre, realeaseYear} = this.formGame.value;
      const email = this.userGlobal.getEmail();
      this.gameService.saveGame(name, genre, realeaseYear, email).pipe(takeUntil(this.unsubscribe$)).subscribe(
        (response) =>{
          const apiResponse: ApiResponseDTO<Game> = response;
          console.log(apiResponse.data, apiResponse.message);
        },
        (error) =>{
          console.log('Error al guardar el juego', error);
        });
    }else {
      console.log('Formulario no válido'); 
    }
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
}
