import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {recogniseError} from 'src/app/services/exceptions';
import { AuthService } from 'src/app/services/auth-service.service';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css']
})
export class ErrorComponent implements OnInit {

  constructor(private auth: AuthService,private activatedRoute:ActivatedRoute) { }
  error ='';
  ngOnInit() {
    if(this.activatedRoute.snapshot.params['error']=='401')
    this.auth.authenticateNotProblem('Token expired');
    this.error=recogniseError(this.activatedRoute.snapshot.params['error']);
  }

}
