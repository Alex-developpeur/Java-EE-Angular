import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject } from "rxjs";
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { UserCrudService } from '../../../../core';
import { User } from "../../../../model/user";
import { Entreprise } from "../../../../model/entreprise";
import { ModalUserComponent } from '../../../modal-user/modal-user.component';

@Component({
	selector: 'app-liste-pro',
	templateUrl: './liste-pro.component.html',
	styleUrls: ['./liste-pro.component.scss']
})
export class ListeProComponent implements OnInit {

	displayedColumns: string[] = ['raisonSociale', 'nature', 'actions'];
	dataSource: MatTableDataSource<Entreprise>;
	@ViewChild(MatPaginator) paginator: MatPaginator;
	@ViewChild(MatSort) sort: MatSort;

	utilisateur: User;
	listeEntreprise: Entreprise[];

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
            	this.listeEntreprise = data.entrepriseList[0].entrepriseList;

				this.dataSource = new MatTableDataSource(this.listeEntreprise);
				this.dataSource.paginator = this.paginator;
				this.dataSource.sort = this.sort;

//            	this.userService.setUser(data);
            }, error => console.log(error));
	}

	applyFilter(event: Event) {
		const filterValue = (event.target as HTMLInputElement).value;
		this.dataSource.filter = filterValue.trim().toLowerCase();

		if (this.dataSource.paginator) {
			this.dataSource.paginator.firstPage();
		}
	}

	deleteEntreprise(id: number) {
		this.userCrud.deleteResource("Entreprise", id)
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

        dialogRef.componentInstance.title = 'Suppression client professionnel';
        dialogRef.componentInstance.content = 
            '<p>Voulez-vous supprimer ce client ?</p> <p>Cette action sera définitive.</p>';

		dialogRef.afterClosed().subscribe(result => {
			if(result)
				this.deleteEntreprise(id);
		});
	}	

	createEntreprise() {
		this.router.navigate(['mon-compte/clients/professionnels/creer']);
	}

	detailsEntreprise(id: number){
		this.router.navigate(['mon-compte/clients/professionnels/details', id]);
	}

	updateEntreprise(id: number){
		this.router.navigate(['mon-compte/clients/professionnels/modifier', id]);
	}

}
