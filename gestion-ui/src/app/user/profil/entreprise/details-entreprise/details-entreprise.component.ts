import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { UserCrudService } from '../../../../core';
import { Entreprise } from "../../../../model/entreprise";
import { Coordonnees } from "../../../../model/coordonnees";
import { ModalUserComponent } from '../../../modal-user/modal-user.component';

@Component({
	selector: 'app-details-entreprise',
	templateUrl: './details-entreprise.component.html',
	styleUrls: ['./details-entreprise.component.scss']
})
export class DetailsEntrepriseComponent implements OnInit {

	@Input() entreprise: Entreprise;
	coordonnees: Coordonnees;

	constructor(private router: Router,
				private userCrud: UserCrudService,
				private dialog: MatDialog) { }

	ngOnInit(): void {
        this.coordonnees = this.entreprise.coordonneesList[0];
	}

    updateEntreprise(id: number){
        this.router.navigate(['mon-compte/profil/entreprise/modifier', id]);
    }

    openDialog(id: number) {
        let dialogRef = this.dialog.open(ModalUserComponent, {
            height: '200px',
            width: '500px',
        });

        dialogRef.componentInstance.title = 'Suppression entreprise';
        dialogRef.componentInstance.content = 
            '<p>Voulez-vous supprimer votre entreprise ?</p> <p>Cette action sera définitive.</p>';

        dialogRef.afterClosed().subscribe(result => {
            if(result)
                this.deleteEntreprise(id);
        });
    }

    deleteEntreprise(id: number) {
        this.userCrud.deleteResource("Entreprise", id)
            .subscribe(data => {
                    this.entreprise = null;
					this.coordonnees = null;
                }, error => console.log(error));
    }

}
