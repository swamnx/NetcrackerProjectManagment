import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth-service.service';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { UserServiceService } from 'src/app/services/user-service.service';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import { TaskServiceService } from 'src/app/services/task-service.service';
import { Task,Project,Comment,User} from 'src/app/DTOs/TaskMain/TaskMain';

@Component({
  selector: 'app-task-full',
  templateUrl: './task-full.component.html',
  styleUrls: ['./task-full.component.css']
})
export class TaskFullComponent implements OnInit {

  ready:boolean=false;
  task:Task;
  constructor(private auth:AuthService, private http: HttpClient, private router: Router,
    private userService:UserServiceService,private projectService:ProjectServiceService,
    private taskService:TaskServiceService, private activatedRoute:ActivatedRoute) { }

  ngOnInit() {
    const idTask = this.activatedRoute.snapshot.params['idTask'];
    this.taskService.getTaskById(idTask).subscribe(
      (value)=>{
        console.log(value);
        this.task=value;
        this.ready=true;
      },
      (error)=>{
        if(error.status==403) this.router.navigateByUrl('error/Forbidden');
        else if(error.status==404) this.router.navigateByUrl('error/Not Found');
        else this.router.navigateByUrl('error/Server Problem');
      }
    )
  }

}
