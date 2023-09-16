import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { CustomerDashboardComponent } from './customer-dashboard/customer-dashboard.component';
import { CustomerRegistrationComponent } from './customer-registration/customer-registration.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { AdminCsrRegistrationComponent } from './admin-csr-registration/admin-csr-registration.component';
import { CsrCallFormComponent } from './csr-call-form/csr-call-form.component';

import { AdminGuard } from './z-Gaurds/admin.guard';
import { CsrGuard } from './z-Gaurds/csr.guard';
import { CustomerGuard } from './z-Gaurds/customer.guard';
import { CsrDashboardComponent } from './csr-dashboard/csr-dashboard.component';
import { AdminCsrUpdateComponent } from './admin-csr-update/admin-csr-update.component';
import { CustomerUpdateComponent } from './customer-update/customer-update.component';
import { AdminCallReportsComponent } from './admin-call-reports/admin-call-reports.component';
const routes:Routes =[
  {
    path:'home',
    component: HomeComponent
  },
  {
    path:'login',
    component: LoginComponent
  },
  {
    path:'customer/dashboard',
    component: CustomerDashboardComponent,
    canActivate:[CustomerGuard]
  },
  {
    path:'customer/register',
    component: CustomerRegistrationComponent
  },
  {
    path:'customer/update',
    component: CustomerUpdateComponent,
    canActivate:[CustomerGuard]
  },
  {
    path:'admin/dashboard',
    component:AdminDashboardComponent,
    canActivate:[AdminGuard]
  },
  {
    path:'admin/register',
    component:AdminCsrRegistrationComponent,
    canActivate:[AdminGuard]
  },
  {
    path:'admin/update',
    component:AdminCsrUpdateComponent,
    canActivate:[AdminGuard]
  },
  {
    path:'admin/callreports',
    component:AdminCallReportsComponent,
    canActivate:[AdminGuard]
  },
  {
    path:'csr/dashboard',
    component:CsrDashboardComponent,
    canActivate:[CsrGuard]
  },
  {
    path:'csr/add-call',
    component:CsrCallFormComponent,
    canActivate:[CsrGuard]
  },
  {
    path:"",
    redirectTo:"home",
    pathMatch: "full"
  }

]



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
