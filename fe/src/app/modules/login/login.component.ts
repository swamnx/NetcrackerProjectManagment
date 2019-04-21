import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth-service.service';
import {MatFormField} from '@angular/material';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { UserWithPassword } from 'src/app/DTOs/UserMain/UserMain';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit {

  loginForm:FormGroup;

  user:UserWithPassword;
  
  constructor(private auth:AuthService, private route:Router, private http:HttpClient) { }

  ngOnInit() {
    this.user = new UserWithPassword();
    this.loginForm = new FormGroup({
      'email': new FormControl(
        this.user.email,
        [Validators.required,Validators.email]
      ),
      'password':new FormControl(
        this.user.password,
        [Validators.required]
      ),
      'remember':new FormControl(
        false
      )
    })
  }

  login(){
      this.auth.authenticate(
        this.loginForm.controls.email.value,
        this.loginForm.controls.password.value,
        this.loginForm.controls.remember.value
      );
  }
}
