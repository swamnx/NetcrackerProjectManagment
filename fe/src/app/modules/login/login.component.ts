import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth-service.service';
import { FormGroup, Validators, FormControl, PatternValidator } from '@angular/forms';
import { timer } from 'rxjs';
import { UserWithPassword } from 'src/app/DTOs/UserMain/UserMain';
import { Router } from '@angular/router';
import { recogniseError } from 'src/app/services/exceptions';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  error: string = '';
  sendingData: boolean = false;
  constructor(private auth: AuthService, private router: Router) {

    this.auth.deleteAuthData();

    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email,Validators.maxLength(60)]),
      password: new FormControl('', [Validators.required,Validators.pattern
        ('(?=.*[0-9].*)(?=.*[a-z].*)(?=.*[A-Z].*)[0-9a-zA-Z!@#$%^&*_+-=;.,%:?*]{8,32}')]),
      remember: new FormControl(false)
    });
  }

  ngOnInit() {
  }

  login() {
    this.sendingData = true;
    let user = new UserWithPassword();
    user.email = this.loginForm.controls.email.value;
    user.password = this.loginForm.controls.password.value;
    timer(500).subscribe(
      val => {
        this.auth.getToken(user).subscribe(
          (value) => {
            this.auth.token = value.token;
            this.auth.getUserAuth().subscribe(
              (value) => {
                localStorage.setItem('user', JSON.stringify(value));
                localStorage.setItem('token', this.auth.token);
                this.auth.user = value;
                this.auth.authenticated = true;
                this.router.navigateByUrl('/about');
              },
              (error) => {
                this.auth.deleteAuthData();
                this.sendingData = false;
                this.error = recogniseError(error.status);
              }
            )
          },
          (error) => {
            this.sendingData = false;
            this.error = recogniseError(error.status);
          }
        )
      }
    )
  }
}
