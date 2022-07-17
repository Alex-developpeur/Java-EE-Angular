import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable, Subject } from "rxjs";
import { MatDialog } from '@angular/material/dialog';
import { AdminService } from '../../../core';
import { Groupe } from "../../../model/groupe";
import { ModalSupprimerComponent } from '../../modal-supprimer/modal-supprimer.component';

@Component({
	selector: 'app-liste-groupe',
	templateUrl: './liste-groupe.component.html',
	styleUrls: ['./liste-groupe.component.scss']
})
export class ListeGroupeComponent implements OnInit {

	dtOptions: DataTables.Settings = {};
	listeGroupe: Observable<Groupe[]>;
	groupes: Groupe[];

	constructor(private router: Router, private adminService: AdminService, public dialog: MatDialog) { }

	ngOnInit() {
		this.groupes = null;
		this.optionsTable();
		this.reloadData();
	}

	reloadData() {
		this.listeGroupe = this.adminService.getResourceList("Groupes");

		this.listeGroupe.subscribe(
			res => {
				this.groupes = res;
			}, 
		err => { console.log(err); }
		);
	}

	deleteGroupe(id: number) {
		this.groupes = null;
		this.adminService.deleteResource("Groupe", id)
		.subscribe(
			data => {
				this.reloadData();
			},
			error => console.log(error));
	}

	openDialog(id: number) {
		let dialogRef = this.dialog.open(ModalSupprimerComponent, {
			height: '200px',
			width: '500px',
		});

		dialogRef.componentInstance.title = 'groupe';
		dialogRef.componentInstance.content = 'ce groupe';

		dialogRef.afterClosed().subscribe(result => {
			if(result)
				this.deleteGroupe(id);
		});
	}	

	createGroupe() {
		this.router.navigate(['admin/groupes/creer']);
	}

	detailsGroupe(id: number){
		this.router.navigate(['admin/groupes/details', id]);
	}

	updateGroupe(id: number){
		this.router.navigate(['admin/groupes/modifier', id]);
	}

	optionsTable() {
		this.dtOptions = {
			responsive: true,
			language: {
				processing: "Traitement en cours ...",
				search: "Chercher :",
				lengthMenu: "Afficher _MENU_ lignes",
				info: "Lignes _START_ à _END_ sur _TOTAL_",
				infoEmpty: "Aucune ligne affichée",
				infoFiltered: "(Filtrer un maximum de_MAX_)",
				infoPostFix: "",
				loadingRecords: "Chargement...",
				zeroRecords: "Aucun résultat trouvé",
				emptyTable: "Aucune donnée disponible",
				paginate: {
					first: "<<",
					previous: "<",
					next: ">",
					last: ">>"
				},
				aria: {
					sortAscending: ": Trier par ordre croissant",
					sortDescending: ": Trier par ordre décroissant"
				}
			}
		};		
	}

}
