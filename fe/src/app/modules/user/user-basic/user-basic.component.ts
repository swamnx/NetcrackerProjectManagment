import { Component, OnInit} from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserServiceService } from 'src/app/services/user-service.service';
import { User} from 'src/app/DTOs/UserMain/UserMain';
import { timer } from 'rxjs';
import { AuthService } from 'src/app/services/auth-service.service';

@Component({
  selector: 'app-user-basic',
  templateUrl: './user-basic.component.html',
  styleUrls: ['./user-basic.component.css']
})
export class UserBasicComponent implements OnInit {

  readyUser: boolean=false;
  user: User;

  constructor(private auth:AuthService,private router: Router,private userService: UserServiceService,private activatedRoute: ActivatedRoute) {
    if(!auth.authenticated) this.auth.authenticateNot();
  }
  
  ngOnInit() {
    timer(500).subscribe(
      val=>{
          const idUser = this.activatedRoute.snapshot.params['idUser'];
          this.userService.getUserById(idUser).subscribe(
            (value) => {
              this.user = value;
              this.readyUser = true;
            },
            (error) => {
              this.router.navigateByUrl('/error/' + error.status);
            }
          )
      }
    )
  }
}