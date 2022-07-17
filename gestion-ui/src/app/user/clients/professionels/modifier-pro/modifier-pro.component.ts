import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserCrudService } from '../../../../core';
import { ValidFormService } from '../../../../core';
import { Entreprise } from "../../../../model/entreprise";
import { Coordonnees } from "../../../../model/coordonnees";

@Component({
	selector: 'app-modifier-pro',
	templateUrl: './modifier-pro.component.html',
	styleUrls: ['./modifier-pro.component.scss']
})
export class ModifierProComponent implements OnInit {

	id: number;
    entreprise: Entreprise;
    coordonnees: Coordonnees;
    entrepriseForm: FormGroup;

    constructor(private route: ActivatedRoute,
    			private router: Router,
                private _snackBar: MatSnackBar,
                private userCrud: UserCrudService,
                private validFormService: ValidFormService) { }

    ngOnInit() {
        this.entreprise = new Entreprise();
        this.coordonnees = new Coordonnees();

        this.entrepriseForm = this.validFormService.getFormGroupEntreprise();
        this.entrepriseForm = this.validFormService.getFormGroupCoordonnees(this.entrepriseForm);
        this.id = this.route.snapshot.params['id'];

   		this.userCrud.getResource("Entreprise", this.id)
			.subscribe(data => {
				this.entreprise = data;
				this.coordonnees = data.coordonneesList[0];
				this.entrepriseForm.patchValue({
					raisonSocialeForm: this.entreprise.raisonSociale,
					formeJuridiqueForm: this.entreprise.formeJuridique,
					natureForm: this.entreprise.nature,
					numeroSiretForm: this.entreprise.numeroSiret,
					numeroTvaForm: this.entreprise.numeroTva,

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
        if(!this.entrepriseForm.invalid) {
            this.entreprise.raisonSociale = this.entrepriseForm.get('raisonSocialeForm').value;
            this.entreprise.formeJuridique = this.entrepriseForm.get('formeJuridiqueForm').value;
            this.entreprise.nature = this.entrepriseForm.get('natureForm').value;
            if(this.entrepriseForm.get('numeroSiretForm').value !== '')
	            this.entreprise.numeroSiret = this.entrepriseForm.get('numeroSiretForm').value;
            else
            	this.entreprise.numeroSiret = null;
	        if(this.entrepriseForm.get('numeroTvaForm').value !== '')
            	this.entreprise.numeroTva = this.entrepriseForm.get('numeroTvaForm').value;
            else
            	this.entreprise.numeroTva = null;

    	    this.coordonnees.rue = this.entrepriseForm.get('rueForm').value;
	        this.coordonnees.codePostal = this.entrepriseForm.get('codePostalForm').value;
            this.coordonnees.ville = this.entrepriseForm.get('villeForm').value;
            if(this.entrepriseForm.get('paysForm').value !== '')
            	this.coordonnees.pays = this.entrepriseForm.get('paysForm').value;
            else
            	this.coordonnees.pays = null;
            if(this.entrepriseForm.get('emailForm').value !== '')
        	    this.coordonnees.email = this.entrepriseForm.get('emailForm').value;
            else
            	this.coordonnees.email = null;
            if(this.entrepriseForm.get('telForm').value !== '')
    	        this.coordonnees.tel = this.entrepriseForm.get('telForm').value;
            else
            	this.coordonnees.tel = null;
            if(this.entrepriseForm.get('faxForm').value !== '')
	            this.coordonnees.fax = this.entrepriseForm.get('faxForm').value;
            else
            	this.coordonnees.fax = null;

			this.userCrud.updateResource("Entreprise", this.id, this.entreprise)
				.subscribe(data => {
						this.entreprise = new Entreprise();
						this.coordonnees = new Coordonnees();
						this.gotoDetailClient();
					}, error => this.toastError(error.error));
        }
    }

    onCancel() {
        this.gotoDetailClient();
    }

    gotoDetailClient() {
        this.router.navigate(['mon-compte/clients/professionnels/details', this.id]);
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
