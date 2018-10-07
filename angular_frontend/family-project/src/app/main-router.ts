import { Injectable, NgModule, Component } from "@angular/core";

import { RouterModule } from '@angular/router';
import { MainMenuComponent } from "./main-menu/main-menu.component";
import { SearchPageComponent } from "./search-page/search-page.component";
import { AddingPageComponent } from "./adding-page/adding-page.component";


const patches = [{path:'', component: MainMenuComponent},
{path:'search', component: SearchPageComponent},
{path:'add/family', component: AddingPageComponent}]
 @NgModule ({
     imports: [RouterModule.forRoot(patches)],
     exports: [RouterModule]
 
 })
 export class MainRouter{}