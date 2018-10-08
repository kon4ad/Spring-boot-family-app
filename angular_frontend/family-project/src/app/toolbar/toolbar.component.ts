import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent implements OnInit {

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
