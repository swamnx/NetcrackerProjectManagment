import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import { Project } from 'src/app/DTOs/ProjectMain/ProjectMain';
import { routerNgProbeToken } from '@angular/router/src/router_module';

@Component({
  selector: 'app-create-project',
  templateUrl: './create-project.component.html',
  styleUrls: ['./create-project.component.css']
})
export class CreateProjectComponent implements OnInit {

  constructor(private router: Router, private projectService:ProjectServiceService) { }
  problem:string='';
  ngOnInit() {
  }
  createProject(data){
    let prj = new Project();
    prj.code = data.value.prjcode;
    prj.description = data.value.description;
    this.projectService.createProject(prj).subscribe(
      value=>{
        this.router.navigateByUrl('projects/'+value.idProject);
      },
      error=>{
        this.problem=error.status;
      }
    )
  }
}
