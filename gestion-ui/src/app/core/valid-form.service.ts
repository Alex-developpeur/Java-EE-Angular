import { Injectable } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';

@Injectable({
	providedIn: 'root'
})
export class ValidFormService {

	nomForm;
	emailForm;
	mdpForm;
	mdpConfirmForm;

	constructor(private formBuilder: FormBuilder) { }

	// Utilisateur :
	getFormGroupUser() {
		return this.formBuilder.group({
			nomForm: ['', [Validators.minLength(3), Validators.maxLength(50)]],
			emailForm: ['', [Validators.required, Validators.pattern('^\\S+@[\\w-.]+\.\\w+$'), Validators.maxLength(50)]],
			mdpForm: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(100)]],
			mdpConfirmForm: ['']
		}, {validator: this.checkPassword('mdpForm', 'mdpConfirmForm')});
	}

	checkPassword(controlName: string, matchingControlName: string) {
	    return (formGroup: FormGroup) => {
	        const control = formGroup.controls[controlName];
	        const matchingControl = formGroup.controls[matchingControlName];
	        if(control.value === matchingControl.value) {
	        	matchingControl.setErrors(null);
	        } else {
	        	matchingControl.setErrors({ notSame: true });
	        }
	    }
	}

	validNom(formGroup: FormGroup) {
		const nom = formGroup.controls.nomForm;
		if(nom.hasError('minlength') || nom.hasError('maxlength')) {
			return "Le nom doit comprendre de 3 à 50 caractères.";
		}		
	}

	validEmail(formGroup: FormGroup) {
		const email = formGroup.controls.emailForm;
		if (email.hasError('required')) {
			return "L'adresse email ne peut être vide.";
		} else if(email.hasError('pattern')) {
			return "L'adresse email doit être valide.";
		} else if(email.hasError('maxlength')) {
			return "L'adresse email doit comprendre de 6 à 50 caractères.";
		}
	}

	validMdp(formGroup: FormGroup) {
		const mdp = formGroup.controls.mdpForm;
		if (mdp.hasError('required')) {
			return "Le mot de passe ne peut être vide.";
		} else if(mdp.hasError('minlength') || mdp.hasError('maxlength')) {
			return "Le mot de passe doit comprendre de 6 à 50 caractères.";
		}
	}

	validMdpConfirm() {
		return "Le mot de passe et la confirmation doivent être identique.";
	}

	// Entreprise :
	getFormGroupEntreprise() {
		return this.formBuilder.group({
			raisonSocialeForm: ['', [Validators.required, Validators.maxLength(50)]],
			formeJuridiqueForm: ['', Validators.required],
			natureForm: ['', Validators.required],
			numeroSiretForm: ['', Validators.pattern('(\\d{3}[ ]?){3}\\d{5}')],
			numeroTvaForm: ['', Validators.pattern('[A-Z]{2}([ ]?\\d{3}){3}')]
		});
	}

	validRaisonSociale(formGroup: FormGroup) {
		const raisonSociale = formGroup.controls.raisonSocialeForm;
		if (raisonSociale.hasError('required')) {
			return "La raison sociale ne peut être vide.";
		} else if(raisonSociale.hasError('maxlength')) {
			return "La raison sociale ne doit pas comprendre plus de 50 caractères.";
		}
	}

	validFormeJuridique(formGroup: FormGroup) {
		const formeJuridique = formGroup.controls.formeJuridiqueForm;
		if (formeJuridique.hasError('required')) {
			return "La forme juridique ne peut être vide.";
		}
	}

	validNature(formGroup: FormGroup) {
		const nature = formGroup.controls.natureForm;
		if (nature.hasError('required')) {
			return "La nature ne peut être vide.";
		}
	}

	validNumeroSiret(formGroup: FormGroup) {
		const numeroSiret = formGroup.controls.numeroSiretForm;
		if (numeroSiret.hasError('required')) {
			return "Le numéro SIRET ne peut être vide.";
		} else if(numeroSiret.hasError('pattern')) {
			return "Le numéro SIRET doit être composé de 14 chiffres (ex: 123 456 789 01234).";
		}
	}

	validNumeroTva(formGroup: FormGroup) {
		const numeroTva = formGroup.controls.numeroTvaForm;
		if(numeroTva.hasError('pattern')) {
			return "Le numéro de TVA doit être composé de 2 lettres majuscules et 9 chiffres (ex: FR 123 456 789).";
		}
	}

	// Personne :
	getFormGroupPersonne() {
		return this.formBuilder.group({
			civiliteForm: ['', Validators.required],
			nomForm: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]],
			prenomForm: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]]
		});
	}

	validCivilite(formGroup: FormGroup) {
		const civilite = formGroup.controls.civiliteForm;
		if (civilite.hasError('required')) {
			return "La civilité ne peut être vide.";
		}
	}

	validNomPersonne(formGroup: FormGroup) {
		const nom = formGroup.controls.nomForm;
		if (nom.hasError('required')) {
			return "Le nom ne peut être vide.";
		} else if(nom.hasError('minlength') || nom.hasError('maxlength')) {
			return "Le nom doit comprendre de 3 à 50 caractères.";
		}
	}

	validPrenom(formGroup: FormGroup) {
		const prenom = formGroup.controls.prenomForm;
		if (prenom.hasError('required')) {
			return "Le prénom ne peut être vide.";
		} else if(prenom.hasError('minlength') || prenom.hasError('maxlength')) {
			return "Le prénom doit comprendre de 3 à 50 caractères.";
		}
	}

	// Coordonnees :
	getFormGroupCoordonnees(formGroup: FormGroup) {
		formGroup.addControl('rueForm', new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]));
		formGroup.addControl('codePostalForm', new FormControl('', [Validators.required, Validators.pattern('\\d{2}[ ]?\\d{3}')]));
		formGroup.addControl('villeForm', new FormControl('', [Validators.required, Validators.minLength(1), Validators.maxLength(50)]));
		formGroup.addControl('paysForm', new FormControl('', [Validators.minLength(3), Validators.maxLength(50)]));
		formGroup.addControl('emailForm', new FormControl('', [Validators.pattern('^\\S+@[\\w-.]+\.\\w+$'), Validators.maxLength(50)]));
		formGroup.addControl('telForm', new FormControl('', Validators.pattern('(\\d{2}[ ]?){4}\\d{2}')));
		formGroup.addControl('faxForm', new FormControl('', Validators.pattern('(\\d{2}[ ]?){4}\\d{2}')));
		return formGroup;
	}

	validRue(formGroup: FormGroup) {
		const rue = formGroup.controls.rueForm;
		if (rue.hasError('required')) {
			return "La rue ne peut être vide.";
		} else if(rue.hasError('minlength') || rue.hasError('maxlength')) {
			return "La rue doit comprendre de 3 à 50 caractères."
		}
	}

	validCodePostal(formGroup: FormGroup) {
		const codePostal = formGroup.controls.codePostalForm;
		if (codePostal.hasError('required')) {
			return "Le code postal ne peut être vide.";
		} else if(codePostal.hasError('pattern')) {
			return "Le code postal doit être composé de 5 chiffres."
		}
	}

	validVille(formGroup: FormGroup) {
		const ville = formGroup.controls.villeForm;
		if (ville.hasError('required')) {
			return "La ville ne peut être vide.";
		} else if(ville.hasError('minlength') || ville.hasError('maxlength')) {
			return "La ville doit comprendre de 1 à 50 caractères."
		}
	}

	validPays(formGroup: FormGroup) {
		const pays = formGroup.controls.paysForm;
		if(pays.hasError('minlength') || pays.hasError('maxlength')) {
			return "Le pays doit comprendre de 3 à 50 caractères."
		}
	}

	validTel(formGroup: FormGroup) {
		const tel = formGroup.controls.telForm;
		if(tel.hasError('pattern')) {
			return "Le téléphone doit être composé de 10 chiffres (ex: 12 34 56 78 90)."
		}
	}

	validFax(formGroup: FormGroup) {
		const fax = formGroup.controls.faxForm;
		if(fax.hasError('pattern')) {
			return "Le fax doit être composé de 10 chiffres (ex: 12 34 56 78 90)."
		}
	}

	// Ligne de devis et facture :
	getFormGroupLigneDocuments() {
		return this.formBuilder.group({
            designation: ['', Validators.required],
            quantite: [0.00, [Validators.required, Validators.pattern('((\\d+[.,]\\d{2})|(\\d+))')]],
            prixUnitaire: [0.00, [Validators.required, Validators.pattern('((\\d+[.,]\\d{2})|(\\d+))')]]
		});
	}

	validDesignation(formGroup: FormGroup) {
		const designation = formGroup.controls.designation;
		if(designation.hasError('required')) {
			return "La désignation ne peut être vide."
		}
	}

	validQuantite(formGroup: FormGroup) {
		const quantite = formGroup.controls.quantite;
		if(quantite.hasError('required')) {
			return "La quantité ne peut être vide."
		} else if(quantite.hasError('pattern')) {
			return "La quantité doit être un nombre avec 2 décimals uniquement (ex: 12,34)."
		}
	}

	validPrixUnitaire(formGroup: FormGroup) {
		const prixUnitaire = formGroup.controls.prixUnitaire;
		if(prixUnitaire.hasError('required')) {
			return "La prix unitaire ne peut être vide."
		} else if(prixUnitaire.hasError('pattern')) {
			return "La prix unitaire doit être un nombre avec 2 décimals uniquement (ex: 12,34)."
		}
	}

}
