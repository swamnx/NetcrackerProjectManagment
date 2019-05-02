import { Component, OnInit, Input } from '@angular/core';
import { AuthService } from 'src/app/services/auth-service.service';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { Task } from 'src/app/models/task';
import { UserServiceService } from 'src/app/services/user-service.service';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import { TaskServiceService } from 'src/app/services/task-service.service';
import { PageForTasksTable } from 'src/app/DTOs/TaskMain/TaskMain';
import { routerNgProbeToken } from '@angular/router/src/router_module';

@Component({
  selector: 'app-tasks-table',
  templateUrl: './tasks-table.component.html',
  styleUrls: ['./tasks-table.component.css']
})
export class TasksTableComponent implements OnInit {

  constructor(private auth:AuthService, private http: HttpClient, private router: Router,
    private userService:UserServiceService,private projectService:ProjectServiceService,
    private taskService:TaskServiceService, private activatedRoute:ActivatedRoute) {
  }

  page:PageForTasksTable;
  readyPage:boolean=false;
  type:string='real';

  onClickReal(){
    this.type = 'real';
    this.firstPage();
  }
  onClickAvailable(){
    this.type = 'available';
    this.firstPage();

  }
  ngOnInit() {
    this.taskService.getPageOfTasksForUser(0,this.type).subscribe(
      (value)=>{
        this.page=value;
        this.readyPage=true;
      },
      (error)=>{
        this.router.navigateByUrl('/error/'+error.status);
      }
    );
  }
  onChooseTask(idTask:number){
    this.router.navigateByUrl('/tasks/'+idTask);
  }
  firstPage(){
    this.readyPage=false;
    this.taskService.getPageOfTasksForUser(0,this.type).subscribe(
      (value)=>{
        this.page=value;
        this.readyPage=true;
      },
      (error)=>{
        this.router.navigateByUrl('/error/'+error.status);
      }
    );
  }
  previousPage(){
    this.readyPage=false;
    this.taskService.getPageOfTasksForUser(this.page.number-1,this.type).subscribe(
      (value)=>{
        this.page=value;
        this.readyPage=true;
      },
      (error)=>{
        this.router.navigateByUrl('/error/'+error.status);
      }
    );
  }
  nextPage(){
    this.readyPage=false;
    this.taskService.getPageOfTasksForUser(this.page.number+1,this.type).subscribe(
      (value)=>{
        this.page=value;
        this.readyPage=true;
      },
      (error)=>{
        this.router.navigateByUrl('/error/'+error.status);
      }
    );
  }
  lastPage(){
    this.readyPage=false;
    this.taskService.getPageOfTasksForUser(this.page.totalPages-1,this.type).subscribe(
      (value)=>{
        console.log(value);
        this.page=value;
        this.readyPage=true;
      },
      (error)=>{
        this.router.navigateByUrl('/error/'+error.status);
      }
    );
  }

}
