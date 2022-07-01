import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { UserCrudService } from '../../../../core';
import { UserDataHolderService } from '../../../../core';
import { Personne } from "../../../../model/personne";
import { Coordonnees } from "../../../../model/coordonnees";
import { Devis } from "../../../../model/documents/devis";
import { ModalUserComponent } from '../../../modal-user/modal-user.component';

@Component({
	selector: 'app-details-particulier',
	templateUrl: './details-particulier.component.html',
	styleUrls: ['./details-particulier.component.scss']
})
export class DetailsParticulierComponent implements OnInit {

	id: number;
	personne: Personne;
	coordonnees: Coordonnees;
    devis: Devis[];

	constructor(private route: ActivatedRoute,
				private router: Router,
                private userCrud: UserCrudService,
                private dataHolder: UserDataHolderService,
				private dialog: MatDialog) { }

	ngOnInit(): void {
		this.personne = new Personne();
		this.coordonnees = new Coordonnees();
        this.id = this.route.snapshot.params['id'];

        this.userCrud.getResource("Personne", this.id)
            .subscribe(data => {
				this.personne = data;
				this.coordonnees = data.coordonnees;
                if(data.devisList !== undefined)
                    this.devis = data.devisList;
                this.dataHolder.setParticulier(this.personne);
            }, error => console.log(error));
	}

    updatePersonne(id: number){
        this.router.navigate(['mon-compte/clients/particuliers/modifier', id]);
    }

    openDialog(id: number) {
        let dialogRef = this.dialog.open(ModalUserComponent, {
            height: '200px',
            width: '500px',
        });

        dialogRef.componentInstance.title = 'Suppression client particulier';
        dialogRef.componentInstance.content = 
            '<p>Voulez-vous supprimer ce client ?</p> <p>Cette action sera définitive.</p>';

        dialogRef.afterClosed().subscribe(result => {
            if(result)
                this.deletePersonne(id);
        });
    }

    deletePersonne(id: number) {
        this.userCrud.deleteResource("Personne", id)
            .subscribe(data => {
                    this.personne = new Personne();
					this.coordonnees = new Coordonnees();
					this.listeClients();
                }, error => console.log(error));
    }

    listeClients() {
        this.router.navigate(['mon-compte/clients/particuliers']);
    }
}
