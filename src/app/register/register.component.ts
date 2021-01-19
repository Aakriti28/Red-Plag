import { Component, OnInit } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { UserServiceService } from '../user-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
  providers: [UserServiceService]
})
export class RegisterComponent implements OnInit {
user;
  constructor(private userSer: UserServiceService, private router : Router) { }

  ngOnInit()  {
    this.user = {
      email : '',
      password: ''
    };
    localStorage.clear();

  }
registerUser(){
  this.userSer.registerNewUser(this.user).subscribe(
    response => {
      alert('User '+ this.user.email + ' has been created');
      localStorage.setItem('userToken',response.token);
      this.router.navigate(['/user']);
    },
    error => {console.log('error', error), alert('error')}
  );
}
}
