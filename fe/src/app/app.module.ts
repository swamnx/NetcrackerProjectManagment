import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import {Routes} from "@angular/router";

import { AppComponent } from './app.component';
import { MyheaderComponent } from 'src/app/modules/myheader/myheader.component';
import { AboutComponent } from './modules/about/about.component';
import { NotFoundComponent } from 'src/app/modules/not-found/not-found.component';
import { HomeComponent } from 'src/app/modules/home/home.component';
import { LoginComponent } from './modules/login/login.component';
import { RegistrationComponent } from './modules/registration/registration.component';

@NgModule({
  declarations: [
    AppComponent,
    MyheaderComponent,
    AboutComponent,
    NotFoundComponent,
    HomeComponent,
    LoginComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
