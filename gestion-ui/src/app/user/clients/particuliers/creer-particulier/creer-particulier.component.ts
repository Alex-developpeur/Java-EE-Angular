import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserCrudService } from '../../../../core';
import { UserDataHolderService } from '../../../../core';
import { ValidFormService } from '../../../../core';
import { Personne } from "../../../../model/personne";
import { Coordonnees } from "../../../../model/coordonnees";

@Component({
	selector: 'app-creer-particulier',
	templateUrl: './creer-particulier.component.html',
	styleUrls: ['./creer-particulier.component.scss']
})
export class CreerParticulierComponent implements OnInit {

    personne: Personne;
    coordonnees: Coordonnees;
    personneForm: FormGroup;

    constructor(private router: Router,
                private _snackBar: MatSnackBar,
                private userCrud: UserCrudService,
                private dataHolder: UserDataHolderService,
                private validFormService: ValidFormService) { }

    ngOnInit() {
        this.personne = new Personne();
        this.coordonnees = new Coordonnees();

        this.personneForm = this.validFormService.getFormGroupPersonne();
        this.personneForm = this.validFormService.getFormGroupCoordonnees(this.personneForm);
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
            if(this.personneForm.get('emailForm').value !== '')
        	    this.coordonnees.email = this.personneForm.get('emailForm').value;
            if(this.personneForm.get('telForm').value !== '')
    	        this.coordonnees.tel = this.personneForm.get('telForm').value;
            if(this.personneForm.get('faxForm').value !== '')
	            this.coordonnees.fax = this.personneForm.get('faxForm').value;

//            this.personne.coordonneesList = [];
            this.personne.coordonnees = this.coordonnees;
            this.personne.entreprise = this.dataHolder.getUser().entrepriseList[0];

            this.userCrud.createResource("Personne", this.personne)
                .subscribe(data => {
                        this.personne = new Personne();
                        this.coordonnees = new Coordonnees();
                        this.gotoListClients();
                    }, error => this.toastError(error.error));
            //    .subscribe(data => this.gotoList(), error => console.log(error.error));
        }
    }

    onCancel() {
        this.gotoListClients();
    }

    gotoListClients() {
        this.router.navigate(['mon-compte/clients/particuliers']);
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
