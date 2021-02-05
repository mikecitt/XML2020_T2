import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginGuard } from './guards/login-guard.guard';
import { HomePageComponent } from './modules/home/home-page/home-page.component';
import { LoginPageComponent } from './modules/login/login-page/login-page.component';
import { RegisterPageComponent } from './modules/register/register-page/register-page.component';

const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'login', component: LoginPageComponent, canActivate: [LoginGuard] },
  { path: 'register', component: RegisterPageComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
