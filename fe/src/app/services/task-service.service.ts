import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AuthService } from './auth-service.service';
import { Task,Project,Comment,User,Page } from 'src/app/DTOs/TaskMain/TaskMain';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskServiceService {

  constructor(private http:HttpClient, private auth:AuthService) { }

  getTasks(page:number):Observable<Page>{
    let options = {
      params: new HttpParams()
      .set('page',page.toString(10))
      .set('size','5')
      .set('idUser',this.auth.user.idUser.toString(10))
    }
    return this.http.get<Page>('api/tasks',options);
  }
  createTask(task:Task):Observable<Task>{
    return this.http.post<Task>('api/tasks',task);
  }
  updateTask(task:Task):Observable<Task>{
    return this.http.patch<Task>('api/tasks',task);
  }
  deleteTaskById(idTask:number):Observable<void>{
    return this.http.delete<void>('api/tasks/'+idTask);
  }
  getTaskById(idTask:number):Observable<Task>{
    let options ={params: new HttpParams().set('idUser','1')}
    return this.http.get<Task>('api/tasks/'+idTask,options);
  }
}
