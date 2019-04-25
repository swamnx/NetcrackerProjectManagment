import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import { Project } from 'src/app/DTOs/ProjectMain/ProjectMain';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-project',
  templateUrl: './create-project.component.html',
  styleUrls: ['./create-project.component.css']
})
export class CreateProjectComponent implements OnInit {

  createProjectForm:FormGroup;

  project:Project;

  constructor(private router: Router, private projectService:ProjectServiceService) {
    this.project = new Project();
    this.createProjectForm = new FormGroup({
      'code': new FormControl(
        this.project.code,
        [Validators.required]
      ),
      'description':new FormControl(
        this.project.description,
        [Validators.required]
      ),
    })
  }
  ngOnInit() {
  }
  createProject(){
    this.projectService.createProject(this.project).subscribe(
      value=>{
        this.router.navigateByUrl('projects/'+value.idProject);
      },
      error=>{
        this.router.navigateByUrl('/error/'+error.status);
      }
    )
  }
}
