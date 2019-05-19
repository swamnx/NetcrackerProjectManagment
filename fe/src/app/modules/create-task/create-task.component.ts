import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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

  createTaskForm: FormGroup;
  projects: Project[];
  sendingData: boolean = true;
  nowDate:Date = new Date();

  constructor(private auth: AuthService, private router: Router,private fb: FormBuilder,
    private projectService: ProjectServiceService, private taskService: TaskServiceService) {
      if(!auth.authenticated) this.auth.authenticateNot();
    
    this.createTaskForm = fb.group({
      description: new FormControl('', [Validators.required,Validators.maxLength(300)]),
      name:new FormControl('',[Validators.required,Validators.maxLength(50)]),
      taskProject: new FormControl(null, [Validators.required]),
      priority: new FormControl('normal', [Validators.required]),
      dueDate: new FormControl(''),
      estimationDate: new FormControl('')
    },{validator:dateDueAndEstimationValidator});

  }


  ngOnInit() {
    timer(500).subscribe(
      val => {
        this.projectService.getProjects().subscribe(
          (value) => {
            this.projects = value;
            this.sendingData = false;
          },
          (error) => {
            this.router.navigateByUrl('/error/' + error.status);
          }
        );
      }
    )
  }
  createTask() {
    this.sendingData = true;
    let task = new Task();
    task.description = this.createTaskForm.controls['description'].value;
    task.status = 'open';
    task.name = this.createTaskForm.controls['name'].value;
    task.priority = this.createTaskForm.controls['priority'].value;
    task.createDate = new Date();
    task.updateDate = task.createDate;
    task.dueDate = this.createTaskForm.controls['dueDate'].value;
    task.estimationDate = this.createTaskForm.controls['estimationDate'].value;
    task.taskProject = this.createTaskForm.controls['taskProject'].value;
    task.taskUser = this.auth.user;
    task.idCreatedBy = this.auth.user.idUser;
    timer(500).subscribe(
      val => {
        this.taskService.createTask(task).subscribe(
          (value) => {
            this.router.navigateByUrl('/tasks/' + value.idTask);
          },
          (error) => {
            this.router.navigateByUrl('/error/' + error.status);
          }
        )
      }
    )
  }

}
export function dateDueAndEstimationValidator(formGroup: FormGroup) {
  const controlDueDate = formGroup.controls['dueDate'];
  const controlEstimationDate = formGroup.controls['estimationDate'];
  if (controlDueDate.untouched || controlDueDate.errors) {
    return;
  }
  if (controlEstimationDate.errors && !controlEstimationDate.errors.badDataComparison) {
    return;
  }
  if (controlDueDate.value < controlEstimationDate.value) {
    controlEstimationDate.setErrors({ badDataComparison: true });
  } else {
    controlEstimationDate.setErrors(null);
  }
}
