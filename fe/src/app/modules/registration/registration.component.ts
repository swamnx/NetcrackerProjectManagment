import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth-service.service';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { timer } from 'rxjs';
import { Router } from '@angular/router';
import { UserWithPassword } from 'src/app/DTOs/UserMain/UserMain';
import { recogniseError } from 'src/app/services/exceptions';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
})
export class RegistrationComponent implements OnInit {

  registrationForm: FormGroup;
  error: string = '';
  sendingData: boolean = false;

  constructor(private auth: AuthService, private router: Router, private fb: FormBuilder) {

    this.auth.deleteAuthData();

    this.registrationForm = fb.group({
      email: new FormControl('', [Validators.required, Validators.email]),
      name: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
      repeat: new FormControl('', [Validators.required]),
      role: new FormControl('', [Validators.required]),
      remember: new FormControl(false)
    }, { validator: confirmPasswordValidator });
  }

  ngOnInit() {
  }

  register() {
    this.sendingData = true;
    let user = new UserWithPassword();
    user.email = this.registrationForm.controls.email.value;
    user.name = this.registrationForm.controls.name.value;
    user.password = this.registrationForm.controls.password.value;
    user.role = this.registrationForm.controls.role.value;
    timer(500).subscribe(
      val => {
        this.auth.registerUser(user).subscribe(
          (value) => {
            this.auth.token = value.token;
            this.auth.getUserAuth().subscribe(
              (value) => {
                if (this.registrationForm.controls.remember.value) {
                  localStorage.setItem('user', JSON.stringify(value));
                  localStorage.setItem('authenticated', JSON.stringify(true));
                  localStorage.setItem('token', this.auth.token);
                }
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
export function confirmPasswordValidator(formGroup: FormGroup) {
  const controlPassword = formGroup.controls['password'];
  const controlRepeat = formGroup.controls['repeat'];
  if (controlPassword.untouched || controlPassword.value == '') {
    return;
  }
  if (controlPassword.errors && !controlRepeat.errors) {
    return;
  }
  if (controlPassword.errors && !controlRepeat.errors.notEqual) {
    return;
  }
  if (controlPassword.value !== controlRepeat.value) {
    controlRepeat.setErrors({ notEqual: true });
  } else {
    controlRepeat.setErrors(null);
  }
}