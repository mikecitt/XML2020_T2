import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { en_US } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginPageComponent } from './modules/login/login-page/login-page.component';
import { LoginFormComponent } from './modules/login/login-form/login-form.component';
import { AntdModule } from './antd-module';
import { RegisterPageComponent } from './modules/register/register-page/register-page.component';
import { RegisterFormComponent } from './modules/register/register-form/register-form.component';
import { NavigationBarComponent } from './core/navigation-bar/navigation-bar.component';
import { HomePageComponent } from './modules/home/home-page/home-page.component';
import { XmlInterceptor } from './interceptors/xml.interceptor';
import { HomeComplaintsDecisionComponent } from './modules/home/complaints-decision/complaints-decision.component';
import { TokenInterceptor } from './interceptors/token.interceptor';
import { ComplaintDecisionOverviewComponent } from './modules/complaints/complaint-decision-overview/complaint-decision-overview.component';
import { HomeComplaintsSilenceComponent } from './modules/home/complaints-silence/complaints-silence.component';
import { ComplaintSilenceOverviewComponent } from './modules/complaints/complaint-silence-overview/complaint-silence-overview.component';
import { NewComplaintComponent } from './modules/complaints/new-complaint/new-complaint.component';

registerLocaleData(en);

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    LoginFormComponent,
    RegisterPageComponent,
    RegisterFormComponent,
    NavigationBarComponent,
    HomePageComponent,
    ComplaintDecisionOverviewComponent,
    HomeComplaintsDecisionComponent,
    ComplaintDecisionOverviewComponent,
    HomeComplaintsSilenceComponent,
    ComplaintSilenceOverviewComponent,
    NewComplaintComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AntdModule,
    ReactiveFormsModule,
  ],
  providers: [
    { provide: NZ_I18N, useValue: en_US },
    { provide: HTTP_INTERCEPTORS, useClass: XmlInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
