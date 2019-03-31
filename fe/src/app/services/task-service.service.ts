import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth-service.service';
import { Task } from '../models/task';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskServiceService {

  constructor(private http:HttpClient, private auth:AuthService) { }

  getTasks():Observable<Task[]>{
    return this.http.get<Task[]>('http://localhost:8083/api/tasks');
  }
  createTask(task:Task):Observable<Task>{
    return this.http.post<Task>('http://localhost:8083/api/tasks',task);
  }
  updateTask(task:Task):Observable<Task>{
    return this.http.patch<Task>('http://localhost:8083/api/tasks',task);
  }
  deleteTaskById(idTask:number):Observable<void>{
    return this.http.delete<void>('http://localhost:8083/api/tasks/'+idTask);
  }
  getTaskById(idTask:number):Observable<Task>{
    return this.http.get<Task>('http://localhost:8083/api/tasks/'+idTask);
  }
}
