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

  constructor(private router: Router, private projectService:ProjectServiceService) {
    this.createProjectForm = new FormGroup({
      code: new FormControl('',[Validators.required]),
      description:new FormControl('',[Validators.required])
    })
  }
  ngOnInit() {
  }
  createProject(){
    let project = new Project();
    project.code=this.createProjectForm.controls['code'].value;
    project.description=this.createProjectForm.controls['description'].value;
    this.projectService.createProject(project).subscribe(
      value=>{
        this.router.navigateByUrl('/projects/'+value.idProject);
      },
      error=>{
        this.router.navigateByUrl('/error/'+error.status);
      }
    )
  }
}
