import { Component, OnInit, Inject } from '@angular/core';
import { AuthService } from 'src/app/services/auth-service.service';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { UserServiceService } from 'src/app/services/user-service.service';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import { TaskServiceService } from 'src/app/services/task-service.service';
import { Task,Project,Comment,User} from 'src/app/DTOs/TaskMain/TaskMain';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material';

export class AssignDialogData{
  users:Array<User>;
}
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
    private taskService:TaskServiceService, private activatedRoute:ActivatedRoute, private dialog:MatDialog) { }

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
  assignDialog(): void {
    let data = new AssignDialogData();
    let comparator:string;
    if(this.task.status==='Open' || this.task.status === 'Reopen')
    comparator='dev pm'
    if(this.task.status === 'In Progress' || this.task.status === 'Resolved')
    comparator='dev';
    if(this.task.status === 'Ready for test')
    comparator='tester';
    if(this.task.status === 'Closed')
    comparator='';
    data.users = this.task.taskProject.projectUsers.filter(user=>( comparator.indexOf(user.role)!=-1) );
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

  constructor(
    public dialogRef: MatDialogRef<AssignDialog>,
    @Inject(MAT_DIALOG_DATA) public data: AssignDialogData) {
      console.log(data);
    }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
