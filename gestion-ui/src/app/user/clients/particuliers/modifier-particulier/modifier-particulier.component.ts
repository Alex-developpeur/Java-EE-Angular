import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserCrudService } from '../../../../core';
import { ValidFormService } from '../../../../core';
import { Personne } from "../../../../model/personne";
import { Coordonnees } from "../../../../model/coordonnees";

@Component({
	selector: 'app-modifier-particulier',
	templateUrl: './modifier-particulier.component.html',
	styleUrls: ['./modifier-particulier.component.scss']
})
export class ModifierParticulierComponent implements OnInit {

	id: number;
    personne: Personne;
    coordonnees: Coordonnees;
    personneForm: FormGroup;

    constructor(private route: ActivatedRoute,
    			private router: Router,
                private _snackBar: MatSnackBar,
                private userCrud: UserCrudService,
                private validFormService: ValidFormService) { }

    ngOnInit() {
        this.personne = new Personne();
        this.coordonnees = new Coordonnees();

        this.personneForm = this.validFormService.getFormGroupPersonne();
        this.personneForm = this.validFormService.getFormGroupCoordonnees(this.personneForm);
        this.id = this.route.snapshot.params['id'];

   		this.userCrud.getResource("Personne", this.id)
			.subscribe(data => {
				this.personne = data;
				this.coordonnees = data.coordonnees;
				this.personneForm.patchValue({
					civiliteForm: this.personne.civilite,
					nomForm: this.personne.nom,
					prenomForm: this.personne.prenom,

					rueForm: this.coordonnees.rue,
					villeForm: this.coordonnees.ville,
					codePostalForm: this.coordonnees.codePostal,
					paysForm: this.coordonnees.pays,
					emailForm: this.coordonnees.email,
					telForm: this.coordonnees.tel,
					faxForm: this.coordonnees.fax
				});
			}, error => console.log(error));

    }

    onSubmit() {
        if(!this.personneForm.invalid) {
            this.personne.civilite = this.personneForm.get('civiliteForm').value;
            this.personne.nom = this.personneForm.get('nomForm').value;
            this.personne.prenom = this.personneForm.get('prenomForm').value;

    	    this.coordonnees.rue = this.personneForm.get('rueForm').value;
	        this.coordonnees.codePostal = this.personneForm.get('codePostalForm').value;
            this.coordonnees.ville = this.personneForm.get('villeForm').value;
            if(this.personneForm.get('paysForm').value !== '')
            	this.coordonnees.pays = this.personneForm.get('paysForm').value;
            else
            	this.coordonnees.pays = null;
            if(this.personneForm.get('emailForm').value !== '')
        	    this.coordonnees.email = this.personneForm.get('emailForm').value;
            else
            	this.coordonnees.email = null;
            if(this.personneForm.get('telForm').value !== '')
    	        this.coordonnees.tel = this.personneForm.get('telForm').value;
            else
            	this.coordonnees.tel = null;
            if(this.personneForm.get('faxForm').value !== '')
	            this.coordonnees.fax = this.personneForm.get('faxForm').value;
            else
            	this.coordonnees.fax = null;

			this.userCrud.updateResource("Personne", this.id, this.personne)
				.subscribe(data => {
						this.personne = new Personne();
						this.coordonnees = new Coordonnees();
						this.gotoDetailClient();
					}, error => this.toastError(error.error));
        }
    }

    onCancel() {
        this.gotoDetailClient();
    }

    gotoDetailClient() {
        this.router.navigate(['mon-compte/clients/particuliers/details', this.id]);
    }

    validFormCivilite() {
        return this.validFormService.validCivilite(this.personneForm);
    }    

    validFormNom() {
        return this.validFormService.validNomPersonne(this.personneForm);
    }

    validFormPrenom() {
        return this.validFormService.validPrenom(this.personneForm);
    }

    validFormRue() {
        return this.validFormService.validRue(this.personneForm);
    }

    validFormCodePostal() {
        return this.validFormService.validCodePostal(this.personneForm);
    }

    validFormVille() {
        return this.validFormService.validVille(this.personneForm);
    }

    validFormPays() {
        return this.validFormService.validPays(this.personneForm);
    }

    validFormEmail() {
        return this.validFormService.validEmail(this.personneForm);
    }

    validFormTel() {
        return this.validFormService.validTel(this.personneForm);
    }

    validFormFax() {
        return this.validFormService.validFax(this.personneForm);
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
