import { Component, OnInit, Inject } from '@angular/core';
import { AuthService } from 'src/app/services/auth-service.service';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { UserServiceService } from 'src/app/services/user-service.service';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import { TaskServiceService } from 'src/app/services/task-service.service';
import { Task,Project,Comment,User} from 'src/app/DTOs/TaskMain/TaskMain';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { debounceTime, switchMap } from 'rxjs/operators';
import { CommentForCreating } from 'src/app/DTOs/CommentMain/CommentMain';
import { CommentServiceService } from 'src/app/services/comment-service.service';

export class AssignDialogData{
  role:string;
  idProject:number;
}
@Component({
  selector: 'app-task-full',
  templateUrl: './task-full.component.html',
  styleUrls: ['./task-full.component.css']
})
export class TaskFullComponent implements OnInit {

  ready:boolean=false;
  task:Task;
  commentControl = new FormControl('',Validators.required);
  constructor(private auth:AuthService, private http: HttpClient, private router: Router,
    private userService:UserServiceService,private projectService:ProjectServiceService,
    private taskService:TaskServiceService, private commentService:CommentServiceService, private activatedRoute:ActivatedRoute, private dialog:MatDialog) { }

  ngOnInit() {
    const idTask = this.activatedRoute.snapshot.params['idTask'];
    this.taskService.getTaskById(idTask).subscribe(
      (value)=>{
        this.task=value;
        this.ready=true;
      },
      (error)=>{
        this.router.navigateByUrl('/error/' + error.status);
      }
    )
  }
  updateStatus(status:string){
    this.task.status=status;
    this.task.updateDate=new Date();
    this.taskService.updateTask(this.task).subscribe(
      value=>{
        this.task=value;
      },
      error=>{
        this.router.navigateByUrl('error/'+error.status);
      }
    );
  }
  leaveComment():void{
    let comment = new CommentForCreating();
    comment.comment=this.commentControl.value;
    comment.commentTask.idTask=this.task.idTask;
    comment.commentUser.idUser=this.auth.user.idUser;
    comment.commentUser.name=this.auth.user.name;
    this.commentService.createComment(comment).subscribe(
      value=>{
        this.task.taskComments.push(value);
      },
      error=>{
        this.router.navigateByUrl('error/'+error.status);
      }
    );
    
  }
  assignDialog(): void {
    let data = new AssignDialogData();
    if(this.task.status==='Open' || this.task.status === 'Reopen')
    data.role='dev';
    if(this.task.status === 'In Progress' || this.task.status === 'Resolved')
    data.role='dev';
    if(this.task.status === 'Ready for test')
    data.role='tester';
    data.idProject=this.task.taskProject.idProject;
    const dialogRef = this.dialog.open(AssignDialog, {
      width: '250px',
      data
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result!=null){
        this.task.taskUser=result;
        this.task.updateDate=new Date();
        this.taskService.updateTask(this.task).subscribe(
          value=>{
            this.task=value;
          },
          error=>{
            this.router.navigateByUrl('error/'+error.status);
          }
        );
      }
    });
  }
}
@Component({
  templateUrl: 'assign-dialog.html',
})
export class AssignDialog {
  assignForm:FormGroup;
  dynamicUsers:Observable<User[]>;
  constructor(

    public dialogRef: MatDialogRef<AssignDialog>,
    @Inject(MAT_DIALOG_DATA) public data: AssignDialogData,private fb:FormBuilder, private taskService:TaskServiceService) {
      this.assignForm = fb.group({
        email:new FormControl(
          null,[Validators.required]
        )
        },{validator: choosePersonValidator});
        this.dynamicUsers=this.assignForm.get('email').valueChanges.pipe(debounceTime(300),
          switchMap(email=>this.taskService.getUsersOnProjectForTaskAssign(this.data.role,email,this.data.idProject))
        );
    }

  displayFn(user: User) {
      if (user) { return user.email; }
  }
  onNoClick(): void {
    this.dialogRef.close();
  }

}
export function choosePersonValidator(formGroup: FormGroup) {
  const email = formGroup.controls['email'];
  if(email.value==null){
    return;
  }
  if (email.value['email'] == undefined) {
    email.setErrors({ notUser: true });
  } else {
    email.setErrors(null);
  }
}
