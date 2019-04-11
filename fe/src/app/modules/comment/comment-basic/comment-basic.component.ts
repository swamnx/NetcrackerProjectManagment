import { Component, OnInit, Input } from '@angular/core';
import { User } from 'src/app/models/user';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { UserServiceService } from 'src/app/services/user-service.service';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import { AuthService } from 'src/app/services/auth-service.service';
import { TaskServiceService } from 'src/app/services/task-service.service';
import { CommentBasicWithIds } from 'src/app/DTOs/comment';
@Component({
  selector: 'app-comment-basic',
  templateUrl: './comment-basic.component.html',
  styleUrls: ['./comment-basic.component.css']
})
export class CommentBasicComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router, private auth:AuthService,
    private userService:UserServiceService,private projectService:ProjectServiceService,
    private taskService:TaskServiceService, private activatedRoute:ActivatedRoute) { }

    ready:boolean=false;
  ngOnInit() {
  }
  @Input() comment:CommentBasicWithIds;
}
