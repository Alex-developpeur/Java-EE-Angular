import { Entreprise } from "../entreprise";
import { Personne } from "../personne";
import { LigneDevis } from "./ligne-devis";

export class Devis {

	id: number;
    numeroDevis: string;
    dateEmission: string;
    modeDeReglement: string;
    tva: number;
	acompte: number;
	etatDevis: string;

    entreprise: Entreprise;
    personne: Personne;
    ligneDevisList: LigneDevis[];

}
