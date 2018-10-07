import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { AppComponent } from './app.component';

import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material';
import { MainMenuComponent } from './main-menu/main-menu.component';
import {MatButtonModule} from '@angular/material/button';
import {MatListModule} from '@angular/material/list';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { SearchPageComponent } from './search-page/search-page.component';
import { AddingPageComponent } from './adding-page/adding-page.component';
import { FooterComponent } from './footer/footer.component';
import { MainRouter } from './main-router';
@NgModule({
  declarations: [
    AppComponent,
    MainMenuComponent,
    ToolbarComponent,
    SearchPageComponent,
    AddingPageComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    MainRouter,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatListModule,
    FlexLayoutModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
