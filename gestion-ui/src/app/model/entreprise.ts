import { User } from "./user";
import { Coordonnees } from "./coordonnees";
import { Personne } from "./personne";
import { Devis } from "./documents/devis";

export class Entreprise {

	id: number;
    raisonSociale: string;
    formeJuridique: string;
    nature: string;
    numeroSiret: string;
    numeroTva: string;

    profil: User;
    entreprise: Entreprise;
    entrepriseList: Entreprise[];
	coordonneesList: Coordonnees[];
    personneList: Personne[];
    devisList: Devis[];
    	
}
