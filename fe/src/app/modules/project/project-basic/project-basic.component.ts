import { Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/services/auth-service.service';
import { UserServiceService } from 'src/app/services/user-service.service';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import { TaskServiceService } from 'src/app/services/task-service.service';
import { Project, User} from 'src/app/DTOs/ProjectMain/ProjectMain';
import { FormGroup, FormBuilder, FormControl, Validators, ValidatorFn, AbstractControl } from '@angular/forms';
import { Observable, timer } from 'rxjs';
import { MatDialogRef, MatDialog } from '@angular/material';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-project-basic',
  templateUrl: './project-basic.component.html',
  styleUrls: ['./project-basic.component.css']
})
export class ProjectBasicComponent implements OnInit {

  project:Project;
  projectReady:boolean=false;
  userOnThisProject:boolean;
  actionsWithProject:boolean=false;

  constructor(private router: Router, private auth:AuthService,
    private userService:UserServiceService,private projectService:ProjectServiceService,private activatedRoute:ActivatedRoute,private dialog:MatDialog) {
      this.projectReady=false;
    }
  
  ngOnInit() {
    const idProject:number = this.activatedRoute.snapshot.params['idProject'];
    this.projectService.getProjectById(idProject).subscribe(
      (value)=>{
        this.project=value;
        this.projectReady=true;
        this.userService.isUserOnProject(this.auth.user.idUser,this.project.idProject).subscribe(
          (value)=>{
            if(value.status==200){
              this.userOnThisProject=true;
            }
          },
          (error)=>{
            this.router.navigateByUrl('/error/'+error.status);
          }
        );
      },
      (error)=>{
      this.router.navigateByUrl('/error/'+error.status);
      }
    );
  }
  addUser(): void {
    const dialogRef = this.dialog.open(AddUserDialog, {
      width: '25vh',
      height: '20vh',
      autoFocus: false,
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.actionsWithProject=true;
        timer(500).subscribe(
          val=>{
            this.userService.addUserOnProject(result['idUser'],this.project.idProject).subscribe(
              (value)=>{
                if(value.status==200){
                  this.router.navigateByUrl('/users/'+result['idUser'])
                }
              },
              (error)=>{
                this.router.navigateByUrl('/error/'+error.status);
              }
            )
          }
        )
      }
    });
  }
  
}
@Component({
  templateUrl: 'add-user-dialog.html',
})
export class AddUserDialog {
  addUserForm:FormGroup;
  amountOfDynamicUsers:number=0;
  users:Array<User>
  dynamicUsers:Observable<User[]>;
  lastInput:string=null;
  constructor(

    public dialogRef: MatDialogRef<AddUserDialog>,private fb:FormBuilder, private projectService:ProjectServiceService) {
      this.addUserForm = fb.group(
        {
          email:new FormControl(
          '',Validators.compose([Validators.required,PersonValidator(0)])
          )
        }
      );

      this.dynamicUsers=this.addUserForm.get('email').valueChanges.pipe(
        switchMap(email=>this.projectService.getUsersStartWithEmailForAddingOnProject(this.addUserForm.controls['email'].value))
      );

      this.dynamicUsers.subscribe(
        users => {
          if(this.lastInput==this.addUserForm.controls['email'].value) return;
          this.lastInput=this.addUserForm.controls['email'].value;
          this.users=users;
          this.addUserForm.controls['email'].setValidators(
            Validators.compose([Validators.required,PersonValidator(users.length)])
          )
          this.addUserForm.controls['email'].updateValueAndValidity();
        }
      )
    }

  displayFn(user: User) {
      if (user) { return user.email; }
  }

}
export function PersonValidator(amountOfDynamicUsers:number):ValidatorFn{
  return (email:AbstractControl):{[key:string]:boolean | null}=>{
    if(!email.value) return null;
    else if(amountOfDynamicUsers==0 && email.value['email'] == undefined) return {userNotFound:true};
    else if (email.value['email'] == undefined) return{ userNotSelected: true};
    return null;
  }
}
