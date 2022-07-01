import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from '../model/user';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    public getLoggedInName = new Subject();
    SESSION_ATTRIBUTE_NAME = 'authenticatedUser';
    SESSION_ATTRIBUTE_LOGIN = 'authenticatedLogin';
    SESSION_ATTRIBUTE_PASSWORD = 'authenticatedPassword';
    SESSION_ATTRIBUTE_ROLE = 'authenticatedRole';

    public userRole: string;

    constructor(private http: HttpClient) { }

    authenticationService(login: String, password: String) {
        return this.http.get('http://localhost:8080/api/v1/connection',
            { headers: { authorization: this.createBasicAuthToken(login, password) } }).pipe(map((res : any) => {
                this.userRole = res.groupesSet[0].nom;
                this.registerSuccessfulLogin(res.nom, login, password, this.userRole);
                this.getLoggedInName.next(true);

            }));
    }

    createBasicAuthToken(login: String, password: String) {
        return 'Basic ' + window.btoa(login + ":" + password);
    }

    registerSuccessfulLogin(username, login, password, role) {
        sessionStorage.setItem(this.SESSION_ATTRIBUTE_NAME, username);
        sessionStorage.setItem(this.SESSION_ATTRIBUTE_LOGIN, login);
        sessionStorage.setItem(this.SESSION_ATTRIBUTE_PASSWORD, password);
        sessionStorage.setItem(this.SESSION_ATTRIBUTE_ROLE, role);
    }

    logout() {
        sessionStorage.removeItem(this.SESSION_ATTRIBUTE_NAME);
        sessionStorage.removeItem(this.SESSION_ATTRIBUTE_LOGIN);
        sessionStorage.removeItem(this.SESSION_ATTRIBUTE_PASSWORD);
        sessionStorage.removeItem(this.SESSION_ATTRIBUTE_ROLE);
        this.userRole = null;
        this.getLoggedInName.next(false);
    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem(this.SESSION_ATTRIBUTE_LOGIN);
        if (user === null) return false
            return true
    }

    getLoggedInUserName() {
        let user = sessionStorage.getItem(this.SESSION_ATTRIBUTE_LOGIN);
        if (user === null) return ''
            return user
    }

    hasRole(role: String) {
        return this.isUserLoggedIn() && sessionStorage.getItem(this.SESSION_ATTRIBUTE_ROLE) === role;
    }

}
