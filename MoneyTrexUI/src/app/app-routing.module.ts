import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';
import { RegistComponent } from './regist/regist.component';
import { LoginComponent } from './login/login.component';
import { CustomerDashboardComponent } from './customer/customer-dashboard/customer-dashboard.component';
import { CustomerHomeComponent } from './customer/customer-home/customer-home.component';
import { CustomerTransactionComponent } from './customer/customer-transaction/customer-transaction.component';
import { CustomerExpensesComponent } from './customer/customer-expenses/customer-expenses.component';
import { TransUpdateComponent } from './customer/trans-update/trans-update.component';
import { ReportComponent } from './customer/report/report.component';
import { AuthCustomerService } from './auth-customer.service';
import { AdminDashboardComponent } from './admin/admin-dashboard/admin-dashboard.component';
import { AuthAdminService } from './auth-admin.service';
import { AdminHomeComponent } from './admin/admin-home/admin-home.component';
import { UserDataComponent } from './admin/user-data/user-data.component';
import { UserTransactionsComponent } from './admin/user-transactions/user-transactions.component';

const routes: Routes = [
  {path:"",component:HomeComponent},
  {path:"about",component:AboutComponent},
  {path:"contact",component:ContactComponent},
  {path:"regist",component:RegistComponent},
  {path:"login",component:LoginComponent},
  {path:"customer",component:CustomerDashboardComponent,
    // canActivate:[AuthCustomerService],
    children:[
      {path:"",component:CustomerHomeComponent},
      {path:"trans",component:CustomerTransactionComponent},
      {path:"exp",component:CustomerExpensesComponent},
      {path:"transupdate/:id",component:TransUpdateComponent},
      {path:"report",component:ReportComponent},
    ]
    },
    {path:"admin",component: AdminDashboardComponent ,
    // canActivate :[AuthAdminService],
    children:[
      {path:"",component:AdminHomeComponent},
      {path:"userdata",component:UserDataComponent},
      {path:"usertrans/:id/:nm",component:UserTransactionsComponent},
    ]

  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
