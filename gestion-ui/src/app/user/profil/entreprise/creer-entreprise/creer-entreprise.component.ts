import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserCrudService } from '../../../../core';
import { UserDataHolderService } from '../../../../core';
import { ValidFormService } from '../../../../core';
import { Entreprise } from "../../../../model/entreprise";
import { Coordonnees } from "../../../../model/coordonnees";

@Component({
	selector: 'app-creer-entreprise',
	templateUrl: './creer-entreprise.component.html',
	styleUrls: ['./creer-entreprise.component.scss']
})
export class CreerEntrepriseComponent implements OnInit {

    entreprise: Entreprise;
    coordonnees: Coordonnees;
    entrepriseForm: FormGroup;

    constructor(private router: Router,
                private _snackBar: MatSnackBar,
                private userCrud: UserCrudService,
                private dataHolder: UserDataHolderService,
                private validFormService: ValidFormService) { }

    ngOnInit() {
        this.entreprise = new Entreprise();
        this.coordonnees = new Coordonnees();

        this.entrepriseForm = this.validFormService.getFormGroupEntreprise();
        this.entrepriseForm = this.validFormService.getFormGroupCoordonnees(this.entrepriseForm);
    }

    onSubmit() {
        if(!this.entrepriseForm.invalid) {
            this.entreprise.raisonSociale = this.entrepriseForm.get('raisonSocialeForm').value;
            this.entreprise.formeJuridique = this.entrepriseForm.get('formeJuridiqueForm').value;
            this.entreprise.nature = this.entrepriseForm.get('natureForm').value;
            this.entreprise.numeroSiret = this.entrepriseForm.get('numeroSiretForm').value;
	        if(this.entrepriseForm.get('numeroTvaForm').value !== '')
            	this.entreprise.numeroTva = this.entrepriseForm.get('numeroTvaForm').value;

    	    this.coordonnees.rue = this.entrepriseForm.get('rueForm').value;
	        this.coordonnees.codePostal = this.entrepriseForm.get('codePostalForm').value;
            this.coordonnees.ville = this.entrepriseForm.get('villeForm').value;
            if(this.entrepriseForm.get('paysForm').value !== '')
            	this.coordonnees.pays = this.entrepriseForm.get('paysForm').value;
            if(this.entrepriseForm.get('emailForm').value !== '')
        	    this.coordonnees.email = this.entrepriseForm.get('emailForm').value;
            if(this.entrepriseForm.get('telForm').value !== '')
    	        this.coordonnees.tel = this.entrepriseForm.get('telForm').value;
            if(this.entrepriseForm.get('faxForm').value !== '')
	            this.coordonnees.fax = this.entrepriseForm.get('faxForm').value;

            this.entreprise.coordonneesList = [];
            this.entreprise.coordonneesList[0] = this.coordonnees;
            this.entreprise.profil = this.dataHolder.getUser();

            this.userCrud.createResource("Entreprise", this.entreprise)
                .subscribe(data => {
                        this.entreprise = new Entreprise();
                        this.gotoProfil();
                    }, error => this.toastError(error.error));
            //    .subscribe(data => this.gotoList(), error => console.log(error.error));
        }
    }

    onCancel() {
        this.gotoProfil();
    }

    gotoProfil() {
        this.router.navigate(['mon-compte/profil']);
    }

    validFormRaisonSociale() {
        return this.validFormService.validRaisonSociale(this.entrepriseForm);
    }    

    validFormFormeJuridique() {
        return this.validFormService.validFormeJuridique(this.entrepriseForm);
    }

    validFormNature() {
        return this.validFormService.validNature(this.entrepriseForm);
    }

    validFormNumeroSiret() {
        return this.validFormService.validNumeroSiret(this.entrepriseForm);
    }

    validFormNumeroTva() {
        return this.validFormService.validNumeroTva(this.entrepriseForm);
    }

    validFormRue() {
        return this.validFormService.validRue(this.entrepriseForm);
    }

    validFormCodePostal() {
        return this.validFormService.validCodePostal(this.entrepriseForm);
    }

    validFormVille() {
        return this.validFormService.validVille(this.entrepriseForm);
    }

    validFormPays() {
        return this.validFormService.validPays(this.entrepriseForm);
    }

    validFormEmail() {
        return this.validFormService.validEmail(this.entrepriseForm);
    }

    validFormTel() {
        return this.validFormService.validTel(this.entrepriseForm);
    }

    validFormFax() {
        return this.validFormService.validFax(this.entrepriseForm);
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
