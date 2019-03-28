import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth-service.service';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import {Task} from '../models/task';
import {Project} from '../models/project';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http:HttpClient, private auth:AuthService) { }

  getUsers():Observable<User[]>{
    return this.http.get<User[]>('/api/users');
  }
  saveUser(user:User):Observable<User>{
    return this.http.post<User>('/api/users',user);
  }
  deleteUserById(idUser:number):Observable<void>{
    return this.http.delete<void>('/api/users/'+idUser);
  }
  getUserById(idUser:number):Observable<User>{
    return this.http.get<User>('/api/users/'+idUser);
  }
}
