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
import { RegisterService } from '../../services/register.service';
import { ApiResponseDTO } from '../../interfaces/ApiResponseDTO.interface';
import { User } from '../../interfaces/User.interface';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ ReactiveFormsModule, FormsModule, RouterModule ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit, OnDestroy{

  formRegister!: FormGroup;
  name: string = '';
  email: string = '';
  password: string = '';

  private unsubscribe$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private registerService: RegisterService,
    private router: Router
  ){}

  ngOnInit(): void {
    this.formRegister = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if(this.formRegister.valid){
      const {name, email, password} = this.formRegister.value;
      this.registerService.saveUser(name, email, password).pipe(takeUntil(this.unsubscribe$)).subscribe(
        (response) =>{
          const ApiResponse: ApiResponseDTO<User> = response;
          console.log(ApiResponse.success, ApiResponse.message);
          this.router.navigate(['/login']);
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
