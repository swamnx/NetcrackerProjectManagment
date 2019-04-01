import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth-service.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit {
  email='';
  password='';
  constructor(private auth:AuthService, private http: HttpClient, private router: Router) { }

  ngOnInit() {
  }
  login(){
       this.auth.authenticate(this.email,this.password);
  }
}
