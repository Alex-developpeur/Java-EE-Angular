import { Coordonnees } from "./coordonnees";
import { Entreprise } from "./entreprise";
import { Devis } from "./documents/devis";

export class Personne {

	id: number;
    civilite: string;
    nom: string;
    prenom: string;

    coordonnees: Coordonnees;
    entreprise: Entreprise;
    devisList: Devis[];
    
}
