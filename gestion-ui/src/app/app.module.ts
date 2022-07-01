import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { CommonModule } from '@angular/common';

import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';
registerLocaleData(localeFr, 'fr-FR');

import { ShareModule } from './app/modules/share.module';
import { CoreModule } from './core/core.module';

import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';

import { AppComponent } from './app/app.component';
import { HeaderComponent } from './app/header/header.component';
import { FooterComponent } from './app/footer/footer.component';
import { NotFoundComponent } from './app/erreurs/not-found.component';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './login/login.component';
import { NouveauProfilComponent } from './login/nouveau-profil/nouveau-profil.component';

import { SideAdminComponent } from './admin/side-admin/side-admin.component';
import { SideUserComponent } from './user/side-user/side-user.component';

import { TestComponent } from './test/test.component';
import { ModalComponent } from './test/modal/modal.component';

@NgModule({
    declarations: [
        AppComponent,
        HeaderComponent,
        FooterComponent,
        NotFoundComponent,
        IndexComponent,
        LoginComponent,
        NouveauProfilComponent,
        
        SideAdminComponent,
        SideUserComponent,

        TestComponent,

        ModalComponent
    ],
    imports: [
        BrowserModule,
        CommonModule,
        AppRoutingModule,
        ShareModule,
        CoreModule,
        MatSidenavModule,
        MatListModule,
        BrowserAnimationsModule,
        MatToolbarModule
    ],
    providers: [
        { provide: LOCALE_ID, useValue: "fr-FR" }
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
