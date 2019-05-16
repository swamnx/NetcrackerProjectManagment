import {FormControl, FormGroup, Validators} from '@angular/forms';
import { Component, OnInit} from '@angular/core';
import { Router} from '@angular/router';
import { AuthService } from 'src/app/services/auth-service.service';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import { TaskServiceService } from 'src/app/services/task-service.service';
import { Task, Project } from 'src/app/DTOs/TaskMain/TaskMain';
import { timer } from 'rxjs';
@Component({
  selector: 'app-create-task',
  templateUrl: './create-task.component.html',
  styleUrls: ['./create-task.component.css']
})
export class CreateTaskComponent implements OnInit {

  createTaskForm:FormGroup;
  projects:Project[];
  sendingData:boolean=false;
  ready:boolean=false;
  constructor(private router: Router, private auth:AuthService,private projectService:ProjectServiceService,private taskService:TaskServiceService) {
    this.createTaskForm = new FormGroup({
      description: new FormControl('',[Validators.required]),
      taskProject: new FormControl(null,[Validators.required]),
      priority:new FormControl('normal',[Validators.required]),
      dueDate: new FormControl(''),
      estimationDate:new FormControl('')
    });
  }


  ngOnInit() {
    timer(500).subscribe(
      val=>{
        this.projectService.getProjects().subscribe(
          (value)=>{
            this.projects=value;
            this.ready=true;
          },
          (error)=>{
            this.router.navigateByUrl('/error/'+error.status);
          }
        );
      }
    )
  }
  createTask(){
    this.sendingData=true;
    let task = new Task();
    task.description=this.createTaskForm.controls['description'].value;
    task.status='open';
    task.priority=this.createTaskForm.controls['priority'].value;
    task.createDate=new Date();
    task.updateDate=task.createDate;
    task.dueDate=this.createTaskForm.controls['dueDate'].value;
    task.estimationDate=this.createTaskForm.controls['estimationDate'].value;
    task.taskProject=this.createTaskForm.controls['taskProject'].value;
    task.taskUser=this.auth.user;
    task.idCreatedBy=this.auth.user.idUser;
    timer(500).subscribe(
      val=>{
        this.taskService.createTask(task).subscribe(
          (value)=>{
            this.router.navigateByUrl('/tasks/'+value.idTask);
          },
          (error)=>{
            this.router.navigateByUrl('/error/'+error.status);
          }
        )
      }
    )
  }

}
