import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { User } from '../model/user';
import { Personne } from '../model/personne';
import { Entreprise } from '../model/entreprise';

@Injectable({
	providedIn: 'root'
})
export class UserDataHolderService {

    SESSION_ATTRIBUTE_NAME = 'authenticatedUser';
    private nameSource = new BehaviorSubject<string>(sessionStorage.getItem(this.SESSION_ATTRIBUTE_NAME));
    currentName = this.nameSource.asObservable();

	private user: User;
    private personne: Personne;
    private professionnel: Entreprise;

	constructor() { }

	setName(name: string) {
        this.nameSource.next(name);
    }

    setUser(utilisateur: User) {
        this.user = utilisateur;
    }

    getUser(): User {
        return this.user;
    }

    setParticulier(personne: Personne) {
        this.professionnel = null;
        this.personne = personne;
    }

    getParticulier(): Personne {
        return this.personne;
    }

    setPro(professionnel: Entreprise) {
        this.personne = null;
        this.professionnel = professionnel;
    }

    getPro(): Entreprise {
        return this.professionnel;
    }

}
