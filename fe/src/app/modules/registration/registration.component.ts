import { Component, OnInit} from '@angular/core';
import { AuthService } from 'src/app/services/auth-service.service';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { timer } from 'rxjs';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
})
export class RegistrationComponent implements OnInit {

  registrationForm:FormGroup;

  constructor(private auth:AuthService, private fb:FormBuilder) {
    this.registrationForm = fb.group({
      email: new FormControl('',[Validators.required,Validators.email]),
      name: new FormControl('',[Validators.required]),
      password:new FormControl('',[Validators.required]),
      repeat:new FormControl('',[Validators.required]),
      role:new FormControl('',[Validators.required]),
      remember:new FormControl(false)
    },{validator: confirmPasswordValidator});
  }
  ngOnInit() {
    localStorage.clear();
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