import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth-service.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import {MatFormField} from '@angular/material';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit {
  constructor(private auth:AuthService, private http: HttpClient, private router: Router) { }

  ngOnInit() {
  }
  login(data){
       this.auth.authenticate(data.value.email,data.value.password);
  }
}
