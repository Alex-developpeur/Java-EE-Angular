import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, CanLoad, Route } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from './auth.service';

@Injectable({
    providedIn: 'root'
})
export class AppRoutingGuard implements CanActivate, CanLoad {

    constructor(
        private router: Router,
        private authenticationService: AuthenticationService
    ) { }

    canActivate(route: ActivatedRouteSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        if (!this.authenticationService.isUserLoggedIn()) {
            this.router.navigate(['deconnexion']);
            return false;
        }

        const roles = route.data.roles;
        if (roles && !roles.some(r => this.authenticationService.hasRole(r))) {
            this.router.navigate(['error', 'not-found']);
            return false;
        }

        return true;
    }

    canLoad(route: Route): Observable<boolean> | Promise<boolean> | boolean {
        if (!this.authenticationService.isUserLoggedIn()) {
            return false;
        }

        const roles = route.data && route.data.roles;
        console.log(roles);
        if (roles && !roles.some(r => this.authenticationService.hasRole(r))) {
            return false;
        }

        return true;
    }

}
