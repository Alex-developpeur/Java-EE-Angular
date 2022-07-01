import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject } from "rxjs";
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { AdminService } from '../../../core';
import { User } from "../../../model/user";
import { ModalSupprimerComponent } from '../../modal-supprimer/modal-supprimer.component';

@Component({
	selector: 'app-liste-admin',
	templateUrl: './liste-admin.component.html',
	styleUrls: ['./liste-admin.component.scss']
})
export class ListeAdminComponent implements OnInit {

	displayedColumns: string[] = ['id', 'nom', 'email', 'mdp', 'actions'];
	dataSource: MatTableDataSource<User>;
	@ViewChild(MatPaginator) paginator: MatPaginator;
	@ViewChild(MatSort) sort: MatSort;

	listeAdmin: Observable<User[]>;

	constructor(private router: Router,
				private adminService: AdminService,
				public dialog: MatDialog) { }

	ngOnInit() {
		this.reloadData();
	}

	reloadData() {
		this.listeAdmin = this.adminService.getResourceList("Administrateurs");

		this.listeAdmin.subscribe(
			res => {
				this.dataSource = new MatTableDataSource(res);
				this.dataSource.paginator = this.paginator;
				this.dataSource.sort = this.sort;
			}, 
			err => { console.log(err); }
			);
	}

	applyFilter(event: Event) {
		const filterValue = (event.target as HTMLInputElement).value;
		this.dataSource.filter = filterValue.trim().toLowerCase();

		if (this.dataSource.paginator) {
			this.dataSource.paginator.firstPage();
		}
	}

	deleteAdministrateur(id: number) {
		this.adminService.deleteResource("Administrateur", id)
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

		dialogRef.componentInstance.title = 'administrateur';
		dialogRef.componentInstance.content = 'cet administrateur';

		dialogRef.afterClosed().subscribe(result => {
			if(result)
				this.deleteAdministrateur(id);
		});
	}	

	createAdministrateur() {
		this.router.navigate(['admin/administrateurs/creer']);
	}

	detailsAdministrateur(id: number){
		this.router.navigate(['admin/administrateurs/details', id]);
	}

	updateAdministrateur(id: number){
		this.router.navigate(['admin/administrateurs/modifier', id]);
	}

}
