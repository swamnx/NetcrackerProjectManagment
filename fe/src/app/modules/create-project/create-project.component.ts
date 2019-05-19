import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import { Project } from 'src/app/DTOs/ProjectMain/ProjectMain';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { timer } from 'rxjs';
import { AuthService } from 'src/app/services/auth-service.service';

@Component({
  selector: 'app-create-project',
  templateUrl: './create-project.component.html',
  styleUrls: ['./create-project.component.css']
})
export class CreateProjectComponent implements OnInit {

  createProjectForm: FormGroup;
  sendingData: boolean = false;

  constructor(private auth: AuthService, private router: Router, private projectService: ProjectServiceService) {

    if(!auth.authenticated) this.auth.authenticateNot();
    if(auth.user && auth.user.role!='pm')this.router.navigateByUrl('/error/'+403);

    this.createProjectForm = new FormGroup({
      code: new FormControl('', [Validators.required,Validators.maxLength(50)]),
      description: new FormControl('', [Validators.required,Validators.maxLength(300)])
    })
  }

  ngOnInit() {
  }
  
  createProject() {
    this.sendingData = true;
    let project = new Project();
    project.code = this.createProjectForm.controls['code'].value;
    project.description = this.createProjectForm.controls['description'].value;
    timer(500).subscribe(
      val => {
        this.projectService.createProject(project).subscribe(
          value => {
            this.router.navigateByUrl('/projects/' + value.idProject);
          },
          error => {
            this.router.navigateByUrl('/error/' + error.status);
          }
        )
      }
    )
  }
}
