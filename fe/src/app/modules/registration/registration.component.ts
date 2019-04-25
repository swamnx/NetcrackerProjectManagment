import { Component, OnInit, NgModule} from '@angular/core';
import { AuthService } from 'src/app/services/auth-service.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators, AbstractControl, ValidationErrors, ValidatorFn, FormBuilder } from '@angular/forms';
import { UserWithPassword } from 'src/app/DTOs/UserMain/UserMain';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
})
export class RegistrationComponent implements OnInit {

  registrationForm:FormGroup;

  user:UserWithPassword;

  constructor(private auth:AuthService, private fb:FormBuilder) {
    this.user = new UserWithPassword();
    this.registrationForm = fb.group({
      email: new FormControl(
        this.user.email,
        [Validators.required,Validators.email]
      ),
      name: new FormControl(
        this.user.name,
        [Validators.required]
      ),
      password:new FormControl(
        this.user.password,
        [Validators.required]
      ),
      repeat:new FormControl(
        '',
        [Validators.required]
      ),
      role:new FormControl(
        this.user.role,
        [Validators.required]
      ),
      remember:new FormControl(
        false
      )
    },{validator: confirmPasswordValidator});
  }

  ngOnInit() {
  }
  register(){
    this.auth.register(
      this.registrationForm.controls.email.value,
      this.registrationForm.controls.name.value,
      this.registrationForm.controls.password.value,
      this.registrationForm.controls.role.value,
      this.registrationForm.controls.remember.value);
  }
}
export function confirmPasswordValidator(formGroup: FormGroup) {
      const controlPassword = formGroup.controls['password'];
      const controlRepeat = formGroup.controls['repeat'];
      if(controlPassword.untouched || controlPassword.value==''){
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