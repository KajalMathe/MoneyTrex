import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegistComponent } from './regist/regist.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CustomerHomeComponent } from './customer/customer-home/customer-home.component';
import { CustomerTransactionComponent } from './customer/customer-transaction/customer-transaction.component';
import { CustomerExpensesComponent } from './customer/customer-expenses/customer-expenses.component';
import { CustomerDashboardComponent } from './customer/customer-dashboard/customer-dashboard.component';
import { TransUpdateComponent } from './customer/trans-update/trans-update.component';
import { ReportComponent } from './customer/report/report.component';
import { AdminDashboardComponent } from './admin/admin-dashboard/admin-dashboard.component';
import { AdminHomeComponent } from './admin/admin-home/admin-home.component';
import { UserDataComponent } from './admin/user-data/user-data.component';
import { UserTransactionsComponent } from './admin/user-transactions/user-transactions.component'

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    AboutComponent,
    ContactComponent,
    HomeComponent,
    LoginComponent,
    RegistComponent,
    CustomerHomeComponent,
    CustomerTransactionComponent,
    CustomerExpensesComponent,
    CustomerDashboardComponent,
    TransUpdateComponent,
    ReportComponent,
    AdminDashboardComponent,
    AdminHomeComponent,
    UserDataComponent,
    UserTransactionsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule ,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
