import { Component, OnInit } from '@angular/core';
import { UserCrudService } from '../../core';
import { UserDataHolderService } from '../../core';
import { faUser } from '@fortawesome/free-solid-svg-icons';
import { faHome } from '@fortawesome/free-solid-svg-icons';
import { faUserCog } from '@fortawesome/free-solid-svg-icons';
import { faBuilding } from '@fortawesome/free-solid-svg-icons';
import { faUserTie } from '@fortawesome/free-solid-svg-icons';

@Component({
	selector: 'app-side-user',
	templateUrl: './side-user.component.html',
	styleUrls: ['./side-user.component.scss']
})
export class SideUserComponent implements OnInit {

	// fontawesome :
	faUser = faUser;
	faHome = faHome;
    faUserCog = faUserCog;
    faBuilding = faBuilding;
    faUserTie = faUserTie;

	SESSION_ATTRIBUTE_NAME = 'authenticatedUser';
	userName: string;

    constructor(private userCrud: UserCrudService,
                private dataHolder: UserDataHolderService) { }

    ngOnInit() {
        this.userCrud.getUserInfos()
            .subscribe(data => {
            	this.dataHolder.setName(data.nom);
            	this.dataHolder.setUser(data);
            }, error => console.log(error));

    	this.dataHolder.currentName.subscribe(name => this.userName = name);
    }

}
