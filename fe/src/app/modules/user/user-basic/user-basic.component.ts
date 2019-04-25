import { Component, OnInit, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/services/auth-service.service';
import { UserServiceService } from 'src/app/services/user-service.service';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import { TaskServiceService } from 'src/app/services/task-service.service';
import { User, Project } from 'src/app/DTOs/UserMain/UserMain';
@Component({
  selector: 'app-user-basic',
  templateUrl: './user-basic.component.html',
  styleUrls: ['./user-basic.component.css']
})
export class UserBasicComponent implements OnInit {

  ready: boolean;
  user: User;

  constructor(private http: HttpClient, private router: Router, private auth: AuthService,
    private userService: UserServiceService, private projectService: ProjectServiceService,
    private taskService: TaskServiceService, private activatedRoute: ActivatedRoute) {
    this.ready = false;
  }
  
  ngOnInit() {
    const idUser = this.activatedRoute.snapshot.params['idUser'];
    this.userService.getUserById(idUser).subscribe(
      (value) => {
        this.user = value;
        this.ready = true;
      },
      (error) => {
        this.router.navigateByUrl('/error/' + error.status);
      }
    )
  }
}