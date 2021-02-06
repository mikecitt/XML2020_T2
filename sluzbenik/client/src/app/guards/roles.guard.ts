import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class RolesGuard implements CanActivate {

  constructor(private auth: AuthService, private router: Router) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    this.auth.validateToken();
    if (this.auth.isLoggedIn()) {
      const expectedRoles: string = next.data.expectedRoles;
      const roles: string[] = expectedRoles.split('|', 2);
      if(this.auth.getCurrentUser().authorities[0])
        if (roles[0] != this.auth.getCurrentUser().authorities[0]) {
            this.router.navigate(['/dashboard']);
            return false;
        }

      return true;
    } else {
      this.router.navigate(['']);
      return false;
    }
  }
}
