import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  type='real';
  onClickReal(){
    this.type = 'real';
   
  }
  onClickAvailable(){
    this.type = 'available';

  }
  onClickAll(){
    this.type = 'all';

  }
}
