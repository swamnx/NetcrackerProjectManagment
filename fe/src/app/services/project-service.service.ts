import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth-service.service';
import { Observable } from 'rxjs';
import { Project } from '../models/project';

@Injectable({
  providedIn: 'root'
})
export class ProjectServiceService {

  constructor(private http:HttpClient, private auth:AuthService) { }

  getProjects():Observable<Project[]>{
    return this.http.get<Project[]>('http://localhost:8083/api/projects');
  }
  createProject(project:Project):Observable<Project>{
    return this.http.post<Project>('http://localhost:8083/api/projects',project);
  }
  updateProject(project:Project):Observable<Project>{
    return this.http.patch<Project>('http://localhost:8083/api/projects',project);
  }
  deleteProjectById(idProject:number):Observable<void>{
    return this.http.delete<void>('http://localhost:8083/api/projects/'+idProject);
  }
  getProjectById(idProject:number):Observable<Project>{
    return this.http.get<Project>('http://localhost:8083/api/projects/'+idProject);
  }

}
