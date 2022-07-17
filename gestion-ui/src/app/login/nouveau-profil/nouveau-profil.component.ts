import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserCrudService } from '../../core';
import { ValidFormService } from '../../core';
import { User } from "../../model/user";

@Component({
  selector: 'app-nouveau-profil',
  templateUrl: './nouveau-profil.component.html',
  styleUrls: ['./nouveau-profil.component.scss']
})
export class NouveauProfilComponent implements OnInit {

	utilisateur: User;
	userForm: FormGroup;

	constructor(private router: Router,
				private authenticationService: AuthenticationService,
				private userCrud: UserCrudService,
				private validFormService: ValidFormService,
				private _snackBar: MatSnackBar) { }

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

			this.userCrud.createResource("nouvel-utilisateur", this.utilisateur)
				.subscribe(data => this.handleLogin(), error => this.toastError(error.error));
		}
	}

    handleLogin() {
        this.authenticationService.authenticationService(this.utilisateur.email, this.utilisateur.mdp)
        	.subscribe((result)=> {
	            this.router.navigate(['/mon-compte/tableau-de-bord']);
	        }, (error) => {
	            console.log(error);
	        });      
    }

	onCancel() {
		this.gotoToIndex();
	}

	gotoToIndex() {
		this.router.navigate(['/']);
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
		if(error.status === 500) {
	        this._snackBar.open(error.message, 'x', {
	            duration: 5000,
	            panelClass: 'warning-snackbar'
	        });
	    }
    }

}
