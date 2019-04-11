import { Component, OnInit, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/services/auth-service.service';
import { UserServiceService } from 'src/app/services/user-service.service';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import { TaskServiceService } from 'src/app/services/task-service.service';
import { User} from 'src/app/models/user';
import { Task} from 'src/app/models/task';
import { Project} from 'src/app/models/project';

@Component({
  selector: 'app-user-full',
  templateUrl: './user-full.component.html',
  styleUrls: ['./user-full.component.css']
})
export class UserFullComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router, private auth:AuthService,
    private userService:UserServiceService,private projectService:ProjectServiceService,
    private taskService:TaskServiceService, private activatedRoute:ActivatedRoute) {

    }
  ready:boolean=false;
  user:User;
  ngOnInit() {
  }
  

}