import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthguardGuard implements CanActivate {

  constructor(private authService : AuthService, private router : Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    
    if(this.authService.isLoggedIn()) {
      const expectedRole = route.data['expectedRole'];
      const actualRole = this.authService.getRole();
      if(expectedRole && actualRole !== expectedRole) {
        this.router.navigate(['/error']);
        return false;
      }
    }
    else {
      this.router.navigate(['/login']);
    }
    

    return true;
  }
  
}
