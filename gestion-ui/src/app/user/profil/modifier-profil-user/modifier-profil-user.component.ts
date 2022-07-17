import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthenticationService } from '../../../core';
import { UserCrudService } from '../../../core';
import { UserDataHolderService } from '../../../core';
import { ValidFormService } from '../../../core';
import { User } from "../../../model/user";

@Component({
  selector: 'app-modifier-profil-user',
  templateUrl: './modifier-profil-user.component.html',
  styleUrls: ['./modifier-profil-user.component.scss']
})
export class ModifierProfilUserComponent implements OnInit {

    SESSION_ATTRIBUTE_NAME = 'authenticatedUser';
    ancienMotDePasse: string;
	utilisateur: User;
	adminForm: FormGroup;

	constructor(private router: Router,
				private authenticationService: AuthenticationService,
				private userCrud: UserCrudService,
				private validFormService: ValidFormService,
				private dataHolder: UserDataHolderService,
				private _snackBar: MatSnackBar) { }

	ngOnInit() {
		this.utilisateur = new User();
		this.adminForm = this.validFormService.getFormGroupUser();

		this.userCrud.getUserInfos()
			.subscribe(data => {
				this.utilisateur = data;
				this.ancienMotDePasse = this.utilisateur.mdp;
				this.adminForm.setValue({
					nomForm: this.utilisateur.nom,
					emailForm: this.utilisateur.email,
					mdpForm: this.utilisateur.mdp,
					mdpConfirmForm: this.utilisateur.mdp
				});
			}, error => console.log(error));
	}

	onSubmit() {
		if(!this.adminForm.invalid) {
			if(this.adminForm.get('nomForm').value !== "")
				this.utilisateur.nom = this.adminForm.get('nomForm').value;
			else
				this.utilisateur.nom = null;
			this.dataHolder.getUser().nom = this.adminForm.get('nomForm').value;
			this.dataHolder.getUser().email = this.adminForm.get('emailForm').value;
			this.utilisateur.email = this.adminForm.get('emailForm').value;
			this.utilisateur.mdp = this.adminForm.get('mdpForm').value;

			this.userCrud.updateResource("Utilisateur", this.utilisateur.id, this.utilisateur)
				.subscribe(data => this.updateAuthAdmin(), error => this.toastError(error.error));
		}
	}

	updateAuthAdmin() {
		sessionStorage.setItem(this.SESSION_ATTRIBUTE_NAME, this.utilisateur.nom);
        if(this.ancienMotDePasse !== this.utilisateur.mdp) {
			this.authenticationService.password = this.utilisateur.mdp;
        }
        this.dataHolder.setName(this.utilisateur.nom);
		this.utilisateur = new User();
		this.gotoToProfil();
	}

	onCancel() {
		this.gotoToProfil();
	}

	gotoToProfil() {
		this.router.navigate(['mon-compte/profil']);
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
