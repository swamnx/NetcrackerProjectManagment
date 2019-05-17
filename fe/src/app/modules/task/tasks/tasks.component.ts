import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth-service.service';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  type: string = 'real';

  constructor(private auth: AuthService) {
    if (!auth.authenticated) this.auth.authenticateNot();
  }

  ngOnInit() {
  }

  onClickReal() {
    this.type = 'real';

  }
  onClickAvailable() {
    this.type = 'available';

  }
  onClickAll() {
    this.type = 'all';

  }
}
