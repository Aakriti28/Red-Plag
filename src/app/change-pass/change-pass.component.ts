import { Component, OnInit } from '@angular/core';
import { UserServiceService } from '../user-service.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-change-pass',
  templateUrl: './change-pass.component.html',
  styleUrls: ['./change-pass.component.scss']
})
export class ChangePassComponent implements OnInit {
  user;
  constructor(private userSer: UserServiceService, private router : Router) { }

  ngOnInit()  {
    this.user = {
      old_password : '',
      new_password: ''
    };
  }
changePassword(){
  this.userSer.changePass(this.user).subscribe(
    response => {
      console.log(response.token)
      localStorage.setItem('userToken',response.token);
      this.userSer.LogoutUser();
      this.router.navigate(['/login']);

      alert('User Password has been Updated Successfully. Please Login Again! :)')
    },
    error => {console.log('error', error), alert('error')
  }
  );
}
}
