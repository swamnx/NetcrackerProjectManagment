import { Component, OnInit, Input } from '@angular/core';
import { Task } from 'src/app/models/task';
import { MatTableDataSource, MatSort } from '@angular/material';
import { DataSource } from '@angular/cdk/table';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { UserServiceService } from 'src/app/services/user-service.service';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import { AuthService } from 'src/app/services/auth-service.service';
import { TaskServiceService } from 'src/app/services/task-service.service';

@Component({
  selector: 'app-task-basic',
  templateUrl: './task-basic.component.html',
  styleUrls: ['./task-basic.component.css']
})
export class TaskBasicComponent implements OnInit {
  
  constructor(private http: HttpClient, private router: Router, private auth:AuthService,
    private userService:UserServiceService,private projectService:ProjectServiceService,
    private taskService:TaskServiceService, private activatedRoute:ActivatedRoute) {
  }
  task:Task;
  ready:boolean=false;
  ngOnInit() {
    this.taskService.getTaskById(this.idTask).subscribe(
      (value)=>{
        this.task=value;
        this.ready=true;
      }
    )
  }
  @Input() idTask:number;
}
