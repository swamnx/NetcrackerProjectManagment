import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth-service.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Task } from 'src/app/models/task';
import { UserServiceService } from 'src/app/services/user-service.service';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import { TaskServiceService } from 'src/app/services/task-service.service';

@Component({
  selector: 'app-table-tasks',
  templateUrl: './table-tasks.component.html',
  styleUrls: ['./table-tasks.component.css']
})
export class TableTasksComponent implements OnInit {
  error:string;
  tasks:Task[];
  constructor(private auth:AuthService, private http: HttpClient, private router: Router,
    private userService:UserServiceService,private projectService:ProjectServiceService,
    private taskService:TaskServiceService) {
    this.error='';
  }
  ready:boolean=false;
  ngOnInit() {
    this.taskService.getTasks(0).subscribe(
      (value)=>{
        console.log(value);
        this.ready=true;
      }
    );
  }

}