import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-usernav',
  templateUrl: './usernav.component.html',
  styleUrls: ['./usernav.component.css']
})
export class UsernavComponent implements OnInit {

  name = localStorage.getItem('username');
  role = localStorage.getItem('userRole');
  
  showLogoutPopup = false;

  constructor(private authService : AuthService, private router : Router) { }

  ngOnInit(): void {
  }
  
  logout(){
    this.showLogoutPopup = true;    
  }

  Logout() {
    this.showLogoutPopup = false;
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  cancelLogout() {
    this.showLogoutPopup = false;
  }
}
