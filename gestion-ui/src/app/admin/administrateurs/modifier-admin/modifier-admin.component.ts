import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from '../../../core';
import { ValidFormService } from '../../../core';
import { User } from "../../../model/user";

@Component({
	selector: 'app-modifier-admin',
	templateUrl: './modifier-admin.component.html',
	styleUrls: ['./modifier-admin.component.scss']
})
export class ModifierAdminComponent implements OnInit {

	id: number;
	administrateur: User;
	adminForm: FormGroup;

	constructor(private route: ActivatedRoute,
				private router: Router,
				private adminService: AdminService,
				private validFormService: ValidFormService,
				private _snackBar: MatSnackBar) { }

	ngOnInit() {
		this.administrateur = new User();
		this.adminForm = this.validFormService.getFormGroupUser();
		this.id = this.route.snapshot.params['id'];

		this.adminService.getResource("Administrateur", this.id)
			.subscribe(data => {
				this.administrateur = data;
				this.adminForm.setValue({
					nomForm: this.administrateur.nom,
					emailForm: this.administrateur.email,
					mdpForm: this.administrateur.mdp,
					mdpConfirmForm: this.administrateur.mdp
				});
			}, error => console.log(error));
	}

	onSubmit() {
		if(!this.adminForm.invalid) {
			if(this.adminForm.get('nomForm').value !== "")
				this.administrateur.nom = this.adminForm.get('nomForm').value;
			this.administrateur.email = this.adminForm.get('emailForm').value;
			this.administrateur.mdp = this.adminForm.get('mdpForm').value;

			this.adminService.updateResource("Administrateur", this.id, this.administrateur)
				.subscribe(data => {this.administrateur = new User(); this.gotoList();},
				error => this.toastError(error.error));
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
	            panelClass: 'warning-snackbar'
	        });
	    }
    }
}
