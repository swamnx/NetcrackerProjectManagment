import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AuthService } from './auth-service.service';
import { Task,Project,Comment,User, PageForProjectTable, PageForTasksTable } from 'src/app/DTOs/TaskMain/TaskMain';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskServiceService {

  constructor(private http:HttpClient, private auth:AuthService) { }

  getPageOfTasksForUser(page:number,type:string){
    let options = {
      params:new HttpParams()
      .set('page',page.toString(10))
      .set('size','3')
    }
    return this.http.get<PageForTasksTable>('/api/tasks/page/'+type+'Tasks',options);
  }
  
  getPageOfTasksForProject(page:number,idProject:number):Observable<PageForProjectTable>{
    let options = {
      params:new HttpParams()
      .set('page',page.toString(10))
      .set('size','3')
      .set('idProject',idProject.toString(10))
    }
    return this.http.get<PageForProjectTable>('api/tasks/page/projectTasks',options);
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
