import { Component, OnInit, Input } from '@angular/core';
import { Project } from 'src/app/models/project';

@Component({
  selector: 'app-project-basic',
  templateUrl: './project-basic.component.html',
  styleUrls: ['./project-basic.component.css']
})
export class ProjectBasicComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  @Input() project:Project;
}
