import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from '../../../core';
import { Groupe } from "../../../model/groupe";

@Component({
	selector: 'app-modifier-groupe',
	templateUrl: './modifier-groupe.component.html',
	styleUrls: ['./modifier-groupe.component.scss']
})
export class ModifierGroupeComponent implements OnInit {

	id: number;
	groupe: Groupe;

	constructor(private route: ActivatedRoute,
				private router: Router,
				private _snackBar: MatSnackBar,
				private adminService: AdminService) { }

	ngOnInit() {
		this.groupe = new Groupe();
		this.id = this.route.snapshot.params['id'];

		this.adminService.getResource("Groupe", this.id)
			.subscribe(data => {
				this.groupe = data;
			}, error => console.log(error));
	}

	updateGroupe() {
		this.adminService.updateResource("Groupe", this.id, this.groupe)
			.subscribe(data => {this.groupe = new Groupe(); this.gotoList();},
			error => this.toastError(error.error));
	}

	onSubmit() {
		this.updateGroupe();    
	}

	onCancel() {
		this.gotoList();
	}

	gotoList() {
		this.router.navigate(['admin/groupes']);
	}

	toastError(error) {
		if(error.status === 500) {
			this._snackBar.open(error.message, 'x', {
				duration: 5000,
				panelClass: 'error-snackbar'
			});
		}
	}

}
