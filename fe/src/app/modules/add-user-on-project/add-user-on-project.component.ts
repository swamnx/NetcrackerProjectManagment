import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import { Component, OnInit, Input } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/services/auth-service.service';
import { UserServiceService } from 'src/app/services/user-service.service';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import { TaskServiceService } from 'src/app/services/task-service.service';
import {User} from 'src/app/DTOs/UserMain/UserMain';
import { Options } from 'selenium-webdriver';
import { Project } from 'src/app/DTOs/ProjectMain/ProjectMain';
@Component({
  selector: 'app-add-user-on-project',
  templateUrl: './add-user-on-project.component.html',
  styleUrls: ['./add-user-on-project.component.css']
})
export class AddUserOnProjectComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router, private auth:AuthService,
    private userService:UserServiceService,private projectService:ProjectServiceService,
    private taskService:TaskServiceService, private activatedRoute:ActivatedRoute) { }
    ready:boolean=false;
  ngOnInit() {
    this.userService.getUsers().subscribe(
      value=>{
        this.options=value;
        this.filteredOptions = this.myControl.valueChanges
        .pipe(
            startWith<string| User>(''),
            map(value =>  typeof value==='string' ?value:value.email),
            map(email => email ?this._filter(email):this.options.slice())
          );
          this.ready=true;
      }
    );
  }
  myControl = new FormControl();
  options: User[];
  filteredOptions: Observable<User[]>;
  private _filter(email:string): User[] {
    const filterValue = email.toLowerCase();

    return this.options.filter(option=> option.email.toLowerCase().indexOf(filterValue) === 0);
  }
  displayFn(user?: User): string | undefined {
    return user ? user.email : undefined;
  }
  add(){
    let idProject= this.activatedRoute.snapshot.params['idProject'];
    this.http.patch<User>('api/users/addUserOnProject/'+idProject,this.myControl.value).subscribe(
      value=>{
        console.log(value);
        this.router.navigateByUrl('/users/'+value.idUser);
      },
      error=>{
        this.router.navigateByUrl('/error/'+error.status);
      }
    )
  }
}
