import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { UserComponent } from './user/user.component';
import { ChangePassComponent } from './change-pass/change-pass.component';

import { AuthGuard } from './auth.guard';

const routes: Routes = [
  {path: '', redirectTo:'/home', pathMatch : 'full'},
  {path: 'login', component: LoginComponent
},
  {path: 'register', component: RegisterComponent
},
{path: 'user', component: UserComponent, canActivate: [AuthGuard]
},

{path: 'change_pass', component: ChangePassComponent, canActivate: [AuthGuard]
},
  {path: '**', component: HomeComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
