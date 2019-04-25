import { Component, OnInit, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/services/auth-service.service';
import { UserServiceService } from 'src/app/services/user-service.service';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import { TaskServiceService } from 'src/app/services/task-service.service';
import { Project,User,Task,Comment } from 'src/app/DTOs/ProjectMain/ProjectMain';
import { PageForProjectTable } from 'src/app/DTOs/TaskMain/TaskMain';

@Component({
  selector: 'app-project-basic',
  templateUrl: './project-basic.component.html',
  styleUrls: ['./project-basic.component.css']
})
export class ProjectBasicComponent implements OnInit {

  readyProject:boolean;
  readyPage:boolean;
  project:Project;
  page:PageForProjectTable;

  constructor(private http: HttpClient, private router: Router, private auth:AuthService,
    private userService:UserServiceService,private projectService:ProjectServiceService,
    private taskService:TaskServiceService, private activatedRoute:ActivatedRoute) {
      this.readyPage=false;
      this.readyProject=false;
    }
  ngOnInit() {
    const idProject:number = this.activatedRoute.snapshot.params['idProject'];
    this.projectService.getProjectById(idProject).subscribe(
      (value)=>{
        this.project=value;
        this.readyProject=true;
        this.taskService.getPageOfTasksForProject(0,idProject).subscribe(
          (value)=>{
            this.page=value;
            this.readyPage=true;
          },
          (error)=>{
            this.router.navigateByUrl('/error/'+error.status);
          }
        )
        
      },
      (error)=>{
      this.router.navigateByUrl('/error/'+error.status);
      }
    )
  }
  
  firstPage(){
    this.readyPage=false;
    this.taskService.getPageOfTasksForProject(0,this.project.idProject).subscribe(
      (value)=>{
        this.page=value;
        this.readyPage=true;
      },
      (error)=>{
        this.router.navigateByUrl('/error/'+error.status);
      }
    )
  }

  previousPage(){
    this.readyPage=false;
    this.taskService.getPageOfTasksForProject(this.page.number-1,this.project.idProject).subscribe(
      (value)=>{
        this.page=value;
        this.readyPage=true;
      },
      (error)=>{
        this.router.navigateByUrl('/error/'+error.status);
      }
    )
  }
  nextPage(){
    this.readyPage=false;
    this.taskService.getPageOfTasksForProject(this.page.number+1,this.project.idProject).subscribe(
      (value)=>{
        this.page=value;
        this.readyPage=true;
      },
      (error)=>{
        this.router.navigateByUrl('/error/'+error.status);
      }
    )
  }
  lastPage(){
    this.readyPage=false;
    this.taskService.getPageOfTasksForProject(this.page.totalPages-1,this.project.idProject).subscribe(
      (value)=>{
        this.page=value;
        this.readyPage=true;
      },
      (error)=>{
        this.router.navigateByUrl('/error/'+error.status);
      }
    )
  }
}
