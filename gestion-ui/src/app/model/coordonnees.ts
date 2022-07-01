import { Entreprise } from "./entreprise";

export class Coordonnees {

	id: number;
    rue: string;
    codePostal: string;
    ville: string;
    pays: string;
    email: string;
    tel: string;
    fax: string;

	entreprise: Entreprise;

}
