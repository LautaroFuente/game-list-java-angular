import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { UserGlobalService } from '../../services/user-global.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-menu',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './login-menu.component.html',
  styleUrl: './login-menu.component.css'
})
export class LoginMenuComponent implements OnInit{

  private name!:string;

  constructor(private userGlobal: UserGlobalService, private router: Router){}

  ngOnInit(): void {
    this.name = this.userGlobal.getName();
  }

  getName(): string {
    return this.name;
  }

  logout(): void {
    this.userGlobal.logout();
    this.router.navigate(['/']);
  }
}
