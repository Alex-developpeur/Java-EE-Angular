import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { AdminService } from '../../../core';
import { User } from "../../../model/user";
import { ModalSupprimerComponent } from '../../modal-supprimer/modal-supprimer.component';

@Component({
	selector: 'app-details-admin',
	templateUrl: './details-admin.component.html',
	styleUrls: ['./details-admin.component.scss']
})
export class DetailsAdminComponent implements OnInit {

	id: number;
	administrateur: User;

	constructor(private route: ActivatedRoute,
				private router: Router,
				private adminService: AdminService,
				public dialog: MatDialog) { }

	ngOnInit() {
		this.administrateur = new User();
		this.id = this.route.snapshot.params['id'];

		this.adminService.getResource("Administrateur", this.id)
			.subscribe(data => {
				this.administrateur = data;
			}, error => console.log(error));
	}

	deleteAdministrateur(id: number) {
		this.adminService.deleteResource("Administrateur", id)
			.subscribe(data => {
					this.listAdministrateur();
				}, error => console.log(error));
	}

	updateAdministrateur(id: number){
		this.router.navigate(['admin/administrateurs/modifier', id]);
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

	listAdministrateur(){
		this.router.navigate(['admin/administrateurs']);
	}

}
