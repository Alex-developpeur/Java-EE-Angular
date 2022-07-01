import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ShareModule } from '../app/modules/share.module';
import { AdminRoutingModule } from './admin-routing.module';

import { AdminComponent } from './admin.component';
import { ProfilAdminComponent } from './profil/profil-admin/profil-admin.component';
import { ModifierProfilAdminComponent } from './profil/modifier-profil-admin/modifier-profil-admin.component';

import { ListeGroupeComponent } from './groupes/liste-groupe/liste-groupe.component';
import { DetailsGroupeComponent } from './groupes/details-groupe/details-groupe.component';
import { CreerGroupeComponent } from './groupes/creer-groupe/creer-groupe.component';
import { ModifierGroupeComponent } from './groupes/modifier-groupe/modifier-groupe.component';

import { ListeAdminComponent } from './administrateurs/liste-admin/liste-admin.component';
import { DetailsAdminComponent } from './administrateurs/details-admin/details-admin.component';
import { CreerAdminComponent } from './administrateurs/creer-admin/creer-admin.component';
import { ModifierAdminComponent } from './administrateurs/modifier-admin/modifier-admin.component';

import { ListeUserComponent } from './utilisateurs/liste-user/liste-user.component';
import { DetailsUserComponent } from './utilisateurs/details-user/details-user.component';
import { CreerUserComponent } from './utilisateurs/creer-user/creer-user.component';
import { ModifierUserComponent } from './utilisateurs/modifier-user/modifier-user.component';

import { DashboardAdminComponent } from './dashboard-admin/dashboard-admin.component';
import { ModalSupprimerComponent } from './modal-supprimer/modal-supprimer.component';

@NgModule({
	declarations: [
    	DashboardAdminComponent,
    	AdminComponent,
        ModalSupprimerComponent,
        ProfilAdminComponent,
        ModifierProfilAdminComponent,

        ListeGroupeComponent,
        DetailsGroupeComponent,
        CreerGroupeComponent,
        ModifierGroupeComponent,

        ListeAdminComponent,
        CreerAdminComponent,
        DetailsAdminComponent,
        ModifierAdminComponent,

        ListeUserComponent,
        DetailsUserComponent,
        CreerUserComponent,
        ModifierUserComponent
	],
	imports: [
		CommonModule,
		ShareModule,
		AdminRoutingModule
	]
})
export class AdminModule { }
