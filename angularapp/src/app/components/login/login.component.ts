import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user : any = {
    email : '',
    password : ''
  }

  errorMessage = '';

  constructor(private authService : AuthService, private router : Router) { }

  ngOnInit(): void {
  }


  onLogin(form : NgForm) {
    if(form.invalid) {
      return;
    }
    const loginPayload = {
      email: this.user.email,
      password: this.user.password
    }

    this.authService.login(loginPayload).subscribe((result)=>{
      console.log(result);
      
      localStorage.setItem('token' , result.token);
      localStorage.setItem('username' , result.username);
      localStorage.setItem('userRole' , result.userRole);
      localStorage.setItem('userId' , result.userId);

      this.user = {
        email : '',
        password : ''
      }

      this.router.navigate(['/home']);

    },
    (error)=>{
      console.log(error.error);
      
      this.errorMessage = error.error;
    });

  }

}
