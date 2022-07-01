import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppRoutingGuard } from '../core';
import { UserComponent } from './user.component';
import { DashboardUserComponent } from './dashboard-user/dashboard-user.component';

import { ProfilUserComponent } from './profil/profil-user/profil-user.component';
import { ModifierProfilUserComponent } from './profil/modifier-profil-user/modifier-profil-user.component';
import { CreerEntrepriseComponent } from './profil/entreprise/creer-entreprise/creer-entreprise.component';
import { ModifierEntrepriseComponent } from './profil/entreprise/modifier-entreprise/modifier-entreprise.component';

import { ListeProComponent } from './clients/professionels/liste-pro/liste-pro.component';
import { DetailsProComponent } from './clients/professionels/details-pro/details-pro.component';
import { CreerProComponent } from './clients/professionels/creer-pro/creer-pro.component';
import { ModifierProComponent } from './clients/professionels/modifier-pro/modifier-pro.component';

import { ListeParticuliersComponent } from './clients/particuliers/liste-particuliers/liste-particuliers.component';
import { CreerParticulierComponent } from './clients/particuliers/creer-particulier/creer-particulier.component';
import { DetailsParticulierComponent } from './clients/particuliers/details-particulier/details-particulier.component';
import { ModifierParticulierComponent } from './clients/particuliers/modifier-particulier/modifier-particulier.component';

import { CreerDevisComponent } from './clients/documents/creer-devis/creer-devis.component';

const routes: Routes = [
	{
		path: '', component: UserComponent,
		canLoad: [AppRoutingGuard],
		canActivate: [AppRoutingGuard],
		data: {
			roles: [ 'USER' ]
		},
		children: [
			{path: 'tableau-de-bord', component: DashboardUserComponent},
			{
				path: 'profil',
				children: [
					{path: '', component: ProfilUserComponent},
					{path: 'modifier/:id', component: ModifierProfilUserComponent},
					{
						path: 'entreprise',
						children: [
							{path: 'creer', component: CreerEntrepriseComponent},
							{path: 'modifier/:id', component: ModifierEntrepriseComponent}
						]
					}
				]
			},
			{
				path: 'clients',
				children: [
//					{path: '', component: ProfilUserComponent},
//					{path: 'modifier/:id', component: ModifierProfilUserComponent},
					{
						path: 'particuliers',
						children: [
							{path: '', component: ListeParticuliersComponent},
							{path: 'creer', component: CreerParticulierComponent},
							{path: 'details/:id', component: DetailsParticulierComponent},
							{path: 'modifier/:id', component: ModifierParticulierComponent}
						]
					},
					{
						path: 'professionnels',
						children: [
							{path: '', component: ListeProComponent},
							{path: 'creer', component: CreerProComponent},
							{path: 'details/:id', component: DetailsProComponent},
							{path: 'modifier/:id', component: ModifierProComponent}
						]
					},
					{path: 'creer-devis', component: CreerDevisComponent}
				]
			}

		]
	}
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule]
})

export class UserRoutingModule { }
