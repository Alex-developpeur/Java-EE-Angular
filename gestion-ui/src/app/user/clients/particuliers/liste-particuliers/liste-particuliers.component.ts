import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject } from "rxjs";
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { UserCrudService } from '../../../../core';
import { User } from "../../../../model/user";
import { Personne } from "../../../../model/personne";
import { ModalUserComponent } from '../../../modal-user/modal-user.component';

@Component({
	selector: 'app-liste-particuliers',
	templateUrl: './liste-particuliers.component.html',
	styleUrls: ['./liste-particuliers.component.scss']
})
export class ListeParticuliersComponent implements OnInit {

	displayedColumns: string[] = ['nom', 'actions'];
	dataSource: MatTableDataSource<Personne>;
	@ViewChild(MatPaginator) paginator: MatPaginator;
	@ViewChild(MatSort) sort: MatSort;

	utilisateur: User;
	listePersonne: Personne[];

	constructor(private router: Router,
				private userCrud: UserCrudService,
				public dialog: MatDialog) { }

	ngOnInit() {
		this.reloadData();
	}

	reloadData() {
        this.userCrud.getUserInfos()
            .subscribe(data => {
            	this.utilisateur = data;
            	this.listePersonne = data.entrepriseList[0].personneList;

				this.dataSource = new MatTableDataSource(this.listePersonne);
				this.dataSource.paginator = this.paginator;
				this.dataSource.sort = this.sort;
//            	this.userCrud.setUser(data);
            }, error => console.log(error));
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

	createPersonne() {
		this.router.navigate(['mon-compte/clients/particuliers/creer']);
	}

	detailsPersonne(id: number){
		this.router.navigate(['mon-compte/clients/particuliers/details', id]);
	}

	updatePersonne(id: number){
		this.router.navigate(['mon-compte/clients/particuliers/modifier', id]);
	}

}
