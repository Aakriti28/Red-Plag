import { Component, OnInit } from '@angular/core';
import { UserServiceService } from '../user-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  providers: [UserServiceService]

})
export class LoginComponent implements OnInit {
  user;
  constructor(private userSer: UserServiceService, private router : Router) { }

  ngOnInit()  {
    this.user = {
      email : '',
      password: ''
    };
    localStorage.clear();
  }
loginUser(){
  this.userSer.loginNewUser(this.user).subscribe(
    response => {
      console.log(response.token)
      localStorage.setItem('userToken',response.token);
      this.router.navigate(['/user']);

      alert('User '+ this.user.email + ' has been logged in')
    },
    error => {console.log('error', error), alert('error')
  }
  );
}
}
