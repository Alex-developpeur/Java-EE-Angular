import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from '../../../core';
import { ValidFormService } from '../../../core';
import { User } from "../../../model/user";

@Component({
	selector: 'app-creer-admin',
	templateUrl: './creer-admin.component.html',
	styleUrls: ['./creer-admin.component.scss']
})
export class CreerAdminComponent implements OnInit {

	administrateur: User;
	adminForm: FormGroup;

	constructor(private router: Router,
				private _snackBar: MatSnackBar,
				private adminService: AdminService,
				private validFormService: ValidFormService) { }

	ngOnInit() {
		this.administrateur = new User();
		this.adminForm = this.validFormService.getFormGroupUser();
	}

	onSubmit() {
		if(!this.adminForm.invalid) {
			if(this.adminForm.get('nomForm').value !== "")
				this.administrateur.nom = this.adminForm.get('nomForm').value;
			this.administrateur.email = this.adminForm.get('emailForm').value;
			this.administrateur.mdp = this.adminForm.get('mdpForm').value;

			this.adminService.createResource("Administrateur", this.administrateur)
				.subscribe(data => this.gotoList(), error => this.toastError(error.error));
			this.administrateur = new User();
		}
	}

	onCancel() {
		this.gotoList();
	}

	gotoList() {
		this.router.navigate(['admin/administrateurs']);
	}

	validFormNom() {
		return this.validFormService.validNom(this.adminForm);
	}	

	validFormEmail() {
		return this.validFormService.validEmail(this.adminForm);
	}

	validFormMDP() {
		return this.validFormService.validMdp(this.adminForm);
	}

	validFormMDPConfirm() {
		return this.validFormService.validMdpConfirm();
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