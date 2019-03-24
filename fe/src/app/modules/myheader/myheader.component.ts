import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from 'src/app/services/auth-service.service';

@Component({
  selector: 'app-myheader',
  templateUrl: './myheader.component.html',
})
export class MyheaderComponent implements OnInit {

  constructor(private auth: AuthService, private http: HttpClient) { }

  ngOnInit() {
  }

}
