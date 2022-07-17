import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserCrudService } from '../../../../core';
import { ValidFormService } from '../../../../core';
import { UserDataHolderService } from '../../../../core';
import { Devis } from "../../../../model/documents/devis";
import { Personne } from "../../../../model/personne";
import { Entreprise } from "../../../../model/entreprise";
import { LigneDevis } from "../../../../model/documents/ligne-devis";

@Component({
	selector: 'app-creer-devis',
	templateUrl: './creer-devis.component.html',
	styleUrls: ['./creer-devis.component.scss']
})
export class CreerDevisComponent implements OnInit {

	particulier: Personne;
	professionnel: Entreprise;

    devis: Devis;
    ligneDevis: LigneDevis;
    ligneDevisList: LigneDevis[];
    ligneDevisForm: FormGroup;

    montantHT: number;
    totalHT: number;
    totalTVA: number;
    totalTTC: number;
    tvaDevisSelect: number;
    modeReglementSelect: string;
    acompte: string;

    displayedColumns: string[] = ['designation', 'quantite', 'prixUnitaire', 'montantHT', 'actions'];
    dataSource: MatTableDataSource<LigneDevis>;

    constructor(private router: Router,
		    	private _snackBar: MatSnackBar,
		    	private userCrud: UserCrudService,
		    	private dataHolder: UserDataHolderService,
		    	private validFormService: ValidFormService) { }

    ngOnInit() {
        this.devis = new Devis();
        this.ligneDevisList = [];
		this.particulier = this.dataHolder.getParticulier();
		this.professionnel = this.dataHolder.getPro();

        this.montantHT = 0;
        this.totalHT = 0;
        this.totalTVA = 0;
        this.totalTTC = 0;
        this.tvaDevisSelect = 20;
        this.modeReglementSelect = "carte bancaire";
        this.ligneDevisForm = this.validFormService.getFormGroupLigneDocuments();
        this.reloadData();
    }

    validerDevis() {
    	this.devis.modeDeReglement = this.modeReglementSelect
    	this.devis.tva = Number(this.tvaDevisSelect);
    	this.devis.acompte = (Number(this.acompte) ? Number(this.acompte) : 0.00);
    	this.devis.ligneDevisList = this.ligneDevisList;
    	this.devis.personne = this.particulier;
    	this.devis.entreprise = this.professionnel;
	    this.userCrud.createResource("Devis", this.devis)
	        .subscribe(data => {
	                this.devis = new Devis();
	                this.gotoDetailClient();
	            }, error => this.toastError(error.error));
	    //    .subscribe(data => this.gotoList(), error => console.log(error.error));

    }

    reloadData() {
        this.dataSource = new MatTableDataSource(this.ligneDevisList);
    }

    addLigneDevis() {
    	if(!this.ligneDevisForm.invalid) {
	        this.ligneDevis = new LigneDevis();
	        this.ligneDevis.designation = this.ligneDevisForm.get('designation').value;
	        this.ligneDevis.quantite = this.ligneDevisForm.get('quantite').value.replace(",", ".");
	        this.ligneDevis.prixUnitaire = this.ligneDevisForm.get('prixUnitaire').value.replace(",", ".");
	        this.ligneDevisList.push(this.ligneDevis);
	        this.montantHT = Math.ceil(this.ligneDevis.quantite * this.ligneDevis.prixUnitaire * 100) / 100;
	        this.reloadData();

	        this.totalHT = (this.totalHT + this.montantHT);
	        this.caculTotaux();
	    }
    }

    deleteLigneDevis(id) {
        if(this.ligneDevisList[id] !== undefined) {
            this.ligneDevis = this.ligneDevisList[id];
            this.totalHT = (this.totalHT - Math.ceil(this.ligneDevis.quantite * this.ligneDevis.prixUnitaire * 100) / 100);
            this.totalTVA = (Math.ceil(this.totalHT * 20) / 100);
            this.totalTTC = (this.totalHT + this.totalTVA);

            this.ligneDevisList.splice(id,1);
            this.reloadData();
        }
    }

    caculTotaux() {
        this.totalTVA = (Math.ceil(this.totalHT * this.tvaDevisSelect)/100);
        this.totalTTC = (this.totalHT + this.totalTVA);
    }

    acompteField(event) {
        console.log(event.target.value);
        this.acompte = event.target.value;
    }

    onCancel() {
        this.gotoDetailClient();
    }

    gotoDetailClient() {
    	if(this.particulier != null)
	        this.router.navigate(['mon-compte/clients/particuliers/details/' + this.particulier.id]);
	    else if(this.professionnel != null)
	    	this.router.navigate(['mon-compte/clients/professionnels/details/' + this.professionnel.id]);
    }


    validFormDesignation() {
    	return this.validFormService.validDesignation(this.ligneDevisForm);
    }

	validFormQuantite() {
    	return this.validFormService.validQuantite(this.ligneDevisForm);
    }

	validFormPrixUnitaire() {
    	return this.validFormService.validPrixUnitaire(this.ligneDevisForm);
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
