import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth-service.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProjectServiceService {

  constructor(private http:HttpClient, private auth:AuthService) { }

  getUsers():Observable<User[]>{
    return this.http.get<User[]>('/api/ba');
  }
}
