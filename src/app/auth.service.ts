import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable()
export class AuthService {
  constructor(private http: HttpClient) { }


  public get loggedIn(): boolean {
    return (localStorage.getItem('userToken') !== null);
  }
}
