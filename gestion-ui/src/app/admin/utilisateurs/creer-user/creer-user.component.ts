import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from '../../../core';
import { ValidFormService } from '../../../core';
import { User } from "../../../model/user";

@Component({
	selector: 'app-creer-user',
	templateUrl: './creer-user.component.html',
	styleUrls: ['./creer-user.component.scss']
})
export class CreerUserComponent implements OnInit {

	utilisateur: User;
	userForm: FormGroup;

	constructor(private router: Router,
				private _snackBar: MatSnackBar,
				private adminService: AdminService,
				private validFormService: ValidFormService) { }

	ngOnInit() {
		this.utilisateur = new User();
		this.userForm = this.validFormService.getFormGroupUser();
	}

	onSubmit() {
		if(!this.userForm.invalid) {
			if(this.userForm.get('nomForm').value !== "")
				this.utilisateur.nom = this.userForm.get('nomForm').value;
			this.utilisateur.email = this.userForm.get('emailForm').value;
			this.utilisateur.mdp = this.userForm.get('mdpForm').value;

			this.adminService.createResource("Utilisateur", this.utilisateur)
				.subscribe(data => this.gotoList(), error => this.toastError(error.error));
			//	.subscribe(data => this.gotoList(), error => console.log(error.error));
			this.utilisateur = new User();
		}
	}

	onCancel() {
		this.gotoList();
	}

	gotoList() {
		this.router.navigate(['admin/utilisateurs']);
	}

	validFormNom() {
		return this.validFormService.validNom(this.userForm);
	}	

	validFormEmail() {
		return this.validFormService.validEmail(this.userForm);
	}

	validFormMDP() {
		return this.validFormService.validMdp(this.userForm);
	}

	validFormMDPConfirm() {
		return this.validFormService.validMdpConfirm();
	}

	toastError(error) {
		console.log(error);
		if(error.status === 500) {
	        this._snackBar.open(error.message, 'x', {
	            duration: 5000,
	            panelClass: 'error-snackbar'
	        });
	    }
    }

}