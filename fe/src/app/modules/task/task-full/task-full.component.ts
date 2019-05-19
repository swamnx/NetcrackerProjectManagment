import { Component, OnInit, Inject } from '@angular/core';
import { AuthService } from 'src/app/services/auth-service.service';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { UserServiceService } from 'src/app/services/user-service.service';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import { TaskServiceService } from 'src/app/services/task-service.service';
import { Task, Project, Comment, User } from 'src/app/DTOs/TaskMain/TaskMain';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material';
import { FormGroup, FormBuilder, FormControl, Validators, ValidatorFn, AbstractControl } from '@angular/forms';
import { Observable, timer } from 'rxjs';
import { debounceTime, switchMap } from 'rxjs/operators';
import { CommentForCreating } from 'src/app/DTOs/CommentMain/CommentMain';
import { CommentServiceService } from 'src/app/services/comment-service.service';
import { MatDialogModule } from '@angular/material/dialog';
import { dateDueAndEstimationValidator } from '../../create-task/create-task.component';

export class AssignDialogData {
  role: string;
  idProject: number;
}
export class EditDialogData {
  task: Task;
}
@Component({
  selector: 'app-task-full',
  templateUrl: './task-full.component.html',
  styleUrls: ['./task-full.component.css']
})
export class TaskFullComponent implements OnInit {

  readyTask: boolean = false;
  sendingData: boolean = false;
  task: Task;
  userOnThisProject: boolean = false;
  commentControl = new FormControl('', Validators.required);

  constructor(private auth: AuthService, private http: HttpClient, private router: Router,
    private userService: UserServiceService, private projectService: ProjectServiceService,
    private taskService: TaskServiceService, private commentService: CommentServiceService, private activatedRoute: ActivatedRoute, private dialog: MatDialog) {
      if(!auth.authenticated) auth.authenticateNot();
    }
  ngOnInit() {
    const idTask = this.activatedRoute.snapshot.params['idTask'];
    timer(500).subscribe(
      val => {
        this.taskService.getTaskById(idTask).subscribe(
          (value) => {
            this.task = value;

            this.userService.isUserOnProject(this.auth.user.idUser, this.task.taskProject.idProject).subscribe(
              (response) => {

                if (response.status == 200) this.userOnThisProject = true;
                this.readyTask = true;
              },
              (error) => {
                this.router.navigateByUrl('/error/' + error.status);
              }
            )
          },
          (error) => {
            this.router.navigateByUrl('/error/' + error.status);
          }
        )
      }
    )
  }

  dateShow(date: Date) {
    let dateLocal = new Date(date + ' UTC');
    return 'on ' + dateLocal.getDay() + '/' + dateLocal.getMonth() + '/' + dateLocal.getFullYear() + ' at ' +
      dateLocal.getHours() + ':' + dateLocal.getMinutes();
  }

  updateStatus(status: string) {
    this.sendingData = true;
    this.task.status = status;
    this.task.updateDate = new Date();
    timer(500).subscribe(
      val => {
        this.taskService.updateTask(this.task).subscribe(
          value => {
            this.task = value;
            this.sendingData = false;
          },
          error => {
            this.router.navigateByUrl('error/' + error.status);
          }
        );
      }
    )
  }
  leaveComment(): void {
    this.sendingData=true;
    let comment = new CommentForCreating();
    comment.comment = this.commentControl.value;
    comment.commentTask.idTask = this.task.idTask;
    comment.commentUser.idUser = this.auth.user.idUser;
    comment.commentUser.name = this.auth.user.name;
    timer(500).subscribe(
      val => {
        this.commentService.createComment(comment).subscribe(
          value => {
            this.task.taskComments.push(value);
            this.sendingData = false;
          },
          error => {
            this.router.navigateByUrl('error/' + error.status);
          }
        );
      }
    )

  }
  assignDialog(): void {
    let data = new AssignDialogData();
    if (this.task.status == 'open' || this.task.status == 'reopen')
      data.role = 'dev';
    if (this.task.status == 'inProgress' || this.task.status == 'resolved')
      data.role = 'dev';
    if (this.task.status == 'readyForTest')
      data.role = 'tester';
    data.idProject = this.task.taskProject.idProject;
    const dialogRef = this.dialog.open(AssignDialog, {
      width: '25vh',
      height: '20vh',
      autoFocus: false,
      data
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.sendingData = true;
        timer(500).subscribe(
          val => {
            this.task.taskUser = result;
            this.task.updateDate = new Date();
            this.taskService.updateTask(this.task).subscribe(
              value => {
                this.task = value;
                this.sendingData = false;
              },
              error => {
                this.router.navigateByUrl('error/' + error.status);
              }
            );
          }
        )
      }
    });
  }
  editDialog(): void {
    let data = new EditDialogData;
    data.task = this.task;
    const dialogRef = this.dialog.open(EditDialog, {
      width: '30vh',
      height: '45vh',
      autoFocus: false,
      data
    })
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.sendingData=true;
        this.task.description = result.value['description'];
        this.task.dueDate = result.value['dueDate'];
        this.task.name = result.value['name'];
        this.task.estimationDate = result.value['estimationDate'];
        this.task.priority = result.value['priority'];
        this.task.updateDate = new Date();
        timer(500).subscribe(
          val => {
            this.taskService.updateTask(this.task).subscribe(
              (value) => {
                this.task = value;
                this.sendingData = false;
              },
              (error) => {
                this.router.navigateByUrl('/error/' + error.status);
              }
            )
          }
        )
      }
    })
  }
}
@Component({
  templateUrl: 'assign-dialog.html',
})
export class AssignDialog {
  assignForm: FormGroup;
  amountOfDynamicUsers: number = 0;
  users: Array<User>
  dynamicUsers: Observable<User[]>;
  lastInput: string = null;
  constructor(

    public dialogRef: MatDialogRef<AssignDialog>,
    @Inject(MAT_DIALOG_DATA) public data: AssignDialogData, private fb: FormBuilder, private taskService: TaskServiceService) {
    this.assignForm = fb.group(
      {
        email: new FormControl(
          null, Validators.compose([Validators.required, PersonValidator(0)])
        )
      }
    );

    this.dynamicUsers = this.assignForm.get('email').valueChanges.pipe(
      switchMap(email => this.taskService.getUsersOnProjectForTaskAssign(this.data.role, email, this.data.idProject))
    );

    this.dynamicUsers.subscribe(
      users => {
        if (this.lastInput == this.assignForm.controls['email'].value) return;
        this.lastInput = this.assignForm.controls['email'].value;
        this.users = users;
        this.assignForm.controls['email'].setValidators(
          Validators.compose([Validators.required, PersonValidator(users.length)])
        )
        this.assignForm.controls['email'].updateValueAndValidity();
      }
    )
  }

  displayFn(user: User) {
    if (user) { return user.email; }
  }

}
export function PersonValidator(amountOfDynamicUsers: number): ValidatorFn {
  return (email: AbstractControl): { [key: string]: boolean | null } => {
    if (!email.value) return null;
    else if (amountOfDynamicUsers == 0 && email.value['email'] == undefined) return { userNotFound: true };
    else if (email.value['email'] == undefined) return { userNotSelected: true };
    return null;
  }
}
@Component({
  templateUrl: 'edit-dialog.html',
})
export class EditDialog {
  editForm: FormGroup;
  nowDate:Date=new Date();
  constructor(

    public dialogRef: MatDialogRef<EditDialog>,
    @Inject(MAT_DIALOG_DATA) public data: EditDialogData, private taskService: TaskServiceService,private fb:FormBuilder) {
    this.editForm = fb.group({
      description: new FormControl(this.data.task.description, [Validators.required,Validators.maxLength(300)]),
      priority: new FormControl(this.data.task.priority, [Validators.required]),
      dueDate: new FormControl(this.data.task.dueDate),
      estimationDate: new FormControl(this.data.task.estimationDate),
      name:new FormControl(this.data.task.name,[Validators.required,Validators.maxLength(50)])
    },{validator:dateDueAndEstimationForEditValidator})
  }
}
export function dateDueAndEstimationForEditValidator(formGroup: FormGroup) {
  const controlDueDate = formGroup.controls['dueDate'];
  const controlEstimationDate = formGroup.controls['estimationDate'];
  if (controlDueDate.errors) {
    return;
  }
  if (controlEstimationDate.errors && !controlEstimationDate.errors.badDataComparison) {
    return;
  }
  if (new Date(controlDueDate.value) < controlEstimationDate.value) {
    controlEstimationDate.setErrors({ badDataComparison: true });
  } else {
    controlEstimationDate.setErrors(null);
  }
}