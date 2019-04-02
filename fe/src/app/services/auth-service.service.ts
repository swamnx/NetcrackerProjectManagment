import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import { Router } from '@angular/router';
import { UserServiceService } from './user-service.service';
import { ProjectServiceService } from './project-service.service';
import { TaskServiceService } from './task-service.service';
import { User } from '../models/user';

@Injectable(
  {providedIn: 'root'}
)
export class AuthService {
  authenticated = false;
  problem = '';
  user:User=null;
  constructor(private http: HttpClient, private router: Router) {

  }
  private sign(email:string,password:string):Observable<User>{
    let params = new HttpParams();
    params.set("email",email);
    params.set("password",password);
    return this.http.get<User>('http://localhost:8083/api/users/sign',{params:params});
  }
  private createUser(user:User):Observable<User>{
    return this.http.post<User>('api/users',user);
  }
  authenticate(email, password){
    this.sign(email,password).subscribe(
      (value)=>{
        this.user=value;
        this.authenticated =true;
        this.problem='';
      },
      (error)=>{
        this.problem=error;
      }
    )
  }
  register(emailp,passwordp,rolep){
    let user = new User();
    user.email=emailp;
    user.password=passwordp;
    user.role=rolep;
    this.createUser(user).subscribe(
      (value)=>{
        this.user=value;
        this.authenticated = true;
        this.problem='';
        console.log(this.user);
      },
      (error)=>{
        this.problem=error;
      }
    )
  }
  authenticateNot() {
    this.authenticated = false;
    this.problem='';
    this.user=null;
    this.router.navigateByUrl('/login');
  }
  authenticateNotProblem(problem:string) {
    this.authenticated = false;
    this.problem=problem;
    this.user=null;
    this.router.navigateByUrl('/login');
  }

}
