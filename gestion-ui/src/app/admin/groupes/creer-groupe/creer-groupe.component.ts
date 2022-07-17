import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from '../../../core';
import { Groupe } from "../../../model/groupe";

@Component({
	selector: 'app-creer-groupe',
	templateUrl: './creer-groupe.component.html',
	styleUrls: ['./creer-groupe.component.scss']
})
export class CreerGroupeComponent implements OnInit {

	groupe: Groupe = new Groupe();
	submitted = false;

	constructor(private router: Router,
				private _snackBar: MatSnackBar,
				private adminService: AdminService) { }

	ngOnInit() {
	}

	newGroupe(): void {
		this.submitted = false;
		this.groupe = new Groupe();
	}

	onSubmit() {
		this.adminService.createResource("Groupe", this.groupe)
			.subscribe(data => {this.gotoList(); this.submitted = true;},
			error => this.toastError(error.error));
		this.groupe = new Groupe();
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
