import { Component, OnInit} from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserServiceService } from 'src/app/services/user-service.service';
import { User} from 'src/app/DTOs/UserMain/UserMain';
import { timer } from 'rxjs';

@Component({
  selector: 'app-user-basic',
  templateUrl: './user-basic.component.html',
  styleUrls: ['./user-basic.component.css']
})
export class UserBasicComponent implements OnInit {

  readyUser: boolean;
  user: User;

  constructor(private router: Router,private userService: UserServiceService,private activatedRoute: ActivatedRoute) {
    this.readyUser = false;
  }
  
  ngOnInit() {
    timer(1000).subscribe(
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