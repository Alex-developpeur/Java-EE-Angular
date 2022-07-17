import { Groupe } from "./groupe";

export class Profil {

	id: number;
    nom: string;
    email: string;
    mdp: string;
	groupesList: Groupe[];
}
