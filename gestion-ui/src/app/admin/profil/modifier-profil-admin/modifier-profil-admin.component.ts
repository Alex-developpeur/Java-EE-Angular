import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthenticationService } from '../../../core';
import { AdminService } from '../../../core';
import { ValidFormService } from '../../../core';
import { User } from "../../../model/user";

@Component({
	selector: 'app-modifier-profil-admin',
	templateUrl: './modifier-profil-admin.component.html',
	styleUrls: ['./modifier-profil-admin.component.scss']
})
export class ModifierProfilAdminComponent implements OnInit {

    SESSION_ATTRIBUTE_NAME = 'authenticatedUser';
    ancienMotDePasse: string;
	administrateur: User;
	adminForm: FormGroup;

	constructor(private router: Router,
				private authenticationService: AuthenticationService,
				private adminService: AdminService,
				private validFormService: ValidFormService,
				private _snackBar: MatSnackBar) { }

	ngOnInit() {
		this.administrateur = new User();
		this.adminForm = this.validFormService.getFormGroupUser();

		this.adminService.getAdminInfos()
			.subscribe(data => {
				this.administrateur = data;
				this.ancienMotDePasse = this.administrateur.mdp;
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

			this.adminService.updateResource("Administrateur", this.administrateur.id, this.administrateur)
				.subscribe(data => this.updateAuthAdmin(), error => this.toastError(error.error));
		}
	}

	updateAuthAdmin() {
		sessionStorage.setItem(this.SESSION_ATTRIBUTE_NAME, this.administrateur.nom);
        if(this.ancienMotDePasse !== this.administrateur.mdp) {
			this.authenticationService.password = this.administrateur.mdp;
        }
        this.adminService.setName(this.administrateur.nom);
		this.administrateur = new User();
		this.gotoToProfil();
	}

	onCancel() {
		this.gotoToProfil();
	}

	gotoToProfil() {
		this.router.navigate(['admin/profil']);
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
