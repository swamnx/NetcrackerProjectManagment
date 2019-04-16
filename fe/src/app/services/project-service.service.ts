import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth-service.service';
import { Observable } from 'rxjs';
import { Project } from 'src/app/DTOs/ProjectMain/ProjectMain';

@Injectable({
  providedIn: 'root'
})
export class ProjectServiceService {

  constructor(private http:HttpClient, private auth:AuthService) { }

  getProjects():Observable<Project[]>{
    return this.http.get<Project[]>('api/projects/users/'+this.auth.user.idUser.toString(10));
  }
  createProject(project:Project):Observable<Project>{
    return this.http.post<Project>('api/projects/'+this.auth.user.idUser.toString(10),project);
  }
  updateProject(project:Project):Observable<Project>{
    return this.http.patch<Project>('api/projects',project);
  }
  deleteProjectById(idProject:number):Observable<void>{
    return this.http.delete<void>('api/projects/'+idProject);
  }
  getProjectById(idProject:number):Observable<Project>{
    return this.http.get<Project>('api/projects/'+idProject);
  }

}
