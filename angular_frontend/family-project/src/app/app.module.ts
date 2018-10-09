import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { AppComponent } from './app.component';
import {MatTableModule} from '@angular/material/table';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule, MatError, MatFormFieldModule, MatInputModule, MatNativeDateModule} from '@angular/material';
import { MainMenuComponent } from './main-menu/main-menu.component';
import {MatButtonModule} from '@angular/material/button';
import {MatListModule} from '@angular/material/list';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { SearchPageComponent } from './search-page/search-page.component';
import { AddingPageComponent } from './adding-page/adding-page.component';
import { FooterComponent } from './footer/footer.component';
import { MainRouter } from './main-router';
import {MatStepperModule} from '@angular/material/stepper';
import {MatSelectModule} from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { SearchPanelComponent } from './search-page/search-panel/search-panel.component';
import { HttpService } from './http-serivce';
import { FamilyTableComponent } from './search-page/family-table/family-table.component';

import { ShowFamilyComponent } from './show-family/show-family.component';
@NgModule({
  declarations: [
    AppComponent,
    MainMenuComponent,
    ToolbarComponent,
    SearchPageComponent,
    AddingPageComponent,
    FooterComponent,
    SearchPanelComponent,
    FamilyTableComponent,
    ShowFamilyComponent
  ],
  imports: [
    BrowserModule,
    MainRouter,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatListModule,
    FlexLayoutModule,
    ReactiveFormsModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTableModule,
    HttpClientModule,
    MatStepperModule
  ],
  providers: [HttpService],
  bootstrap: [AppComponent]
})
export class AppModule { }
