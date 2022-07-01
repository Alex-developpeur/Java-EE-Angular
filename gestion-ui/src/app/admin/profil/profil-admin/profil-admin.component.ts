import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthenticationService } from '../../../core';
import { AdminService } from '../../../core';
import { User } from "../../../model/user";
import { ModalSupprimerComponent } from '../../modal-supprimer/modal-supprimer.component';

@Component({
	selector: 'app-profil-admin',
	templateUrl: './profil-admin.component.html',
	styleUrls: ['./profil-admin.component.scss']
})
export class ProfilAdminComponent implements OnInit {

	administrateur: User;

	constructor(private router: Router,
				private adminService: AdminService,
                private authenticationService: AuthenticationService,
				public dialog: MatDialog,
                private _snackBar: MatSnackBar) { }

    ngOnInit() {
          this.adminService.getAdminInfos()
            .subscribe(data => {
                this.administrateur = data;
            }, error => console.log(error));
    }

    updateAdministrateur(id: number){
        this.router.navigate(['admin/profil/modifier', id]);
    }

    openDialog(id: number) {
        let dialogRef = this.dialog.open(ModalSupprimerComponent, {
            height: '200px',
            width: '500px',
        });

        dialogRef.componentInstance.title = 'de compte';
        dialogRef.componentInstance.content = 'votre compte';

        dialogRef.afterClosed().subscribe(result => {
            if(result)
                this.deleteAdministrateur(id);
        });
    }

    deleteAdministrateur(id: number) {
        this.adminService.deleteResource("Administrateur", id)
            .subscribe(data => {
                    this.authenticationService.logout();
                    this.router.navigate(['/connexion']);
                }, error => console.log(error));

        this._snackBar.open("Compte supprimé avec succes.", 'x', {
            duration: 5000,
            panelClass: 'succes-snackbar'
        });
    }

    tableauDeBord() {
        this.router.navigate(['admin/tableau-de-bord']);
    }

}
