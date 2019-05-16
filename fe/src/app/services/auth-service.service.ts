import { Injectable } from '@angular/core';
import {HttpClient, HttpParams, HttpHeaders} from "@angular/common/http";
import {Observable, timer} from "rxjs";
import { Router } from '@angular/router';
import { UserServiceService } from './user-service.service';
import { ProjectServiceService } from './project-service.service';
import { TaskServiceService } from './task-service.service';
import {recogniseError} from './exceptions';
import { UserAuth,UserWithPassword,AuthToken} from '../DTOs/UserMain/UserMain';

@Injectable(
  {providedIn: 'root'}
)
export class AuthService {

  authenticated:boolean;
  problem:string;
  user:UserAuth;
  token:string;
  ready:boolean;

  constructor(private http: HttpClient, private router: Router) {
    this.authenticated=false;
    this.problem='';
    this.ready=true;
    let user = JSON.parse(localStorage.getItem('user'));
    let authenticated = JSON.parse(localStorage.getItem('authenticated'));
    let token = localStorage.getItem('token');
    if(user && authenticated && token){
      this.user=user;
      this.authenticated=true;
      this.token=token;
    }
    else{
      this.user=null;
      this.token='';
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
    this.ready=false;
    timer(500).subscribe(
      val=>{
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
                this.ready=true;
              },
              (error)=>{
                this.problem=recogniseError(error.status);
                this.ready=true;
              }
            )
          },
          (error)=>{
            this.problem=recogniseError(error.status);
            this.ready=true;
          }
        )
      }
    )
  }
  public register(emailp,namep,passwordp,rolep,remember):void{
    let user = new UserWithPassword();
    user.email=emailp;
    user.name=namep;
    user.password=passwordp;
    user.role=rolep;
    this.ready=false;
    timer(500).subscribe(
      val=>{
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
            this.ready=true;
          },
          (error)=>{
            this.problem=recogniseError(error.status);
            this.ready=true;
          }
        )
      },
      (error)=>{
        this.problem=recogniseError(error.status);
        this.ready=true;
      }
    )
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
