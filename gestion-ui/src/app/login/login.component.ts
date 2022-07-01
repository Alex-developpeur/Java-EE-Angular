import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../core';
import { faUser } from '@fortawesome/free-solid-svg-icons';
import { faKey } from '@fortawesome/free-solid-svg-icons';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    faUser = faUser;
    faKey = faKey;
    username: string;
    password : string;
    errorMessage = 'E-mail ou mot de passe incorrect.';
    successMessage: string;
    invalidLogin = false;
    loginSuccess = false;

    constructor(private router: Router,
        private authenticationService: AuthenticationService) { }

    ngOnInit() {
    }

    handleLogin() {
        this.authenticationService.authenticationService(this.username, this.password).subscribe((result)=> {
            this.invalidLogin = false;
            this.loginSuccess = true;
            this.successMessage = 'Connexion réussie.';
            if(this.authenticationService.userRole == 'ADMIN') {
                this.router.navigate(['/admin/tableau-de-bord']);
            } else if(this.authenticationService.userRole == 'USER') {
                this.router.navigate(['/mon-compte/tableau-de-bord']);
            } else
                this.router.navigate(['']);
        }, (error) => {
            console.log(error);
            this.invalidLogin = true;
            this.loginSuccess = false;
        });      
    }
}