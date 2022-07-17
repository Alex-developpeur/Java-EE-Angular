import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app/app.component';
import { NotFoundComponent } from './app/erreurs/not-found.component';
import { LoginComponent } from './login/login.component';
import { NouveauProfilComponent } from './login/nouveau-profil/nouveau-profil.component';
import { IndexComponent } from './index/index.component';

import { TestComponent } from './test/test.component';

const routes: Routes = [
	{
		path: '',
		children: [
			{path: '', component: IndexComponent},
			{path: 'connexion', component: LoginComponent},
			{path: 'deconnexion', component: LoginComponent},
			{path: 'nouvel-utilisateur', component: NouveauProfilComponent},

			{path: 'test', component: TestComponent},
			{path: 'admin', loadChildren: () => import(`./admin/admin.module`).then(m => m.AdminModule)},
			{path: 'mon-compte', loadChildren: () => import(`./user/user.module`).then(m => m.UserModule)}
//			{path: 'user-test', component: UserComponent},
//			{path: 'admin-test', component: AdminComponent}
		]
	},
//	{ path: 'erreur/404', component: NotFoundComponent },
//    { path: '**', redirectTo: 'erreur/404' }
];

@NgModule({
	imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
	exports: [RouterModule]
})
export class AppRoutingModule { }
