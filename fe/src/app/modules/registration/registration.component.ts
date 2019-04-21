import { Component, OnInit, NgModule} from '@angular/core';
import { AuthService } from 'src/app/services/auth-service.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators, AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';
import { UserWithPassword } from 'src/app/DTOs/UserMain/UserMain';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
})
export class RegistrationComponent implements OnInit {

  registrationForm:FormGroup;

  user:UserWithPassword;

  constructor(private auth:AuthService) {
    this.user = new UserWithPassword();
    this.registrationForm = new FormGroup({
      'email': new FormControl(
        this.user.email,
        [Validators.required,Validators.email]
      ),
      'name': new FormControl(
        this.user.name,
        [Validators.required]
      ),
      'password':new FormControl(
        this.user.password,
        [Validators.required]
      ),
      'repeat':new FormControl(
        '',
        [Validators.required]
      ),
      'role':new FormControl(
        this.user.role,
        [Validators.required]
      ),
      'remember':new FormControl(
        false
      )
    },{validators:RegistrationComponent.equalPasswordsValidator})
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
  static equalPasswordsValidator(control:FormControl){
    const password = control.get('password');
    const repeat = control.get('repeat');
    return  password && repeat && password.value == control.value ?{ equalPasswords1:true} :null;
  }
  modelChanged(){
    console.log(this.registrationForm.errors);
  }
}

