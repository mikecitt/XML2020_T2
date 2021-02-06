import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { PodnosenjeZahtevaComponent } from './components/podnosenje-zahteva/podnosenje-zahteva.component';
import { PostRegistrationComponent } from './components/post-registration/post-registration.component';
import { ZahteviListComponent } from './components/zahtevi-list/zahtevi-list.component';
import { TokenInterceptorService } from './services/token-interceptor.service';
import { ObavestenjeDodajComponent } from './components/obavestenje-dodaj/obavestenje-dodaj.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ResenjaListComponent } from './components/resenja-list/resenja-list.component';
import { ZalbaListComponent } from './components/zalba-list/zalba-list.component';
import { ObrazloziZalbuComponent } from './components/obrazlozi-zalbu/obrazlozi-zalbu.component';
import { XhtmlPrikazComponent } from './components/xhtml-prikaz/xhtml-prikaz.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    PodnosenjeZahtevaComponent,
    PostRegistrationComponent,
    ZahteviListComponent,
    ObavestenjeDodajComponent,
    DashboardComponent,
    ResenjaListComponent,
    ZalbaListComponent,
    ObrazloziZalbuComponent,
    XhtmlPrikazComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [
  {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptorService,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
