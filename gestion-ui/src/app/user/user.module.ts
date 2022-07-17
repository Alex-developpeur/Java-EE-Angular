import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ShareModule } from '../app/modules/share.module';
import { UserRoutingModule } from './user-routing.module';

import { UserComponent } from './user.component';

import { ProfilUserComponent } from './profil/profil-user/profil-user.component';
import { ModifierProfilUserComponent } from './profil/modifier-profil-user/modifier-profil-user.component';
import { CreerEntrepriseComponent } from './profil/entreprise/creer-entreprise/creer-entreprise.component';
import { DetailsEntrepriseComponent } from './profil/entreprise/details-entreprise/details-entreprise.component';
import { ModifierEntrepriseComponent } from './profil/entreprise/modifier-entreprise/modifier-entreprise.component';

import { ModalUserComponent } from './modal-user/modal-user.component';

import { ListeProComponent } from './clients/professionels/liste-pro/liste-pro.component';
import { DetailsProComponent } from './clients/professionels/details-pro/details-pro.component';
import { CreerProComponent } from './clients/professionels/creer-pro/creer-pro.component';
import { ModifierProComponent } from './clients/professionels/modifier-pro/modifier-pro.component';

import { ListeParticuliersComponent } from './clients/particuliers/liste-particuliers/liste-particuliers.component';
import { CreerParticulierComponent } from './clients/particuliers/creer-particulier/creer-particulier.component';
import { DetailsParticulierComponent } from './clients/particuliers/details-particulier/details-particulier.component';
import { ModifierParticulierComponent } from './clients/particuliers/modifier-particulier/modifier-particulier.component';

import { ListeDevisComponent } from './clients/documents/liste-devis/liste-devis.component';
import { CreerDevisComponent } from './clients/documents/creer-devis/creer-devis.component';

@NgModule({
	declarations: [
		UserComponent,
		ProfilUserComponent,
		ModifierProfilUserComponent,
		CreerEntrepriseComponent,
		DetailsEntrepriseComponent,
		ModifierEntrepriseComponent,
		ModalUserComponent,
		
		ListeProComponent,
		DetailsProComponent,
		CreerProComponent,
		ModifierProComponent,

		ListeParticuliersComponent,
		CreerParticulierComponent,
		DetailsParticulierComponent,
		ModifierParticulierComponent,

		ListeDevisComponent,
		CreerDevisComponent
	],
	imports: [
		CommonModule,
		ShareModule,
		UserRoutingModule
	]
})
export class UserModule { }
