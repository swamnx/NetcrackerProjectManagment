import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth-service.service';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { timer } from 'rxjs';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit {

  loginForm:FormGroup;
  
  constructor(private auth:AuthService) {
    this.loginForm = new FormGroup({
      email: new FormControl('',[Validators.required,Validators.email]),
      password:new FormControl('',[Validators.required]),
      remember:new FormControl(false)
    });
  }

  ngOnInit() {
    localStorage.clear();
  }

  login(){
    this.auth.authenticate(
      this.loginForm.controls.email.value,
      this.loginForm.controls.password.value,
      this.loginForm.controls.remember.value
      );
  }
}
