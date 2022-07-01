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
	selector: 'app-liste-user',
	templateUrl: './liste-user.component.html',
	styleUrls: ['./liste-user.component.scss']
})
export class ListeUserComponent implements OnInit {

	displayedColumns: string[] = ['id', 'nom', 'email', 'mdp', 'actions'];
	dataSource: MatTableDataSource<User>;
	@ViewChild(MatPaginator) paginator: MatPaginator;
	@ViewChild(MatSort) sort: MatSort;

	listeUser: Observable<User[]>;

	constructor(private router: Router,
				private adminService: AdminService,
				public dialog: MatDialog) { }

	ngOnInit() {
		this.reloadData();
	}

	reloadData() {
		this.listeUser = this.adminService.getResourceList("Utilisateurs");

		this.listeUser.subscribe(
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

	deleteUtilisateur(id: number) {
		this.adminService.deleteResource("Utilisateur", id)
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

		dialogRef.componentInstance.title = 'utilisateur';
		dialogRef.componentInstance.content = 'cet utilisateur';

		dialogRef.afterClosed().subscribe(result => {
			if(result)
				this.deleteUtilisateur(id);
		});
	}	

	createUtilisateur() {
		this.router.navigate(['admin/utilisateurs/creer']);
	}

	detailsUtilisateur(id: number){
		this.router.navigate(['admin/utilisateurs/details', id]);
	}

	updateUtilisateur(id: number){
		this.router.navigate(['admin/utilisateurs/modifier', id]);
	}

}
