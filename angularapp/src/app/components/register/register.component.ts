import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user : User = {
    email: '',
    password: '',
    username: '',
    mobileNumber: '',
    userRole: ''
  };

  confirmPassword : string = '';

  errorMessage = '';
  successMessage = '';
  showModal = false;

  constructor(private authService : AuthService, private router : Router) { }

  ngOnInit(): void {
  }

  register(form: NgForm) {
    if (
      form.invalid ||
      !this.isPasswordStrong(this.user.password) ||
      this.user.password !== this.confirmPassword
    ) {
      this.errorMessage = this.getErrorMsg();
      return;
    }

    this.authService.register(this.user).subscribe(
      (result) => {
        console.log(result);
        
        this.successMessage = "Registration successful!";
        this.showModal = true;
      },
      (error) => {
        this.errorMessage = error.error || "Registration failed.";
      }
    );
  }

  isPasswordStrong(password: string): boolean {
    return password.length >= 8 &&
           /[A-Z]/.test(password) &&
           /[a-z]/.test(password) &&
           /\d/.test(password) &&
           /[^A-Za-z0-9]/.test(password);
  }

  getErrorMsg(): string {
    if (!this.user.username) return "Username is required";
    if (!this.user.email) return "Email is required";
    if (!/\S+@\S+\.\S+/.test(this.user.email)) return "Please enter valid email";
    if (!this.user.password) return "Password is required";
    if (!this.isPasswordStrong(this.user.password))
      return "Password must include uppercase, lowercase, number, and special character(minimum length should be 8 characters)";
    if (!this.confirmPassword) return "Confirm password is required";
    if (this.user.password !== this.confirmPassword) return "Passwords do not match";
    if (!this.user.mobileNumber || !/^\d{10}$/.test(this.user.mobileNumber)) return "Mobile number must be 10 digits";
    if (!this.user.userRole) return "Role is required";
    return '';
  }

  redirectToLogin() {
    this.router.navigate(['/login']);
  }


}
