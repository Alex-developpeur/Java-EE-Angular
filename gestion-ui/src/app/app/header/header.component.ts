import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../core';
import { faShieldAlt } from '@fortawesome/free-solid-svg-icons';
import { faSignOutAlt } from '@fortawesome/free-solid-svg-icons';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

    faShieldAlt = faShieldAlt;
    faSignOutAlt = faSignOutAlt;
	isLoggedIn = false;

    constructor(private authenticationService: AuthenticationService) { 
        authenticationService.getLoggedInName.subscribe(isLogin => this.ngOnInit());
    }

    ngOnInit() {
        this.isLoggedIn = this.authenticationService.isUserLoggedIn();
    }

    handleLogout() {
        this.authenticationService.logout();
        this.isLoggedIn = this.authenticationService.isUserLoggedIn();
    }

}
