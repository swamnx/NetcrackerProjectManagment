import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth-service.service';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { Task } from 'src/app/models/task';
import { UserServiceService } from 'src/app/services/user-service.service';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import { TaskServiceService } from 'src/app/services/task-service.service';
import { Page } from 'src/app/DTOs/TaskMain/TaskMain';
import { routerNgProbeToken } from '@angular/router/src/router_module';

@Component({
  selector: 'app-table-tasks-available',
  templateUrl: './table-tasks-available.component.html',
  styleUrls: ['./table-tasks-available.component.css']
})
export class TableTasksAvailableComponent implements OnInit {

  constructor(private auth:AuthService, private http: HttpClient, private router: Router,
    private userService:UserServiceService,private projectService:ProjectServiceService,
    private taskService:TaskServiceService, private activatedRoute:ActivatedRoute) {
  }

  page:Page;
  ready:boolean=false;
  ngOnInit() {
    this.taskService.getTasks(0).subscribe(
      (value)=>{
        console.log(value);
        this.page=value;
        this.ready=true;
      },
      (error)=>{
        this.router.navigateByUrl('error/'+error.status);
      }
    );
  }
  onChooseTask(idTask:number){
    this.router.navigateByUrl('tasks/'+idTask);
  }
  firstPage(){
    this.taskService.getTasks(0).subscribe(
      (value)=>{
        this.page=value;
        this.ready=true;
      },
      (error)=>{
        this.router.navigateByUrl('error/'+error.status);
      }
    );
  }
  previousPage(){
    this.taskService.getTasks(this.page.number-1).subscribe(
      (value)=>{
        this.page=value;
        this.ready=true;
      },
      (error)=>{
        this.router.navigateByUrl('error/'+error.status);
      }
    );
  }
  nextPage(){
    this.taskService.getTasks(this.page.number+1).subscribe(
      (value)=>{
        this.page=value;
        this.ready=true;
      },
      (error)=>{
        this.router.navigateByUrl('error/'+error.status);
      }
    );
  }
  lastPage(){
    this.taskService.getTasks(this.page.totalPages-1).subscribe(
      (value)=>{
        this.page=value;
        this.ready=true;
      },
      (error)=>{
        this.router.navigateByUrl('error/'+error.status);
      }
    );
  }
  

}
