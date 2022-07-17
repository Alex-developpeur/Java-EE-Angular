import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { AdminService } from '../../../core';
import { User } from "../../../model/user";
import { ModalSupprimerComponent } from '../../modal-supprimer/modal-supprimer.component';

@Component({
	selector: 'app-details-user',
	templateUrl: './details-user.component.html',
	styleUrls: ['./details-user.component.scss']
})
export class DetailsUserComponent implements OnInit {

	id: number;
	utilisateur: User;

    constructor(private route: ActivatedRoute,
                private router: Router,
                private adminService: AdminService,
                public dialog: MatDialog) { }

    ngOnInit() {
        this.utilisateur = new User();
        this.id = this.route.snapshot.params['id'];

        this.adminService.getResource("Utilisateur", this.id)
            .subscribe(data => {
                this.utilisateur = data;
            }, error => console.log(error));
    }

    deleteUtilisateur(id: number) {
        this.adminService.deleteResource("Utilisateur", id)
            .subscribe(data => {
                    this.listUtilisateur();
                }, error => console.log(error));
    }

    updateUtilisateur(id: number){
        this.router.navigate(['admin/utilisateurs/modifier', id]);
    }

    openDialog(id: number) {
        let dialogRef = this.dialog.open(ModalSupprimerComponent, {
            height: '200px',
            width: '500px',
        });

        dialogRef.componentInstance.title = 'utilisateur';
        dialogRef.componentInstance.content = 'cet utilisateur';

        dialogRef.afterClosed().subscribe(result => {
            if(result)
                this.deleteUtilisateur(id);
        });
    }    

    listUtilisateur() {
        this.router.navigate(['admin/utilisateurs']);
    }

}
