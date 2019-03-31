import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth-service.service';
import { UserServiceService } from 'src/app/services/user-service.service';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import { TaskServiceService } from 'src/app/services/task-service.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {

  users: User[];
  error:string;
  constructor(private http: HttpClient, private router: Router, private auth:AuthService,
    private userService:UserServiceService,private projectService:ProjectServiceService,
    private taskService:TaskServiceService) {
      this.error='no data';
    }

  ngOnInit() {
  }
  buttonFunc(){
    this.userService.getUsers().subscribe(
      (value) =>{
      this.users=value; 
      this.error='f';
      },
      (error) =>{
      this.error='no data';
      }
    )
  }

}
