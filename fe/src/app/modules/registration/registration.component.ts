import { Component, OnInit, NgModule} from '@angular/core';
import { AuthService } from 'src/app/services/auth-service.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
})
export class RegistrationComponent implements OnInit {
  constructor(private auth:AuthService, private http: HttpClient, private router: Router) {
  }

  ngOnInit() {
  }
  register(data){
    this.auth.register(data.value.email,data.value.name,data.value.password,data.value.role);
  }
}
