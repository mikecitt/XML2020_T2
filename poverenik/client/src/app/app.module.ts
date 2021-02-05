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
import { OverviewComponent } from './modules/complaint-silence/overview/overview.component';
import { ComplaintsComponent } from './modules/home/complaints/complaints.component';
import { XmlInterceptor } from './interceptors/xml.interceptor';

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
    OverviewComponent,
    ComplaintsComponent,
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
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
