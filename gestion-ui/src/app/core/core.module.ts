import { NgModule, Optional, SkipSelf } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpInterceptorService } from './http-interceptor.service';
import { AppRoutingGuard } from './app-routing.guard';
import { AuthenticationService } from './auth.service';
import { AdminService } from './admin.service';

import { UserCrudService } from './user-crud.service';
import { UserDataHolderService } from './user-data-holder.service';

import { ValidFormService } from './valid-form.service';

@NgModule({
    imports: [
        HttpClientModule
    ],
	providers: [
		AppRoutingGuard,
		AuthenticationService,
		AdminService,
		UserCrudService,
		UserDataHolderService,
		ValidFormService,
        {
            provide: HTTP_INTERCEPTORS,
            useClass: HttpInterceptorService,
            multi: true
        }
	]
})

export class CoreModule {
	constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
		if (parentModule) {
			throw new Error(
				'CoreModule est déjà chargé. Import dans AppModule seulement.'
			);
		}
	}
}
