import { Injectable } from '@angular/core';
import {HttpClient, HttpParams, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import { Router } from '@angular/router';
import { UserServiceService } from './user-service.service';
import { ProjectServiceService } from './project-service.service';
import { TaskServiceService } from './task-service.service';
import { UserAuth,UserWithPassword,AuthToken} from '../DTOs/UserMain/UserMain';

@Injectable(
  {providedIn: 'root'}
)
export class AuthService {

  authenticated = false;
  problem = '';
  user:UserAuth;
  token:string='';

  constructor(private http: HttpClient, private router: Router) {
    let user = JSON.parse(localStorage.getItem('user'));
    let authenticated = JSON.parse(localStorage.getItem('authenticated'));
    let token = localStorage.getItem('token');
    if(user && authenticated && token){
      this.user=user;
      this.authenticated=true;
      this.token=token;
    }
  }
  private getToken(user:UserWithPassword):Observable<AuthToken>{
    return this.http.post<AuthToken>('api/users/auth/token',user);
  }
  private registerUser(user:UserWithPassword):Observable<AuthToken>{
    return this.http.post<AuthToken>('api/users/auth/signup',user);
  }
  private getUserAuth():Observable<UserAuth>{
    return this.http.get<UserAuth>('api/users/auth/user');
  }
  public authenticate(emailp, passwordp,remember):void{
    let user = new UserWithPassword();
    user.email=emailp;
    user.password=passwordp;
    this.getToken(user).subscribe(
      (value)=>{
        this.token=value.token;
        this.getUserAuth().subscribe(
          (value)=>{
            if(remember==true){
              localStorage.setItem('user',JSON.stringify(value));
              localStorage.setItem('authenticated',JSON.stringify(true));
              localStorage.setItem('token',this.token);
            }
            this.user=value;
            this.authenticated = true;
            this.problem='';
            this.router.navigateByUrl('/');
          },
          (error)=>{
            this.problem=error.status;
          }
        )
      },
      (error)=>{
        this.problem=error.status;
      }
    )
  }
  public register(emailp,namep,passwordp,rolep,remember):void{
    let user = new UserWithPassword();
    user.email=emailp;
    user.name=namep;
    user.password=passwordp;
    user.role=rolep;
    this.registerUser(user).subscribe(
      (value)=>{
        this.token=value.token;
        this.getUserAuth().subscribe(
          (value)=>{
            if(remember==true){
              localStorage.setItem('user',JSON.stringify(value));
              localStorage.setItem('authenticated',JSON.stringify(true));
              localStorage.setItem('token',this.token);
            }
            this.user=value;
            this.authenticated = true;
            this.problem='';
            this.router.navigateByUrl('/');
          },
          (error)=>{
            this.problem=error.status;
          }
        )
      },
      (error)=>{
        this.problem=error.status;
      }
    )
  }
  public authenticateNot():void {
    localStorage.clear();
    this.authenticated = false;
    this.problem='';
    this.user=null;
    this.router.navigateByUrl('/login');
  }
  public authenticateNotProblem(problem:string):void {
    localStorage.clear();
    this.authenticated = false;
    this.problem=problem;
    this.user=null;
    this.router.navigateByUrl('/login');
  }

}
