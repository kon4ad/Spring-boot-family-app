import { Injectable, NgModule, Component } from "@angular/core";

import { RouterModule } from '@angular/router';
import { MainMenuComponent } from "./main-menu/main-menu.component";
import { SearchPageComponent } from "./search-page/search-page.component";
import { AddingPageComponent } from "./adding-page/adding-page.component";
import { ShowFamilyComponent } from "./show-family/show-family.component";


const patches = [{path:'', component: MainMenuComponent},
{path:'search', component: SearchPageComponent},
{path:'add/family', component: AddingPageComponent},
{path:'read/family/:id', component: ShowFamilyComponent}]
 @NgModule ({
     imports: [RouterModule.forRoot(patches)],
     exports: [RouterModule]
 
 })
 export class MainRouter{}