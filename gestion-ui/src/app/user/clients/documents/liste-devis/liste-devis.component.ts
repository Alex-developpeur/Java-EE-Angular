import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject } from "rxjs";
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { UserCrudService } from '../../../../core';
import { UserDataHolderService } from '../../../../core';
import { Devis } from "../../../../model/documents/devis";
import { ModalUserComponent } from '../../../modal-user/modal-user.component';

@Component({
	selector: 'app-liste-devis',
	templateUrl: './liste-devis.component.html',
	styleUrls: ['./liste-devis.component.scss']
})
export class ListeDevisComponent implements OnInit {

	@Input() devisList: Devis[];
	devis: Devis;

	displayedColumns: string[] = ['numeroDevis', 'actions'];
	dataSource: MatTableDataSource<Devis>;
	@ViewChild(MatPaginator) paginator: MatPaginator;
	@ViewChild(MatSort) sort: MatSort;

	constructor(private router: Router,
				private userCrud: UserCrudService,
				private dataHolder: UserDataHolderService,
				public dialog: MatDialog) { }

	ngOnInit(): void {
		//		console.log(this.data.nom);
		//		this.devisListe = this.data.devisList;
		this.devis = this.devisList[0];
		this.reloadData();
	}

	reloadData() {
		this.dataSource = new MatTableDataSource(this.devisList);
		this.dataSource.paginator = this.paginator;
		this.dataSource.sort = this.sort;
	}


	applyFilter(event: Event) {
		const filterValue = (event.target as HTMLInputElement).value;
		this.dataSource.filter = filterValue.trim().toLowerCase();

		if (this.dataSource.paginator) {
			this.dataSource.paginator.firstPage();
		}
	}

	deletePersonne(id: number) {
		this.userCrud.deleteResource("Personne", id)
		.subscribe(
			data => {
				this.reloadData();
			},
			error => console.log(error));
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

	createDevis() {
		this.router.navigate(['mon-compte/clients/creer-devis']);
	}

	showDevis(id: number){
		const user = this.dataHolder.getUser().email;
		const entrepriseId =  this.dataHolder.getUser().entrepriseList[0].id;
		const numeroDevis = this.devisList[id].numeroDevis;

//		this.userCrud.setImage(entrepriseId);

		this.userCrud.getPDF(user, entrepriseId, numeroDevis).subscribe((response)=>{
			let file = new Blob([response], { type: 'application/pdf' });            
			var fileURL = URL.createObjectURL(file);
			window.open(fileURL);
		});
	}

	updatePersonne(id: number){
		this.router.navigate(['mon-compte/clients/particuliers/modifier', id]);
	}

}
