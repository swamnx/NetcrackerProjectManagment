import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AuthService } from './auth-service.service';
import { Observable } from 'rxjs';
import { Project,User } from 'src/app/DTOs/ProjectMain/ProjectMain';

@Injectable({
  providedIn: 'root'
})
export class ProjectServiceService {

  constructor(private http:HttpClient, private auth:AuthService) { }

  getProjects():Observable<Project[]>{
    return this.http.get<Project[]>('api/projects/users/'+this.auth.user.idUser.toString(10));
  }
  createProject(project:Project):Observable<Project>{
    project.projectUsers=[this.auth.user];
    return this.http.post<Project>('api/projects',project);
  }

  getProjectById(idProject:number):Observable<Project>{
    return this.http.get<Project>('api/projects/'+idProject);
  }

}
