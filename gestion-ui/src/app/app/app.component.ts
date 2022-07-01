import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../core';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.scss']
})
export class AppComponent {

	title = 'Fatum';
	SESSION_ATTRIBUTE_ROLE = 'authenticatedRole';
	isLoggedIn = false;
	role: string;

    constructor(private authenticationService: AuthenticationService) {
        authenticationService.getLoggedInName.subscribe(isLogin => this.ngOnInit());
    }

    ngOnInit() {
    	this.isLoggedIn = this.authenticationService.isUserLoggedIn();
		this.role = sessionStorage.getItem(this.SESSION_ATTRIBUTE_ROLE);
    }

}
