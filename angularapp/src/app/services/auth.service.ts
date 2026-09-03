import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { AppConstant } from '../app.constant';
 
@Injectable({
  providedIn: 'root'
})
export class AuthService {


  private apiUrl = AppConstant.apiUrl;

  constructor(private client : HttpClient) { }
 
  register(user : User) : Observable<any> {
    return this.client.post(`${this.apiUrl}/register`,user);
  }
 
  login(user : any) : Observable<any> {
    return this.client.post(`${this.apiUrl}/login`,user);
  }
 
  logout() {
    localStorage.clear();
  }

  isLoggedIn() : boolean {
    const token = localStorage.getItem('token');
    return !!token;
  }

  getRole() : String {
    const role = localStorage.getItem('userRole');
    return role;
  }
 
}
 
 
