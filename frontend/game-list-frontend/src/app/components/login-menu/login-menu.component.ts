import { Component, OnInit, OnDestroy } from '@angular/core';
import { RouterModule } from '@angular/router';
import { UserGlobalService } from '../../services/user-global.service';
import { Router } from '@angular/router';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { ApiResponseDTO } from '../../interfaces/ApiResponseDTO.interface';
import { User } from '../../interfaces/User.interface';

@Component({
  selector: 'app-login-menu',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './login-menu.component.html',
  styleUrl: './login-menu.component.css'
})
export class LoginMenuComponent implements OnInit, OnDestroy{

  private name!:string;

  private unsubscribe$ = new Subject<void>();

  constructor(private userGlobal: UserGlobalService, private router: Router){}

  ngOnInit(): void {
    this.name = this.userGlobal.getName();
  }

  getName(): string {
    return this.name;
  }

  onDeleteAccount(): void {
    this.userGlobal.deleteAccount().pipe(takeUntil(this.unsubscribe$)).subscribe(
      (response) => {
        const apiResponse: ApiResponseDTO<User> = response;
        console.log(apiResponse.success, apiResponse.message);
      }, 
      (error) => {
        console.log(error);
      });
    this.router.navigate(['/']);
  }

  logout(): void {
    this.userGlobal.logout();
    this.router.navigate(['/']);
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
}
