import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from "@angular/common/http";
import { Observable, timer } from "rxjs";
import { Router } from '@angular/router';
import { UserServiceService } from './user-service.service';
import { ProjectServiceService } from './project-service.service';
import { TaskServiceService } from './task-service.service';
import { recogniseError } from './exceptions';
import { UserAuth, UserWithPassword, AuthToken } from '../DTOs/UserMain/UserMain';

@Injectable(
  { providedIn: 'root' }
)
export class AuthService {

  authenticated: boolean = false;
  user: UserAuth;
  token: string;

  constructor(private http: HttpClient, private router: Router) {
    let user = JSON.parse(localStorage.getItem('user'));
    let token = localStorage.getItem('token');
    if (user && token) {
      this.user = user;
      this.authenticated = true;
      this.token = token;
    }
    else {
      this.deleteAuthData();
    }
  }
  public getToken(user: UserWithPassword): Observable<AuthToken> {
    return this.http.post<AuthToken>('api/users/auth/token', user);
  }
  public registerUser(user: UserWithPassword): Observable<AuthToken> {
    return this.http.post<AuthToken>('api/users/auth/signup', user);
  }
  public getUserAuth(): Observable<UserAuth> {
    return this.http.get<UserAuth>('api/users/auth/user');
  }
  public deleteAuthData() {
    this.user = null;
    this.authenticated = false;
    this.token = null;
    localStorage.clear();
  }

  public authenticateNot(): void {
    this.deleteAuthData();
    this.router.navigateByUrl('/login');
  }
  public authenticateNotProblem(problem: string): void {
    this.deleteAuthData();
    this.router.navigateByUrl('/login');
  }

}
