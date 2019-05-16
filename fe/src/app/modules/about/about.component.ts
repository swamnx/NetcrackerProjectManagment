import { Component, OnInit, Inject, ChangeDetectionStrategy } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/services/auth-service.service';
import { UserServiceService } from 'src/app/services/user-service.service';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import { TaskServiceService } from 'src/app/services/task-service.service';

export class MyData{
  test:string;
}
@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router, private auth:AuthService,
    private userService:UserServiceService,private projectService:ProjectServiceService,
    private taskService:TaskServiceService, private activatedRoute:ActivatedRoute) { }
  ngOnInit() {
  }
}
