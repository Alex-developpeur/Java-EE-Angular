import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from '../../../core';
import { ValidFormService } from '../../../core';
import { User } from "../../../model/user";

@Component({
	selector: 'app-modifier-user',
	templateUrl: './modifier-user.component.html',
	styleUrls: ['./modifier-user.component.scss']
})
export class ModifierUserComponent implements OnInit {

	id: number;
	utilisateur: User;
	userForm: FormGroup;

	constructor(private route: ActivatedRoute,
				private router: Router,
				private adminService: AdminService,
				private validFormService: ValidFormService,
				private _snackBar: MatSnackBar) { }

	ngOnInit() {
		this.utilisateur = new User();
		this.userForm = this.validFormService.getFormGroupUser();
		this.id = this.route.snapshot.params['id'];

		this.adminService.getResource("Utilisateur", this.id)
			.subscribe(data => {
				console.log(data);
				this.utilisateur = data;
				this.userForm.setValue({
					nomForm: this.utilisateur.nom,
					emailForm: this.utilisateur.email,
					mdpForm: this.utilisateur.mdp,
					mdpConfirmForm: this.utilisateur.mdp
				});
			}, error => console.log(error));
	}

	onSubmit() {
		if(!this.userForm.invalid) {
			if(this.userForm.get('nomForm').value !== "")
				this.utilisateur.nom = this.userForm.get('nomForm').value;
			this.utilisateur.email = this.userForm.get('emailForm').value;
			this.utilisateur.mdp = this.userForm.get('mdpForm').value;

			this.adminService.updateResource("Utilisateur", this.id, this.utilisateur)
				.subscribe(data => {this.utilisateur = new User(); this.gotoList();},
				error => this.toastError(error.error));
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
		console.log(error)
		if(error.status === 500) {
	        this._snackBar.open(error.message, 'x', {
	            duration: 5000,
	            panelClass: 'warning-snackbar'
	        });
	    }
    }

}
