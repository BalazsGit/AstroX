import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
//import { ResizeDirective } from './resize.directive';
//import { ResizeObserverDirective } from './mainpage/resize-observer.directive';

import { AppComponent } from './app.component';
import { MainpageComponent } from './mainpage/mainpage.component';
import { ResizeDirective } from './resize.directive';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { FormsModule,
         ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatInputModule } from '@angular/material/input';
import { NgIf } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserpageComponent } from './browserpage/browserpage.component';

@NgModule({
  declarations: [
    AppComponent,
    MainpageComponent,
    ResizeDirective,
    BrowserpageComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    NgIf,
    MatToolbarModule,
    MatSidenavModule,
    FormsModule,
    ReactiveFormsModule,
    MatCheckboxModule,
    MatFormFieldModule,
    MatButtonModule,
    MatInputModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
