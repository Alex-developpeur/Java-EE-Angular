import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../core';
import { faUser } from '@fortawesome/free-solid-svg-icons';

@Component({
	selector: 'app-side-admin',
	templateUrl: './side-admin.component.html',
	styleUrls: ['./side-admin.component.scss']
})
export class SideAdminComponent implements OnInit {

	faUser = faUser;
	SESSION_ATTRIBUTE_NAME = 'authenticatedUser';
	userName: string;

    constructor(private adminService: AdminService) { }

    ngOnInit() {
    	this.adminService.currentName.subscribe(name => this.userName = name)
    }

}
