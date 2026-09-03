import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AdminAddInvestmentComponent } from './components/admin-add-investment/admin-add-investment.component';
import { AdminViewInquiryComponent } from './components/admin-view-inquiry/admin-view-inquiry.component';
import { UserAddInquiryComponent } from './components/user-add-inquiry/user-add-inquiry.component'
import { SearchfilterPipe } from './pipes/searchfilter.pipe';

import { AdminViewFeedbackComponent } from './components/admin-view-feedback/admin-view-feedback.component';
import { UserAddFeedbackComponent } from './components/user-add-feedback/user-add-feedback.component';
import { UserViewFeedbackComponent } from './components/user-view-feedback/user-view-feedback.component';

import { AdminViewInvestmentComponent } from './components/admin-view-investment/admin-view-investment.component';
import { SearchPipe } from './pipes/search.pipe';
import { FilterPipe } from './pipes/filter.pipe';
import { UserViewInquiryComponent } from './components/user-view-inquiry/user-view-inquiry.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { UserViewInvestmentComponent } from './components/user-view-investment/user-view-investment.component';
import { AdminnavComponent } from './components/adminnav/adminnav.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { AdminConsoleComponent } from './components/admin-console/admin-console.component';
import { ErrorComponent } from './components/error/error.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminEditInvestmentComponent } from './components/admin-edit-investment/admin-edit-investment.component';
import { UsernavComponent } from './components/usernav/usernav.component';

import { TokenInterceptor } from './helpers/token.interceptor';
import { NgxPaginationModule } from 'ngx-pagination';

@NgModule({
  declarations: [
    AppComponent,
    AdminAddInvestmentComponent,
    AdminViewInquiryComponent,
    UserAddInquiryComponent,
    SearchfilterPipe,
    AdminViewFeedbackComponent,
    UserAddFeedbackComponent,
    UserViewFeedbackComponent,
    AdminViewInvestmentComponent,
    SearchPipe,
    FilterPipe,
    UserViewInquiryComponent,
    LoginComponent,
    RegisterComponent,
    UserViewInvestmentComponent,
    AdminnavComponent,
    HomePageComponent,
    AdminConsoleComponent,
    ErrorComponent,
    AdminEditInvestmentComponent,
    UsernavComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgxPaginationModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
