import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AuthService } from './auth-service.service';
import { Observable } from 'rxjs';
import { Task,Project,Comment,User} from 'src/app/DTOs/UserMain/UserMain';
@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http:HttpClient, private auth:AuthService) { }

  getUsers():Observable<User[]>{
    return this.http.get<User[]>('api/users');
  }
  createUser(user:User):Observable<User>{
    return this.http.post<User>('api/users',user);
  }
  updateUser(user:User):Observable<User>{
    return this.http.patch<User>('api/users',user);
  }
  deleteUserById(idUser:number):Observable<void>{
    return this.http.delete<void>('api/users/'+idUser);
  }
  getUserById(idUser:number):Observable<User>{
    return this.http.get<User>('api/users/'+idUser);
  }
}
