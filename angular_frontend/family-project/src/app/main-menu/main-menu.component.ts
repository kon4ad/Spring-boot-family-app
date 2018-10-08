import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.css']
})
export class MainMenuComponent implements OnInit {

  constructor(public route:Router) { }

  ngOnInit() {
  }

  toAddingPage(){
    this.route.navigate(['add/family']);
  }

  toSearchingPage(){
    this.route.navigate(['search']);
  }
}
