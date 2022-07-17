import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppRoutingGuard } from '../core';

import { AdminComponent } from './admin.component';
import { DashboardAdminComponent } from './dashboard-admin/dashboard-admin.component';
import { ProfilAdminComponent } from './profil/profil-admin/profil-admin.component';
import { ModifierProfilAdminComponent } from './profil/modifier-profil-admin/modifier-profil-admin.component';

import { ListeGroupeComponent } from './groupes/liste-groupe/liste-groupe.component';
import { CreerGroupeComponent } from './groupes/creer-groupe/creer-groupe.component';
import { ModifierGroupeComponent } from './groupes/modifier-groupe/modifier-groupe.component';
import { DetailsGroupeComponent } from './groupes/details-groupe/details-groupe.component';

import { ListeAdminComponent } from './administrateurs/liste-admin/liste-admin.component';
import { DetailsAdminComponent } from './administrateurs/details-admin/details-admin.component';
import { CreerAdminComponent } from './administrateurs/creer-admin/creer-admin.component';
import { ModifierAdminComponent } from './administrateurs/modifier-admin/modifier-admin.component';

import { ListeUserComponent } from './utilisateurs/liste-user/liste-user.component';
import { DetailsUserComponent } from './utilisateurs/details-user/details-user.component';
import { CreerUserComponent } from './utilisateurs/creer-user/creer-user.component';
import { ModifierUserComponent } from './utilisateurs/modifier-user/modifier-user.component';

const routes: Routes = [
	{
		path: '', component: AdminComponent,
		canLoad: [AppRoutingGuard],
		canActivate: [AppRoutingGuard],
		data: {
			roles: [ 'ADMIN' ]
		},
		children: [
			{path: 'tableau-de-bord', component: DashboardAdminComponent},
			{
				path: 'profil',
				children: [
					{path: '', component: ProfilAdminComponent},
					{path: 'modifier/:id', component: ModifierProfilAdminComponent}
				]
			},
			{
				path: 'groupes',
				children: [
					{path: '', component: ListeGroupeComponent},
					{path: 'details/:id', component: DetailsGroupeComponent},
					{path: 'creer', component: CreerGroupeComponent},
					{path: 'modifier/:id', component: ModifierGroupeComponent}
				]
			},
			{
				path: 'administrateurs',
				children: [
					{path: '', component: ListeAdminComponent},
					{path: 'details/:id', component: DetailsAdminComponent},
					{path: 'creer', component: CreerAdminComponent},
					{path: 'modifier/:id', component: ModifierAdminComponent}
				]
			},
			{
				path: 'utilisateurs',
				children: [
					{path: '', component: ListeUserComponent},
					{path: 'details/:id', component: DetailsUserComponent},
					{path: 'creer', component: CreerUserComponent},
					{path: 'modifier/:id', component: ModifierUserComponent}
				]
			}
		]
	}	
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule]
})

export class AdminRoutingModule { }
