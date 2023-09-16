import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

//Components
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { CustomerRegistrationComponent } from './customer-registration/customer-registration.component';
import { CustomerDashboardComponent } from './customer-dashboard/customer-dashboard.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { AdminCsrRegistrationComponent } from './admin-csr-registration/admin-csr-registration.component';
import { AdminCsrUpdateComponent } from './admin-csr-update/admin-csr-update.component';
import { CsrDashboardComponent } from './csr-dashboard/csr-dashboard.component';
import { CsrCallFormComponent } from './csr-call-form/csr-call-form.component';

//Mat Modules
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatIconModule} from '@angular/material/icon';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSelectModule } from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {MatCardModule} from '@angular/material/card';
import { LoginComponent } from './login/login.component';
import {MatDividerModule} from '@angular/material/divider';
import { MatTableModule } from '@angular/material/table' ;
import { MatCheckboxModule } from '@angular/material/checkbox';
import {MatButtonModule} from '@angular/material/button';
import {MatRadioModule} from '@angular/material/radio';
import {MatDialogModule} from '@angular/material/dialog';

import { NgImageSliderModule } from 'ng-image-slider';

import { CustomerUpdateComponent } from './customer-update/customer-update.component';
import { AdminCallReportsComponent } from './admin-call-reports/admin-call-reports.component';

@NgModule({
  declarations: [
    AppComponent,
      HeaderComponent,
      FooterComponent,
      HomeComponent,
      CustomerRegistrationComponent,
      CustomerDashboardComponent,
      AdminDashboardComponent,
      AdminCsrRegistrationComponent,
      AdminCsrUpdateComponent,
      CsrDashboardComponent,
      CsrCallFormComponent,
      LoginComponent,
      CustomerUpdateComponent,
      AdminCallReportsComponent
   ],
  imports: [
    BrowserModule,
    AppRoutingModule,BrowserAnimationsModule,HttpClientModule,
    ReactiveFormsModule, FormsModule,
    MatToolbarModule,
    MatExpansionModule,
    MatIconModule,
    MatTableModule,
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatRadioModule,
    MatCheckboxModule,
    MatSnackBarModule,
    MatSelectModule,
    MatInputModule,
    MatCardModule,MatDividerModule, NgImageSliderModule
  ],
  providers: [HttpClient],
  bootstrap: [AppComponent]
})
export class AppModule { }
