import { Routes } from '@angular/router';
import { NotLoginMenuComponent } from './components/not-login-menu/not-login-menu.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { LoginMenuComponent } from './components/login-menu/login-menu.component';
import { ListAllGamesComponent } from './components/list-all-games/list-all-games.component';
import { ListGamesFromUserComponent } from './components/list-games-from-user/list-games-from-user.component';
import { AddGameComponent } from './components/add-game/add-game.component';
import { ConfigurationComponent } from './components/configuration/configuration.component';

export const routes: Routes = [
    {path: '', component: NotLoginMenuComponent },
    {path: 'register', component: RegisterComponent },
    {path: 'login', component: LoginComponent },
    {path: 'login-menu', component: LoginMenuComponent },
    {path: 'all-games', component: ListAllGamesComponent },
    {path: 'games-from-user', component: ListGamesFromUserComponent },
    {path: 'add-game', component: AddGameComponent },
    {path: 'configuration', component: ConfigurationComponent }
];
