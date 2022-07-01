import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthenticationService } from '../../../core';
import { UserCrudService } from '../../../core';
import { UserDataHolderService } from '../../../core';
import { User } from "../../../model/user";
import { Entreprise } from "../../../model/entreprise";
import { Coordonnees } from "../../../model/coordonnees";
import { ModalUserComponent } from '../../modal-user/modal-user.component';

@Component({
	selector: 'app-profil-user',
	templateUrl: './profil-user.component.html',
	styleUrls: ['./profil-user.component.scss']
})
export class ProfilUserComponent implements OnInit {

	utilisateur: User;
    entreprise: Entreprise;
    image: File;

    constructor(private router: Router,
        private userCrud: UserCrudService,
        private dataHolder: UserDataHolderService,
        private authenticationService: AuthenticationService,
        private dialog: MatDialog,
        private _snackBar: MatSnackBar) { }

    ngOnInit() {
        this.userCrud.getUserInfos()
        .subscribe(data => {
            this.dataHolder.setUser(data);
            this.utilisateur = data;
            this.entreprise = data.entrepriseList[0];
        }, error => console.log(error));
    }

    updateUtilisateur(id: number){
        this.router.navigate(['mon-compte/profil/modifier', id]);
    }

    openDialog(id: number) {
        let dialogRef = this.dialog.open(ModalUserComponent, {
            height: '200px',
            width: '500px',
        });

        dialogRef.componentInstance.title = 'Suppression de compte';
        dialogRef.componentInstance.content = 
        '<p>Voulez-vous supprimer votre compte ?</p> <p>Cette action sera définitive.</p>';

        dialogRef.afterClosed().subscribe(result => {
            if(result)
                this.deleteUtilisateur(id);
        });
    }

    deleteUtilisateur(id: number) {
        this.userCrud.deleteResource("Utilisateur", id)
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
        this.router.navigate(['mon-compte/tableau-de-bord']);
    }

    creerEntreprise() {
        this.router.navigate(['mon-compte/profil/entreprise/creer']);        
    }

    fileChange(event: any) {
        let reader = new FileReader();
        if(event.target.files && event.target.files.length > 0) {
            this.image = event.target.files[0];
        }
    }

    upload() {
        const entrepriseId = this.dataHolder.getUser().entrepriseList[0].id;
        this.userCrud.uploadImage(entrepriseId, this.image);
    }
}
