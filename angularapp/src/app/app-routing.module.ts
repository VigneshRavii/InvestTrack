import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { UserViewInquiryComponent } from './components/user-view-inquiry/user-view-inquiry.component';
import { UserAddFeedbackComponent } from './components/user-add-feedback/user-add-feedback.component';
import { UserViewFeedbackComponent } from './components/user-view-feedback/user-view-feedback.component';
import { AdminAddInvestmentComponent } from './components/admin-add-investment/admin-add-investment.component';
import { AdminViewInvestmentComponent } from './components/admin-view-investment/admin-view-investment.component';
import { AdminEditInvestmentComponent } from './components/admin-edit-investment/admin-edit-investment.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { AdminViewFeedbackComponent } from './components/admin-view-feedback/admin-view-feedback.component';
import { AdminViewInquiryComponent } from './components/admin-view-inquiry/admin-view-inquiry.component';
import { UserViewInvestmentComponent } from './components/user-view-investment/user-view-investment.component';
import { AdminConsoleComponent } from './components/admin-console/admin-console.component';
import { UserAddInquiryComponent } from './components/user-add-inquiry/user-add-inquiry.component';
import { AuthguardGuard } from './components/authguard/authguard.guard';
import { ErrorComponent } from './components/error/error.component';

const routes: Routes = [
  {path:'',redirectTo:'/login', pathMatch:'full'},
  {path:'login', component : LoginComponent },
  {path: 'register', component: RegisterComponent},
  {
    path:'user/view-inquiry',
    component:UserViewInquiryComponent,
    canActivate: [AuthguardGuard],
    data: {expectedRole : 'User'}
  },
  {
    path: 'user/add/feedback',
    component:UserAddFeedbackComponent,
    canActivate: [AuthguardGuard],
    data: {expectedRole : 'User'}
  },
  {
    path: 'user/view-feedback',
    component:UserViewFeedbackComponent,
    canActivate: [AuthguardGuard],
    data: {expectedRole : 'User'}
  },
  {
    path: 'admin/add/investment', 
    component: AdminAddInvestmentComponent,
    canActivate: [AuthguardGuard],
    data: {expectedRole : 'Admin'}
  },
  {
    path: 'admin/view-investment', 
    component: AdminViewInvestmentComponent,
    canActivate: [AuthguardGuard],
    data: {expectedRole : 'Admin'}
  },
  {
    path: 'admin/edit-investment/:investmentId', 
    component: AdminEditInvestmentComponent,
    canActivate: [AuthguardGuard],
    data: {expectedRole : 'Admin'}
  },
  {path:'home', component: HomePageComponent},
  {
    path: 'admin/view-inquiries',
    component:AdminViewInquiryComponent,
    canActivate: [AuthguardGuard],
    data: {expectedRole : 'Admin'}
  },
  {
    path: 'user/view-investment',
    component:UserViewInvestmentComponent,
    canActivate: [AuthguardGuard],
    data: {expectedRole : 'User'}
  },
  {
    path:'admin/console',
    component:AdminConsoleComponent,
    canActivate: [AuthguardGuard],
    data: {expectedRole : 'Admin'} 
  },
  {
    path:'admin/view/feedback', 
    component: AdminViewFeedbackComponent,
    canActivate: [AuthguardGuard],
    data: {expectedRole : 'Admin'} 
  },
  {
    path:'user/add-inquiry/:investmentId',
    component:UserAddInquiryComponent,
    canActivate: [AuthguardGuard],
    data: {expectedRole : 'User'} 
  },
  
  {
    path:'admin/view/feedback', 
    component: AdminViewFeedbackComponent,
    canActivate: [AuthguardGuard],
    data: {expectedRole : 'Admin'} 
  },
  {
    path:'error',
    component:ErrorComponent
  },
  {
    path:'user/add-inquiry/:investmentId',
    component:UserAddInquiryComponent,
    canActivate: [AuthguardGuard],
    data: {expectedRole : 'User'} 
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
