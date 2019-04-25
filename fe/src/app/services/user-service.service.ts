import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AuthService } from './auth-service.service';
import { Observable } from 'rxjs';
import { Project,User} from 'src/app/DTOs/UserMain/UserMain';
@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http:HttpClient, private auth:AuthService) { }

  getUsers():Observable<any[]>{
    return this.http.get<any[]>('api/users');
  }
  updateUser(user:User):Observable<User>{
    return this.http.patch<User>('api/users',user);
  }
  getUserById(idUser:number):Observable<User>{
    return this.http.get<User>('api/users/'+idUser);
  }
}
