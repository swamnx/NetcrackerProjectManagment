import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth-service.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { routerNgProbeToken } from '@angular/router/src/router_module';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
})
export class RegistrationComponent implements OnInit {
  problem='';
  email='';
  passwordp='';
  constructor(private auth:AuthService, private http: HttpClient, private router: Router) { }

  ngOnInit() {
  }
  register(emailp,passwordp){
    let validator ={good: true,problem:'ffffuc'};
    if(validator.good == true){
      this.router.navigateByUrl('login');
    }
    else{
      this.problem=validator.problem;
    }
  }

}
