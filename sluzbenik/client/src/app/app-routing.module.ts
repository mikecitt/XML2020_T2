import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { LoginComponent } from './components/login/login.component';
import { ObavestenjeDodajComponent } from './components/obavestenje-dodaj/obavestenje-dodaj.component';
import { PodnosenjeZahtevaComponent } from './components/podnosenje-zahteva/podnosenje-zahteva.component';
import { PostRegistrationComponent } from './components/post-registration/post-registration.component';
import { RegisterComponent } from './components/register/register.component';
import { ResenjaListComponent } from './components/resenja-list/resenja-list.component';
import { XhtmlPrikazComponent } from './components/xhtml-prikaz/xhtml-prikaz.component';
import { ZahteviListComponent } from './components/zahtevi-list/zahtevi-list.component';
import { ZalbaListComponent } from './components/zalba-list/zalba-list.component';
import { LoginGuard } from './guards/login.guard';
import { RolesGuard } from './guards/roles.guard';

const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'podnesizahtev',
    component: PodnosenjeZahtevaComponent,
    canActivate: [RolesGuard],
    data: { expectedRoles: 'ROLE_GRADJANIN' }
  },
  {
    path: 'postregister',
    component: PostRegistrationComponent
  },
  {
    path: 'svizahtevi',
    component: ZahteviListComponent,
    canActivate: [LoginGuard]
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [LoginGuard]
  },
  {
    path: 'svaresenja',
    component:ResenjaListComponent
  },
  {
    path: 'svezalbe',
    component:ZalbaListComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
