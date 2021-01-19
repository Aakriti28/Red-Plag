import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable  } from 'rxjs'
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable()

export class UserServiceService {



  constructor(private http: HttpClient,  private router : Router) {}

registerNewUser(userData) : Observable<any> {
  return this.http.post('http://127.0.0.1:8000/api/signup/', userData);
}

loginNewUser(userData) : Observable<any> {
  return this.http.post('http://127.0.0.1:8000/api/signin/', userData);
}


uploadFile_service(userData) : Observable<any> {
  return this.http.post('http://127.0.0.1:8000/files/upload/', userData);
}

compareFilesService(userData) : Observable<any> {
  return this.http.post('http://127.0.0.1:8000/files/compare/', userData, {
    observe: 'response', responseType:'blob'
  });
}

downloadFilesService(userData) : Observable<any> {
  return this.http.post('http://127.0.0.1:8000/files/download/', userData, {
    observe: 'response', responseType:"blob"
  });
}


downloadResImService() : Observable<any> {
  return this.http.get('http://127.0.0.1:8000/files/resim/',  {
    observe: 'response', responseType:"blob"
  });
}

deleteFileService(userData) : Observable<any> {
  return this.http.post('http://127.0.0.1:8000/files/delete/', userData);
}



changePass(userData) : Observable<any> {

  return this.http.put('http://127.0.0.1:8000/api/change/', userData);
}




listFilesService() : Observable<string[]> {

  return this.http.get<string[]>('http://127.0.0.1:8000/files/lists/');
}

public get loggedIn(): boolean {
  return (localStorage.getItem('userToken') !== null);
}

LogoutUser(){
  localStorage.removeItem('userToken');
  this.router.navigate(['/home']);
}

//getUserClaims(){
  //return  this.http.get('http://127.0.0.1:8000/api/');
 //}

}
