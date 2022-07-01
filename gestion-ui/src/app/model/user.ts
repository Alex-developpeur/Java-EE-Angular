import { Groupe } from "./groupe";
import { Entreprise } from "./entreprise";

export class User {

	id: number;
    nom: string;
    email: string;
    mdp: string;
	groupesList: Groupe[];
	entrepriseList: Entreprise[];

}
