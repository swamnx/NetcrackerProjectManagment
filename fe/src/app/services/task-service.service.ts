import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AuthService } from './auth-service.service';
import { Task,Project,Comment,User, PageForTasksTable } from 'src/app/DTOs/TaskMain/TaskMain';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TaskServiceService {

  constructor(private http:HttpClient, private auth:AuthService) { }

  getPageOfTasksForUser(type:string,fieldSort:string,directionSort:string,page:number,size:number,search:string,idProject:number){
    let options = {
      params:new HttpParams()
      .set('page',page.toString(10))
      .set('size',size.toString(10))
      .set('directionSort',directionSort)
      .set('fieldSort',fieldSort)
      .set('type',type)
      .set('search',search)
      .set('idProject',idProject.toString(10))
    }
    return this.http.get<PageForTasksTable>('/api/tasks/page',options);
  }
  
  getUsersOnProjectForTaskAssign(role:string,firstEmailLetters:string,idProject:number,):Observable<User[]>{
    let options = {
      params:new HttpParams()
      .set('firstEmailLetters',firstEmailLetters)
      .set('role',role)
      .set('idProject',idProject.toString(10))
    }
    return this.http.get<User[]>('api/tasks/onProjectForTaskAssign',options);
  }
  createTask(task:Task):Observable<Task>{
    return this.http.post<Task>('api/tasks',task);
  }
  updateTask(task:Task):Observable<Task>{
    return this.http.patch<Task>('api/tasks',task);
  }
  getTaskById(idTask:number):Observable<Task>{
    let options ={params: new HttpParams().set('idUser',this.auth.user.idUser.toString(10))};
    return this.http.get<Task>('api/tasks/'+idTask,options);
  }
}
