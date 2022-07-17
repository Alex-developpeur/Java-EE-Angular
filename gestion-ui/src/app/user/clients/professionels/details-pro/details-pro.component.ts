import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { UserCrudService } from '../../../../core';
import { UserDataHolderService } from '../../../../core';
import { Entreprise } from "../../../../model/entreprise";
import { Coordonnees } from "../../../../model/coordonnees";
import { Devis } from "../../../../model/documents/devis";
import { ModalUserComponent } from '../../../modal-user/modal-user.component';

@Component({
	selector: 'app-details-pro',
	templateUrl: './details-pro.component.html',
	styleUrls: ['./details-pro.component.scss']
})
export class DetailsProComponent implements OnInit {

	id: number;
	professionnel: Entreprise;
	coordonnees: Coordonnees;
    devis: Devis[];

	constructor(private route: ActivatedRoute,
				private router: Router,
				private userCrud: UserCrudService,
                private dataHolder: UserDataHolderService,
				private dialog: MatDialog) { }

	ngOnInit(): void {
		this.professionnel = new Entreprise();
		this.coordonnees = new Coordonnees();
        this.id = this.route.snapshot.params['id'];

        this.userCrud.getResource("Entreprise", this.id)
            .subscribe(data => {
				this.professionnel = data;
				this.coordonnees = data.coordonneesList[0];
                if(data.devisList !== undefined)
                    this.devis = data.devisList;
                this.dataHolder.setPro(this.professionnel);
            }, error => console.log(error));
	}

    updateEntreprise(id: number){
        this.router.navigate(['mon-compte/clients/professionnels/modifier', id]);
    }

    openDialog(id: number) {
        let dialogRef = this.dialog.open(ModalUserComponent, {
            height: '200px',
            width: '500px',
        });

        dialogRef.componentInstance.title = 'Suppression client professionnel';
        dialogRef.componentInstance.content = 
            '<p>Voulez-vous supprimer ce client ?</p> <p>Cette action sera définitive.</p>';

        dialogRef.afterClosed().subscribe(result => {
            if(result)
                this.deleteEntreprise(id);
        });
    }

    deleteEntreprise(id: number) {
        this.userCrud.deleteResource("Entreprise", id)
            .subscribe(data => {
                    this.professionnel = new Entreprise();
					this.coordonnees = new Coordonnees();
					this.listeClients();
                }, error => console.log(error));
    }

    listeClients() {
        this.router.navigate(['mon-compte/clients/professionnels']);
    }
}
