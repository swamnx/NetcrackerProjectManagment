import {FormControl, FormGroup} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import { Component, OnInit, Input } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/services/auth-service.service';
import { UserServiceService } from 'src/app/services/user-service.service';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import { TaskServiceService } from 'src/app/services/task-service.service';
import {Task,User} from'src/app/DTOs/TaskMain/TaskMain';
import {Project} from 'src/app/DTOs/ProjectMain/ProjectMain';
@Component({
  selector: 'app-create-task-on-project',
  templateUrl: './create-task-on-project.component.html',
  styleUrls: ['./create-task-on-project.component.css']
})
export class CreateTaskOnProjectComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router, private auth:AuthService,
    private userService:UserServiceService,private projectService:ProjectServiceService,
    private taskService:TaskServiceService, private activatedRoute:ActivatedRoute) { }

    ready:boolean=false;
    projects:Array<Project>;
    users:Array<User>;
    project111:Project;
    user111:User;
    smth(event){
      this.users=this.project111.projectUsers;
    }
    smth1(event){
    }
  ngOnInit() {
    this.projectService.getProjects().subscribe(
      value=>{
        this.projects=value;
        this.users=[this.auth.user];
        this.ready=true;
      },
      error=>{

      }
    )
  }
  description:string;
  dueDate:Date;
  estimationDate:Date;
  create(){
    let idProject= this.activatedRoute.snapshot.params['idProject'];
    let task = new Task();
    task.idCreatedBy=this.auth.user.idUser;
    task.description = this.description;
    task.dueDate = this.dueDate;
    task.createDate=new Date();
    task.updateDate=task.createDate;
    task.estimationDate= this.estimationDate;
    task.taskUser=this.user111;
    task.taskProject=this.project111;
    this.http.post<Task>('api/tasks',task).subscribe(
      value=>{
        this.router.navigateByUrl('/tasks/'+value.idTask);
      },
      error=>{
        this.router.navigateByUrl('/error/'+error.status);
      }
    )
  }
}
