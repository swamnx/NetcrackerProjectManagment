import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth-service.service';

@Component({
  selector: 'app-myheader',
  templateUrl: './myheader.component.html',
  styleUrls: ['myheader.component.css']
})
export class MyheaderComponent implements OnInit {

  constructor(private auth: AuthService) { }

  ngOnInit() {
  }

}
