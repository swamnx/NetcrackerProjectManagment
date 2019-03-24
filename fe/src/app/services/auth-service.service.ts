import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import { Router } from '@angular/router';

@Injectable(
  {providedIn: 'root'}
)
export class AuthService {
  login = '';
  id = -1;
  token = '';
  status = '';
  authenticated = false;
  role ='';
  email ='';
  constructor(private http: HttpClient, private router: Router) {

  }
  authenticate(email, passwordp){
    let test ={good:true,login:'swamnx'};
    if(test.good==true){
      this.login=test.login;
      this.authenticated=true;
      return('ok');
    }
    else{
      return('problem with server validation');
    }
  }
  register(emailp,passwordp){
    let test ={good:true, login:'swamnx'};
    if(test.good==true){
      return('ok');
    }
    else{
      return('problem with server validation');
    }

  }
  authenticateNot() {
    this.login = '';
    this.id = -1;
    this.token = '';
    this.status = '';
    this.authenticated = false;
    this.role ='';
    this.router.navigateByUrl('/');
}

}
