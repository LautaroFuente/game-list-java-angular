import { Component, OnInit, OnDestroy } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  FormsModule,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { AuthService } from '../../services/auth.service';
import { UserGlobalService } from '../../services/user-global.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ ReactiveFormsModule, FormsModule, RouterModule ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit, OnDestroy{

  formLogin!: FormGroup;
  email: string = '';
  password: string = '';

  private unsubscribe$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private userGlobal: UserGlobalService
  ){}

  ngOnInit(): void {
    this.formLogin = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if(this.formLogin.valid){
      const {email, password} = this.formLogin.value;
      this.authService.auth(email, password).pipe(takeUntil(this.unsubscribe$)).subscribe(
        (response) =>{
          console.log('Usuario guardado correctamente');
          this.router.navigate(['/login-menu']);
          const { token, name, email } = response;
          this.userGlobal.login(name, email, token);
        },
        (error) =>{
          console.log('Error al guardar el usuario', error);
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
