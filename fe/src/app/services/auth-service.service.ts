import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import { Router } from '@angular/router';
import { UserServiceService } from './user-service.service';
import { ProjectServiceService } from './project-service.service';
import { TaskServiceService } from './task-service.service';
import { User } from '../models/user';
import { UserForAuth } from '../DTOs/user';

@Injectable(
  {providedIn: 'root'}
)
export class AuthService {
  authenticated = false;
  problem = '';
  user:User;
  token = '';

  constructor(private http: HttpClient, private router: Router) {

  }
  private sign(user:UserForAuth):Observable<User>{
    return this.http.post<User>('api/users/sign',user);
  }
  private createUser(user:UserForAuth):Observable<User>{
    return this.http.post<User>('api/users',user);
  }
  public authenticate(emailp, passwordp):void{
    let user = new UserForAuth();
    user.email=emailp;
    user.password=passwordp;
    this.sign(user).subscribe(
      (value)=>{
        this.user=value;
        this.authenticated = true;
        this.problem='';
        this.router.navigateByUrl('/');
      },
      (error)=>{
        this.problem=error.status;
      }
    )
  }
  public register(emailp,namep,passwordp,rolep):void{
    let user = new UserForAuth();
    user.email=emailp;
    user.name=namep;
    user.password=passwordp;
    user.role=rolep;
    this.createUser(user).subscribe(
      (value)=>{
        this.user=value;
        this.authenticated = true;
        this.problem='';
        this.router.navigateByUrl('/');
      },
      (error)=>{
        this.problem=error.status;
      }
    )
  }
  public authenticateNot():void {
    this.authenticated = false;
    this.problem='';
    this.user=null;
    this.router.navigateByUrl('/login');
  }
  public authenticateNotProblem(problem:string):void {
    this.authenticated = false;
    this.problem=problem;
    this.user=null;
    this.router.navigateByUrl('/login');
  }

}
