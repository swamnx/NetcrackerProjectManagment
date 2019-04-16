import { Component, OnInit, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/services/auth-service.service';
import { UserServiceService } from 'src/app/services/user-service.service';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import { TaskServiceService } from 'src/app/services/task-service.service';
import { Project,User,Task,Comment } from 'src/app/DTOs/ProjectMain/ProjectMain';

@Component({
  selector: 'app-project-basic',
  templateUrl: './project-basic.component.html',
  styleUrls: ['./project-basic.component.css']
})
export class ProjectBasicComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router, private auth:AuthService,
    private userService:UserServiceService,private projectService:ProjectServiceService,
    private taskService:TaskServiceService, private activatedRoute:ActivatedRoute) { }
    ready:boolean=false;
    project:Project;
  ngOnInit() {
    const idProject = this.activatedRoute.snapshot.params['idProject'];
    this.projectService.getProjectById(idProject).subscribe(
      (value)=>{
        this.project=value;
        console.log(value);
        this.ready=true;
      },
      (error)=>{
        if(error.status==404) this.router.navigateByUrl('error/Not Found');
        else this.router.navigateByUrl('error/Server Problem');
      }
    )
  }
}
