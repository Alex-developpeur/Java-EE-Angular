import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserCrudService } from '../../core';
import { User } from "../../model/user";
import { faHome } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-dashboard-user',
  templateUrl: './dashboard-user.component.html',
  styleUrls: ['./dashboard-user.component.scss']
})
export class DashboardUserComponent implements OnInit {

    // Fontawesome :
    faHome = faHome;

    
    nom: string;

    constructor(private route: ActivatedRoute,
                private router: Router,
                private userCrud: UserCrudService
                ) { }

    ngOnInit() {
        this.userCrud.getUserInfos().subscribe((result) => {
            this.nom = result.nom;
        });
    }

}
